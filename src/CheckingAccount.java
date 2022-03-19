import java.math.BigDecimal;

public class CheckingAccount extends Account
{
    private final BigDecimal monthlyFee;

    public CheckingAccount(int accountID, String balance, String overdraftFee, String minimumBalance,
                           String monthlyfee)
    {
        super(accountID, balance, overdraftFee, minimumBalance);
        this.monthlyFee = new BigDecimal(monthlyfee);
    }

    /*
    every month this method will be run and will deduct monthlyFee from balance
     */
    private void deductMontlyFee()
    {
        this.balance = this.balance.subtract(monthlyFee);
    }
}