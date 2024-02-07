// The assignment is ambiguous whether the deliveryFee should be added before or after the discount
//Thus, this code is written based on the assumption that the deliveryFee is added after the discount

import java.util.ArrayList;

class ShoppingCart{
    private ArrayList<String> items = new ArrayList<String>();
    private double totalcost;
    protected static int deliveryFee;

    public void addItem(String newitem){
        items.add(newitem);
    }

    public double calculateTotal(){
        totalcost = 0;
        for(String i : items){
            String[] parts = i.split(":");
            double quantity = Double.parseDouble(parts[1]);
            double cost = Double.parseDouble(parts[2]);
            totalcost+=quantity*cost;
        }
        return totalcost;
    }

    public void addDeliveryFee(int fee){
        deliveryFee=fee;
    }

    public int getDeliveryFee(){
        return deliveryFee;
    }

    public double calculatewithdiscount(){
        return totalcost;
    }

    public double getNetprice(){
        return this.calculateTotal();
    }

}

class Discount extends ShoppingCart{  //the type of var is "ShoppingCart";thus, Discount must inherit from ShoppingCart
    public ShoppingCart shoppingCart;  
    protected static boolean isPercentApplied = false;
    protected static boolean isAmountApplied = false;
    protected static boolean isFreeApplied = false;

    public Discount(ShoppingCart shoppingCart){
        this.shoppingCart = shoppingCart;
    }
}

class DiscountByPercentDecorator extends Discount{

    private double percent;
    private boolean useable;

    public DiscountByPercentDecorator(ShoppingCart shoppingCart , double percent){
        super(shoppingCart);
        this.percent=percent;
        if(isPercentApplied == false){
            useable = true;
            isPercentApplied = true;
        } else{
            useable = false;
        } 
    }


    public double calculateTotal(){
        return this.shoppingCart.calculateTotal();
    }

    public int getDeliveryFee(){
        return this.shoppingCart.getDeliveryFee();
    }

    public double calculatewithdiscount(){
        if(useable == true){
            return this.shoppingCart.calculatewithdiscount()*(1-(percent/100));
        }else{
            return this.shoppingCart.calculatewithdiscount();
        }
    }
    public double getNetprice(){
        return this.calculatewithdiscount()+this.getDeliveryFee();
    }
}

class DiscountByAmountDecorator extends Discount{

    private double amount;
    private boolean useable;

    public DiscountByAmountDecorator(ShoppingCart shoppingCart ,double amount){
        super(shoppingCart);
        this.amount = amount;
        if(isAmountApplied == false){
            useable = true;
            isAmountApplied = true;
        } else{
            useable = false;
        } 
    }

    public double calculateTotal(){
        return this.shoppingCart.calculateTotal();
    }

    public int getDeliveryFee(){
        return this.shoppingCart.getDeliveryFee();
    }

    public double calculatewithdiscount(){
        if(useable == true){
            return this.shoppingCart.calculatewithdiscount()-amount;
        } else {
            return this.shoppingCart.calculatewithdiscount();
        }
    }

    public double getNetprice(){
        return this.calculatewithdiscount()+this.getDeliveryFee();
    }
}

class FreeDeliveryDecorator extends Discount{
    

    public FreeDeliveryDecorator(ShoppingCart shoppingCart){
        super(shoppingCart);
    }

    public double calculateTotal(){
        return this.shoppingCart.calculateTotal();
    }

    public int getDeliveryFee(){
        return this.shoppingCart.getDeliveryFee();
    }

    public double calculatewithdiscount(){
        deliveryFee=0;
        return this.shoppingCart.calculatewithdiscount();
    }

    public double getNetprice(){
        return this.calculatewithdiscount();
    }
}

public class Task1 {
    public static void main(String[] args) throws Exception {
        ShoppingCart cart = new ShoppingCart();

        cart.addItem("Item1:2:50.0"); // net price is 100.0
        cart.addItem("Item2:1:30.0"); // net price is 130.0
        cart = new DiscountByAmountDecorator(cart, 10);
        cart = new DiscountByPercentDecorator(cart, 20);
        cart = new FreeDeliveryDecorator(cart);

        System.out.println(cart.calculateTotal()); //return totalprice, which is 130
        System.out.println(cart.getNetprice()); //return after all discount, which is (130-10)*0.8 = 96


        //illustrating that the coupon can be used only once
        ShoppingCart cart2 = new ShoppingCart();

        cart2.addItem("Item1:2:50.0");
        cart2.addItem("Item2:1:30.0"); 
        cart2.addDeliveryFee(50);

        cart2 = new DiscountByAmountDecorator(cart, 10);
        cart2 = new DiscountByAmountDecorator(cart, 100);
        cart2 = new DiscountByPercentDecorator(cart, 20);
        cart2 = new DiscountByPercentDecorator(cart, 40);
        cart2 = new FreeDeliveryDecorator(cart);

        System.out.println(cart.calculateTotal()); //return totalprice, which is 130
        System.out.println(cart.getNetprice());  //return after all discount(coupon can be applied only once)
    }
}

// Superclass must have the methods for the subclasses to override
// because we the reference of var is to superclass, otherwise methos in subclass cannot access
