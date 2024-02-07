public interface DiscountStrategy {
    public double gettotal(Item[] items);
}

class PercentageDiscountStrategy implements DiscountStrategy {
    
    private double percent;
    private double totalcost;


    public PercentageDiscountStrategy(double percent){
        this.percent=percent;
    }

    public double gettotal(Item[] items){

        for(Item i : items){
            totalcost += i.getQuantity()*i.getPrice();
        }

        return totalcost*(1-(percent/100));
    }

}

class FixedDiscountStrategy implements DiscountStrategy {

    private double amount;
    private double totalcost;

    public FixedDiscountStrategy(double amount){
        this.amount=amount;
    }

    public double gettotal(Item[] items){

        for(Item i : items){
            totalcost += i.getQuantity()*i.getPrice();
        }
        return totalcost-amount;
    }
}