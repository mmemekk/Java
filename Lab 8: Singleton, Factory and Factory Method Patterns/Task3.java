interface PaymentMethod{
    void processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod{
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


interface PaymentMethodFactory{
    public PaymentMethod createPaymentMethod();
}

class CreditCardPaymentFactory implements PaymentMethodFactory{ 
    
    @Override
    public PaymentMethod createPaymentMethod(){
        return new CreditCardPayment();
    }
}

class PayPalPaymentFactory implements PaymentMethodFactory{

    @Override
    public PaymentMethod createPaymentMethod(){
        return new PayPalPayment();
    }
}

class CryptoPaymentFactory implements PaymentMethodFactory{

    @Override
    public PaymentMethod createPaymentMethod(){
        return new CryptoPayment();
    }
}

public class Task3 {
    public static void main(String[] args) {
        //create the fatory
        PaymentMethodFactory creditCardFactory = new CreditCardPaymentFactory();
        PaymentMethodFactory payPalFactory = new PayPalPaymentFactory();
        PaymentMethodFactory cryptoFactory = new CryptoPaymentFactory();
        
        PaymentMethod creditCardPayment = creditCardFactory.createPaymentMethod();
        PaymentMethod payPalPayment = payPalFactory.createPaymentMethod();
        PaymentMethod cryptoPayment = cryptoFactory.createPaymentMethod();

        // Test Your Implementation
        double paymentAmount = 100.0;
        creditCardPayment.processPayment(paymentAmount);
        payPalPayment.processPayment(paymentAmount);
        cryptoPayment.processPayment(paymentAmount);
    }
}
