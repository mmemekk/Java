import java.util.ArrayList;

interface object{
    public void registerObserver(Door door);
    public void removeObserver(Door door);
    public void updateDoorOpen(Door door);
    public void updateDoorClose(Door door);
}
class ControlCenter implements object{
    private ArrayList<Door> doors = new ArrayList<Door>();

    public void registerObserver(Door door){
        doors.add(door);
    }

    public void removeObserver(Door door){
        doors.remove(door);
    }

    public void updateDoorOpen(Door door){
        door.open();
    }

    public void updateDoorClose(Door door){
        door.close();
    }

    public void openAllDoors(){
        for (Door door :doors){
            updateDoorOpen(door);
        }
    }

    public void closeAllDoors(){
        for (Door door :doors){
           updateDoorClose(door);
        }
    }
}

interface Observer{
    public void open();
    public void close();
}

class Door implements Observer{ 
    private String doorname;

    public Door(String doorname){
        this.doorname=doorname;
    }

    public void open(){
        System.out.println("Door "+this.doorname+" opened");
    }

    public void close(){
        System.out.println("Door "+this.doorname+" closed");
    }
}

public class Task2 {
    public static void main(String[] args) throws Exception {
        Door D1 = new Door("D1");
        Door D2 = new Door("D2");
        Door D3 = new Door("D3");

        ControlCenter controlCenter = new ControlCenter();
        controlCenter.registerObserver(D1);
        controlCenter.registerObserver(D2);
        controlCenter.registerObserver(D3);

        //manually control the door
        D1.open();
        D2.open();
        D1.close();
        //control with control center
        controlCenter.openAllDoors();
        controlCenter.closeAllDoors();

        Door D4 = new Door("D4");
        controlCenter.registerObserver(D4);
        controlCenter.openAllDoors();

    }
}

