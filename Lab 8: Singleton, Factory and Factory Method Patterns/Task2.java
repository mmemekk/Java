interface PaymentMethod{
    void processPayment(double amount);
}

class CreditCardPaymnet implements PaymentMethod{
    @Override
    public void processPayment(double amount){
        System.out.println("Processing credit card payment of $"+Double.toString(amount));
    }
}


class PayPalPayment implements PaymentMethod{
    @Override
    public void processPayment(double amount){
        System.out.println("Processing PayPal payment of $"+Double.toString(amount));
    }
}


class CryptoPayment implements PaymentMethod{
    @Override
    public void processPayment(double amount){
        System.out.println("Processing cyptocurrency payment of $"+Double.toString(amount));
    }
}

class PaymentMethodFactory{
    public PaymentMethod createPaymentMethod(String type){

        if (type.equalsIgnoreCase("credit card")){
            return new CreditCardPaymnet();
        } else if (type.equalsIgnoreCase("paypal")){
            return new PayPalPayment();
        } else if (type.equalsIgnoreCase("crypto")){
            return new CryptoPayment();
        }
        return null;
    }
}
public class Task2 {
    public static void main(String[] args) {
        // Implement the Client Code
        PaymentMethodFactory paymentFactory = new PaymentMethodFactory();

        // Create payment objects without knowing the exact class
        PaymentMethod creditCardPayment = paymentFactory.createPaymentMethod("credit card");
        PaymentMethod payPalPayment = paymentFactory.createPaymentMethod("paypal");
        PaymentMethod cryptoPayment = paymentFactory.createPaymentMethod("crypto");

        // Test Your Implementation
        double paymentAmount = 100.0;
        creditCardPayment.processPayment(paymentAmount);
        payPalPayment.processPayment(paymentAmount);
        cryptoPayment.processPayment(paymentAmount);
    }
}
