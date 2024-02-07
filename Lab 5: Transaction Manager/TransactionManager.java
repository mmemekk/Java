public interface TransactionManager {

    public boolean transferFunds (String senderWalletID, String receiverWalletID, double amount)throws InvalidTransactionException;
    public double getBalance (String walletID) throws IllegalArgumentException;
    public boolean isValidWallet (String walletID);
    
}
