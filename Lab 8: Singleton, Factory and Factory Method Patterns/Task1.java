import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class FileReaderSingleton{

    private static FileReaderSingleton instance;
    private BufferedReader reader;

    private FileReaderSingleton(){
        openFile();
    }

    public static FileReaderSingleton getInstance(){
        if (instance == null) {
            instance = new FileReaderSingleton();
        }
        return instance;
    }

    public void openFile(){
        try{
            reader = new BufferedReader(new FileReader("data.txt"));
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public String readLineFromFile(){
        try{
            return reader.readLine();
        } catch(IOException e){
            e.printStackTrace();
        }
        return null; //ensure that if error occurs it still returns something
    }

    public void closeFile(){
        try{
            reader.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}

// Class A for reading from the file
class FileReaderClassA {
    FileReaderSingleton fileReader;
    public FileReaderClassA(FileReaderSingleton fileReader) {
        this.fileReader = fileReader;
    }
    public void readFileLine() {
        String line = fileReader.readLineFromFile();
        if (line != null) {
            System.out.println("FileReaderClassA: " + line);
        }
    }
}

// Class B for reading from the file
class FileReaderClassB {
    FileReaderSingleton fileReader;
    public FileReaderClassB(FileReaderSingleton fileReader) {
        this.fileReader = fileReader;
    }
    public void readFileLine() {
        String line = fileReader.readLineFromFile();
        if (line != null) {
            System.out.println("FileReaderClassB: " + line);
        }
    }
}

public class Task1 {
    public static void main(String[] args) {
        FileReaderSingleton fileReader = FileReaderSingleton.getInstance();
        FileReaderClassA readerA = new FileReaderClassA(fileReader);
        FileReaderClassB readerB = new FileReaderClassB(fileReader);

        // Call the readFileLine() method on both instances
        readerA.readFileLine(); //testline 1
        readerB.readFileLine(); //testline 2
        readerB.readFileLine(); //testline 3
        //A and B based on the same filereader;hence, the result will be continuous although A and B are different class
        fileReader.closeFile();//close
        readerA.readFileLine();
        readerB.readFileLine();

    }
}
