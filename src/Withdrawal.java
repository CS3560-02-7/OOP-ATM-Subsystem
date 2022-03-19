import java.math.BigDecimal;

public class Withdrawal extends Transaction
{
    private int sourceAccountID;
    private BigDecimal maxWithdrawalAmount;

    public Withdrawal(int transactionID, BigDecimal amount, int date, int sourceAccountID,
                      BigDecimal maxWithdrawalAmount)
    {
        super(transactionID, amount, date);
        this.sourceAccountID = sourceAccountID;
        this.maxWithdrawalAmount = maxWithdrawalAmount;
    }

    /* This method will remove money from the source account's balance when a withdrawal is made, and also
       check to ensure that not too much money is withdrawn from the account. It will return true if the
       withdrawal is successful and false if it was not.
     */
/*    public boolean withdrawFrom(int sourceAccountID, BigDecimal amount)
    {
        boolean withdrawSuccessful = false;
        return withdrawSuccessful;
    }
*/

    public boolean availableBalanceInAccount()
    {
        //check if the withdrawal is possible, else return false
        return true;
    }

    public BigDecimal withdrawCash(BigDecimal amount)
    {
        BigDecimal dispensedAmount;
        //dispensedAmount = amount to withdraw;
        //update amount in the bank account (probably not here though);

        return dispensedAmount;
    }

    public boolean checkWithdrawalAmount()
    {
        // return true if withdrawal amount is within the withdrawal limit, else return false
        return true;
    }
}