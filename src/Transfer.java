import java.math.BigDecimal;

public class Transfer extends Transaction {
    private final int destinationAccountID;
    private final int sourceAccountID;

    public Transfer(int transactionID, BigDecimal amount, int date, int destinationAccountID, int sourceAccountID) {
        super(transactionID, amount, date);
        this.destinationAccountID = destinationAccountID;
        this.sourceAccountID = sourceAccountID;
        transferCash();
    }

    private boolean transferCash() {
        //this.amount is the amount to transfer to the destinationAccount
        //update amount in the bank accounts;
        //if transfer is succesful, return true, else false
        boolean transferSuccessful = false;
        //run availableBalanceInSourceAccount to check if there is enough money in the source account to attempt transfer
        return transferSuccessful;
    }

    private boolean availableBalanceInSourceAccount()
    {
        boolean fundsAvailable = false;
        //check if the transfer is possible, else return false
        return fundsAvailable;
    }
}