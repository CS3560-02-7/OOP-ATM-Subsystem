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
        this.amount = amount;
        withdrawCash();
    }


    private boolean availableBalanceInAccount()
    {
        boolean fundsAvailable = false;
        //check if the withdrawal is possible, else return false
        return fundsAvailable;
    }

    private boolean withdrawCash()
    {
        //this.amount is the amount to withdraw from the account
        //update amount in the bank account;
        //if withdraw is succesful, return true, else false
        boolean withdrawSuccessful = false;
        //run availableBalance to check if enough money is available
        //and run checkwithdrawalAmount to check if the withdrawal exceeds limit

        return withdrawSuccessful;
    }

    private boolean checkWithdrawalAmount()
    {
        boolean exceedsLimit = true;
        // return true if withdrawal amount is above the withdrawal limit, else return false
        return exceedsLimit;
    }
}