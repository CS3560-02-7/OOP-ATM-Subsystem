import java.math.BigDecimal;

public abstract class Account
{
    protected int accountID;
    protected BigDecimal balance;
    protected BigDecimal overdraftFee;
    protected BigDecimal minimumBalance;

    public Account(int accountID, String balance, String overdraftFee, String minimumBalance)
    {
        this.accountID = accountID;
        this.balance = new BigDecimal(balance);
        this.overdraftFee = new BigDecimal(overdraftFee);
        this.minimumBalance = new BigDecimal(minimumBalance);
    }
}
