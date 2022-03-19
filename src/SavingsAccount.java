import java.math.BigDecimal;

public class SavingsAccount extends Account
{
    private BigDecimal withdrawLimit;
    private BigDecimal interestRate;

    public SavingsAccount(int accountID, String balance, String overdraftFee, String minimumBalance,
                          String withdrawLimit, String interestRate)
    {
        super(accountID, balance, overdraftFee, minimumBalance);
        this.withdrawLimit = new BigDecimal(withdrawLimit);
        this.interestRate = new BigDecimal(interestRate);
    }

}
