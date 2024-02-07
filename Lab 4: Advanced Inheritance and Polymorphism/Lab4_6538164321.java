public class Lab4_6538164321 {
    public static void main(String[] args) throws Exception {

        Shape[] data = new Shape[5];
        data[0] = new Square("Red",5.0);
        data[1] = new Square("Blue",3.0);
        data[2] = new Rectangle("Green",4.0,6.0);
        data[3] = new Rectangle("Yellow",2.0,8.0);
        data[4] = new Circle("Orange",7.0);

        for(Shape x : data){
            x.printDetails();
            System.out.println("----------------------");
        }

        //if resizing
        // Shape b = new Square("red",5.0);
        // b.printDetails();
        // b.resize(2);
        // b.printDetails();
    }
}

abstract class Shape{
    protected String color;

    public Shape(String color){
        this.color=color;
    }

    public abstract double getArea();
    public abstract void printDetails();

    public abstract void resize(double factor);

    public String getColor(){
        return color;
    }

}

class Square extends Shape{
    private double length;

    public Square(String color, double length){
        super(color);
        this.length=length;
    }

    @Override
    public void resize(double factor){
        this.length=this.length*factor;
    }

    @Override
    public double getArea(){
        return length*length;
    }

    @Override
    public void printDetails(){
        System.out.println("Shape: "+ this.getClass().getSimpleName());
        System.out.println("Color: " + this.color);
        System.out.println("Side Length: "+ this.length);
        System.out.println("Area: " + this.getArea());
    }


}

class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(String color, double width, double height){
        super(color);
        this.width = width;
        this.height= height;
    }

    @Override
    public void resize(double factor){
        this.width=this.width*factor;
        this.height=this.height*factor;
    }

    @Override
    public double getArea(){
        return width*height;
    }

    @Override
    public void printDetails(){
        System.out.println("Shape: "+ this.getClass().getSimpleName());
        System.out.println("Color: " + this.color);
        System.out.println("Width: "+ this.width);
        System.out.println("Height: "+ this.height);
        System.out.println("Area: " + this.getArea());
    }
}

class Circle extends Shape{
    private double radius;

    public Circle(String color, double radius){
        super(color);
        this.radius=radius;
    }

    @Override
    public void resize(double factor){
        this.radius=this.radius*factor;
    }

    @Override
    public double getArea(){
        return Math.PI*radius*radius;
    }
    
    @Override
    public void printDetails(){
        System.out.println("Shape: "+ this.getClass().getSimpleName());
        System.out.println("Color: " + this.color);
        System.out.println("Radius: "+ this.radius);
        System.out.println("Area: " + this.getArea());
    }
}

