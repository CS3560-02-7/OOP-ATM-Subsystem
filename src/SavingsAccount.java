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

    /*
    Every year apply the interest rate to the balance and increase it.

    FOR STUDENTS:
    see this link on how to calculate interest rate with BigDecimal
    https://stackoverflow.com/questions/9188887/bigdecimal-class-java
     */
    private void applyInterestRate()
    {
        this.balance=this.balance;
    }


}
