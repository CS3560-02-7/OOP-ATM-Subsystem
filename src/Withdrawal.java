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
    public boolean withdrawFrom(int sourceAccountID, BigDecimal amount){
        boolean withdrawSuccessful = false;
        return withdrawSuccessful;
    }

}
