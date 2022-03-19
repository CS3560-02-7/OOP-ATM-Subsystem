import java.math.BigDecimal;

public class SavingsAccount extends Account
{
    private final BigDecimal withdrawLimit;
    private final BigDecimal interestRate;

    public SavingsAccount(int accountID, String balance, String overdraftFee, String minimumBalance,
                          String withdrawLimit, String interestRate)
    {
        super(accountID, balance, overdraftFee, minimumBalance);
        this.withdrawLimit = new BigDecimal(withdrawLimit);
        this.interestRate = new BigDecimal(interestRate);
    }

}
