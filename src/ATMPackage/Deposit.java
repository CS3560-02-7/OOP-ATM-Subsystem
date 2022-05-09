package ATMPackage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deposit extends Transaction
{
    private final int destinationAccountID;

    public Deposit(int transactionID, String amount, String date, int destinationAccountID)
    {
        super(transactionID, amount, date);
        this.destinationAccountID = destinationAccountID;
    }

    public boolean depositCash()
    {
        //this.amount is the amount to deposit to the account
        //update amount in the bank account;
        //if deposit is successful, return true, else false
        boolean depositSuccessful = false;
        BigDecimal accountBalance = new BigDecimal("0");

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet accountInfo = statement.executeQuery("SELECT * FROM account WHERE accountID = " + this.destinationAccountID);

            if(accountInfo.next() == false)
                return depositSuccessful;

            accountBalance = new BigDecimal(accountInfo.getString(3));
        } catch (Exception e){
            System.out.println("connection not made");
        }

        BigDecimal newAccountBalance = accountBalance.add(this.amount);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            statement.execute("UPDATE atm.account SET balance = " + newAccountBalance.setScale(2, RoundingMode.CEILING).toString() + " WHERE accountID = " + this.destinationAccountID);

            depositSuccessful = true;


        } catch (Exception e){
            e.printStackTrace();
        }

        return depositSuccessful;
    }

    /* Creates a new instance of deposit from database based on the given transactionID, returns null if this is not possible*/
    public static Deposit createDepositFromDatabase(int transactionID){
        Deposit depositFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet transactionInfo = statement.executeQuery("SELECT * FROM deposit WHERE transactionID = " + transactionID);


            if(transactionInfo.next() != false) {
                depositFromDatabase = new Deposit(transactionInfo.getInt(1), transactionInfo.getString(3),
                        transactionInfo.getString(4), transactionInfo.getInt(2));
            } else {
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return depositFromDatabase;

    }

    /* This method adds a new deposit to the database based on the accountID and amount of money that the user inputted*/
    public static boolean addDepositToDatabase(int destinationID, String amount){

        boolean depositSuccessful = false;
        Date myDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal depositAmount = new BigDecimal(amount);

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            statement.execute("INSERT INTO `atm`.`deposit`\n" +
                    "(`transactionID`,\n" +
                    "`destinationAccountID`,\n" +
                    "`amount`,\n" +
                    "`dateOfTransaction`)\n" +
                    "VALUES\n" +
                    "(" + Transaction.getNextTransactionID() + ",\n" +
                    destinationID + ",\n" +
                    depositAmount + ",\n'" +
                    ft.format(myDate) + "');\n");

            depositSuccessful = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return depositSuccessful;
    }

}