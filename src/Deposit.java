import java.math.BigDecimal;

public class Deposit extends Transaction
{
    private final int destinationAccountID;

    public Deposit(int transactionID, BigDecimal amount, int date, int destinationAccountID)
    {
        super(transactionID, amount, date);
        this.destinationAccountID = destinationAccountID;
        depositCash();
    }

    private boolean depositCash()
    {
        //this.amount is the amount to deposit to the account
        //update amount in the bank account;
        //if deposit is succesful, return true, else false
        boolean depositSuccessful = false;
        return depositSuccessful;
    }
}