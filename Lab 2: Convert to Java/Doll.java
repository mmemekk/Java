
public class Doll {
    private String name;
    private String material;
    private float price;

    public Doll(String name, String material, float price) {
        this.name = name;
        this.material = material;
        this.price = price;
    }

    public String toString() { //automatically called when printing the object
        return name;
    }

    public void play(){
        System.out.println("I don't know. How to play");
    }

    public void displayInfo() {
        System.out.println("\nName: "+name+"\nMaterial: "+material+"\nPrice: $"+price);
    }

    public Boolean isFragile(){
        if (material == "Porcelain" || material == "Glass"){
            return true;
        } else{
            return false;
        }
    }
}

class Barbie extends Doll {
    public Barbie(String name, String material, float price) {
        super(name, material, price);
    }
    @Override //indicate to override the method in superclass
    public void play() {
        System.out.println("Barbie sings: I'm a Barbie girl in a Barbie world!");
    }
}

class TeddyDoll extends Doll {
    public TeddyDoll(String name, String material, float price) {
        super(name, material, price);
    }
    @Override
    public void play() {
        System.out.println("Teddy Doll says: Hug me!");
    }
}

class PorcelainDoll extends Doll {
    public PorcelainDoll(String name, String material, float price) {
        super(name, material, price);
    }
    @Override
    public void play() {
        System.out.println("Porcelain Doll is delicate, be gentle!");
    }
}
