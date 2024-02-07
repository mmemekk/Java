public class App {
    public static void main(String[] args) {
        // Sample usage
        String[] initialWallets = {"Wallet0"};

        TransactionManager transactionManager = new SimpleTransactionManager(initialWallets);
        try {
            transactionManager.transferFunds("Wallet0", "Wallet1", 100.0);
            System.out.println("Balance of Wallet1: " + transactionManager.getBalance("Wallet1"));
            transactionManager.transferFunds("Wallet1", "Wallet2", 50.0);
            System.out.println("Balance of Wallet1: " + transactionManager.getBalance("Wallet1"));
            System.out.println("Balance of Wallet2: " + transactionManager.getBalance("Wallet2"));
            transactionManager.transferFunds("Wallet2", "Wallet3", 25.0);
            System.out.println("Balance of Wallet2: " + transactionManager.getBalance("Wallet2"));
            System.out.println("Balance of Wallet3: " + transactionManager.getBalance("Wallet3"));
            transactionManager.transferFunds("Wallet3", "Wallet1", 10.0);
            System.out.println("Balance of Wallet1: " + transactionManager.getBalance("Wallet1"));
            System.out.println("Balance of Wallet3: " + transactionManager.getBalance("Wallet3"));
            System.out.println(transactionManager.isValidWallet("Wallet1"));
            System.out.println(transactionManager.isValidWallet("Wallet4"));


       }
        catch (Exception e) { //if any exception comes in just throw, no need to specify type
            System.out.println(e.getMessage());
        }
        try {
            transactionManager.transferFunds("Wallet3", "Wallet1", 100.0);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            transactionManager.transferFunds("Wallet5", "Wallet1", 100.0);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

interface TransactionManager {

    public boolean transferFunds (String senderWalletID, String receiverWalletID, double amount)throws InvalidTransactionException;
    public double getBalance (String walletID) throws IllegalArgumentException;
    public boolean isValidWallet (String walletID);
    
}

