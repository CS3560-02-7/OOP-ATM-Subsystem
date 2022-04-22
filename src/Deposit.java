import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Deposit extends Transaction
{
    private final int destinationAccountID;

    public Deposit(int transactionID, String amount, String date, int destinationAccountID)
    {
        super(transactionID, amount, date);
        this.destinationAccountID = destinationAccountID;
        depositCash();
    }

    private boolean depositCash()
    {
        //this.amount is the amount to deposit to the account
        //update amount in the bank account;
        //if deposit is successful, return true, else false
        boolean depositSuccessful = false;
        return depositSuccessful;
    }

    /* Creates a new instance of deposit from database based on the given transactionID, returns null if this is not possible*/
    public static Deposit createDepositFromDatabase(int transactionID){
        Deposit depositFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet transactionInfo = statement.executeQuery("SELECT * FROM deposit WHERE transactionID = " + transactionID);

            transactionInfo.next();
            depositFromDatabase = new Deposit(transactionInfo.getInt(1), transactionInfo.getString(3),
                    transactionInfo.getString(4), transactionInfo.getInt(2));



        } catch (Exception e) {
            e.printStackTrace();
        }
        return depositFromDatabase;

    }

}