public class App {
    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        Item item1 = new Item("Widget", 3, 10.99);
        Item item2 = new Item("Gadget", 2, 19.99);

        cart.addItem(item1);
        cart.addItem(item2);

        //dicount 10 percent
        cart.setDiscountStrategy(new PercentageDiscountStrategy(10));
        System.out.println(cart.calculateTotal());

        //discount by 50
        cart.setDiscountStrategy(new FixedDiscountStrategy(50));
        System.out.println(cart.calculateTotal());

        Item item3 = new Item("Food", 1, 100);
        cart.addItem(item3);
        cart.setDiscountStrategy(new FixedDiscountStrategy(20)); //if don't create new discoint the leftover from last time will also be calcualted.
        System.out.println(cart.calculateTotal());
    }
}

