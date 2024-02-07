import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class TestFile{

    public HashMap<String,int[]> read_scores(String filename){
        HashMap<String,int[]> data =new HashMap<String,int[]>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;
            line=reader.readLine(); //skip the first line
            while ((line = reader.readLine()) != null) {
                String[] datalist = line.split(",");
                String id = null;
                int[] scores = new int[datalist.length-1];

                for (int i = 0; i < datalist.length; i++) {
                    if (i==0){
                        id = datalist[i];
                    }
                    else{
                        scores[i-1] = Integer.parseInt(datalist[i]);
                    }
                }

                data.put(id,scores); //append 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public int getTotalScore(HashMap<String,int[]> allscore,String id){
        int sumscore=0;
        for (int i : allscore.get(id)){
            sumscore+=i;
        }
        return sumscore;
    }

    public HashSet<String> getEditedStudentID(String filepath){
        HashSet<String> data = new HashSet<String>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            line = reader.readLine(); //skip the first line
            while((line =reader.readLine())!=null){
                String[] datalist = line.split(",");
                data.add(datalist[0]);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return data;
    }

    public void createNewEditedScore(String originalfilepath, String editedfilepath, String newfilepath){
        HashMap<String, int[]> data = read_scores(originalfilepath);
        HashSet<String> editedid = getEditedStudentID(editedfilepath);

        //Fixing Scores for all students
        for(String i : editedid){
            String id = i;
            //List of Scores that needed to be fixed for a specific id
            ArrayList <int[]> alledit = new ArrayList<>();
            
            //get all Scores that need to be fixed for a specific id
            try(BufferedReader reader = new BufferedReader(new FileReader(editedfilepath))){
                String line;
                line = reader.readLine(); //skip the first line
                while((line =reader.readLine())!=null){
                    String[] linesplit =  line.split(",");
                    if(linesplit[0].equals(id)){
                        int quiz = Integer.parseInt(linesplit[1]);
                        int score = Integer.parseInt(linesplit[2]);
                        int[] array = {quiz,score};
                        alledit.add(array);
                    }
                }

            } catch (IOException e){
            e.printStackTrace();
            }

            // Fixing the Scores for a specific id
            int[] studentscore = data.get(id);
            for(int[]a:alledit){
                int fixedquiz = a[0];
                int newscore = a[1];
                studentscore[fixedquiz-1]=newscore;
            }
            data.put(id,studentscore);
        }


        //Writeing new Scores to new file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newfilepath))) {
            writer.write("ID,Quiz,New_Score\n");
            for (HashMap.Entry<String,int[]> entry : data.entrySet()) {
                String name = entry.getKey();
                int[] studentScore = entry.getValue();
                String[] stringstudentScore = new String[studentScore.length];

                for(int i=0; i<studentScore.length ;i++) {
                    stringstudentScore[i] = Integer.toString(studentScore[i]);
                }

                writer.write(name+","+String.join(",",stringstudentScore)+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

public class Lab9_6538164321 {
    public static void main(String[] args) throws Exception {
       // Test the methods
        TestFile testFile = new TestFile();

        // Task 1: Test the 'read_scores' method
        HashMap<String, int[]> allScores = testFile.read_scores("student_scores.csv");
        System.out.println("All Scores:");
        for (String studentID : allScores.keySet()) {
            System.out.println(studentID + " - " + Arrays.toString(allScores.get(studentID)));
        }

        // Task 2: Test the 'getTotalScore' method
        String studentIDToLookup = "6632462421";
        int totalScore = testFile.getTotalScore(allScores, studentIDToLookup);
        if (totalScore != -1) {
            System.out.println("Total Score for " + studentIDToLookup + ": " + totalScore);
        } else {
            System.out.println("Student not found.");
        }

        // Task 3: Test the 'getEditedStudentID' method
        HashSet<String> editedStudentIDs = testFile.getEditedStudentID("edited_scores.csv");
        System.out.println("Edited Student IDs: " + editedStudentIDs);

        // Task 4: Test the 'createNewEditedScore' method
        testFile.createNewEditedScore("student_scores.csv", "edited_scores.csv", "new_scores.csv");
        System.out.println("New scores have been written to 'new_scores.csv'.");
    }
}