class ShoppingCart {
    private Item[] items;
    private DiscountStrategy discountStrategy;

    public ShoppingCart() {
        items = new Item[0];
        // discountStrategy = new SampleDiscountStrategy(); // Default strategy
    }

    public void addItem(Item item) {
        // Extend the array to accommodate the new item
        Item[] newItems = new Item[items.length + 1];
        for(int i=0;i<items.length;i++){
            newItems[i] = items[i];
        }
        newItems[items.length] = item;
        items = newItems;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy){
        this.discountStrategy = discountStrategy;
    }

    public double calculateTotal(){
        return discountStrategy.gettotal(items);

    }
}

