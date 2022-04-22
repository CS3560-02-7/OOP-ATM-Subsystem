import java.math.BigDecimal;

public abstract class Account
{
    protected int accountID;
    protected int memberID;
    protected BigDecimal balance;
    protected BigDecimal overdraftFee;
    protected BigDecimal minimumBalance;

    public Account(int accountID, int memberID, String balance, String overdraftFee, String minimumBalance)
    {
        this.accountID = accountID;
        this.memberID = memberID;
        this.balance = new BigDecimal(balance);
        this.overdraftFee = new BigDecimal(overdraftFee);
        this.minimumBalance = new BigDecimal(minimumBalance);
    }

    protected BigDecimal getBalance(){
        return this.balance;
    }

    /*
    This method will gather the transactions for this particular account and display them in the GUI.
     */
    protected void showTransactionHistory()
    {

    }

}
