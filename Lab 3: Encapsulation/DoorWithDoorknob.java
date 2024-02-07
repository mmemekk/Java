public class DoorWithDoorknob extends Door{
    private Doorknob doorknob;

    public DoorWithDoorknob(String doorID) {
        super(doorID);
    }

    public void installDoorknob(Doorknob doorknob){
        this.doorknob = doorknob;
        System.out.println("A doorknob with ID " + doorknob.getDoorknobID()+" already installed");
    }

    // public void unlock(Key key){
    //     if (key.getKeyID().equals(doorknob.getDoorknobID())){
    //         System.out.println("Door "+this.getDoorID()+" is unlocked.");
    //         this.locked=false;
    //     } else{
    //         System.out.println("Key is not correct");
    //     }
    // }
    public void unlock(Key key){
        if(doorknob.canUnlock(key)==true){
            this.unlock();
        }
    }
}