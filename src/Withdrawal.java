import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Withdrawal extends Transaction
{
    private final int sourceAccountID;
    private final BigDecimal maxWithdrawalAmount;
    private int desiredBills;

    public Withdrawal(int transactionID, String amount, String date, int sourceAccountID, int desiredBills)
    {
        super(transactionID, amount, date);
        this.sourceAccountID = sourceAccountID;
        withdrawCash();
        maxWithdrawalAmount = new BigDecimal("1000.00");
        this.desiredBills = desiredBills;
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

    /* Creates a new instance of withdrawal from database based on the given transactionID, returns null if this is not possible*/
    public static Withdrawal createWithdrawalFromDatabase(int transactionID){
        Withdrawal withdrawalFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet transactionInfo = statement.executeQuery("SELECT * FROM withdrawal WHERE transactionID = " + transactionID);

            transactionInfo.next();
            withdrawalFromDatabase = new Withdrawal(transactionInfo.getInt(1), transactionInfo.getString(3),
                    transactionInfo.getString(4), transactionInfo.getInt(2), transactionInfo.getInt(5));



        } catch (Exception e) {
            e.printStackTrace();
        }
        return withdrawalFromDatabase;

    }
}