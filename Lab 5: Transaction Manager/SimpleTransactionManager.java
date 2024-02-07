class InvalidTransactionException extends Exception{
    public InvalidTransactionException(String message){
        super(message);
    }
}

public class SimpleTransactionManager implements TransactionManager{

    private String[] wallets = new String[1000];
    private String[] transactions = new String[10000];    
    private int walletCount;
    private int transactionCount;

    public SimpleTransactionManager(String[] x){
        System.arraycopy(x, 0, wallets, walletCount, x.length);
        walletCount++;
    }

    public boolean transferFunds(String senderWalletID, String receiverWalletID, double amount) throws InvalidTransactionException{
        if (!isValidWallet(senderWalletID)){
            throw new IllegalArgumentException("Sender wallet does not exist.");
        }

        if (senderWalletID != "Wallet0" && getBalance(senderWalletID) < amount){ //check senderID and verify balance
            throw new InvalidTransactionException("Not enough balance in the source wallet.");
        }

        if (!isValidWallet(receiverWalletID)){  //create a new reveriver wallet
            wallets[walletCount]=receiverWalletID;
            walletCount++;
        }

        transactions[transactionCount] = senderWalletID + "|" + receiverWalletID + "|" + Double.toString(amount);
        transactionCount++;

        return true; // do nothing
    }

    public boolean isValidWallet (String walletID){
        for (String i : wallets){
            if (i==walletID) return true;
        }
        return false;
    }

    public double getBalance(String walletId) throws IllegalArgumentException{

        if (!isValidWallet(walletId)) {
            throw new IllegalArgumentException("Invalid wallet ID.");
        }

        double balance = 0.0;
        for (int i = 0; i < transactionCount; i++) {
            String[] parts = transactions[i].split("\\|");
            if (parts[0].equals(walletId)) {
                balance -= Double.parseDouble(parts[2]);
            }
            if (parts[1].equals(walletId)) {
                balance += Double.parseDouble(parts[2]);
            }
        }
        return balance;
    }

}
