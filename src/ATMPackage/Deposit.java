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

    public Deposit(int transactionID, String amount,Date myDate, int destinationAccountID)
    {
        super(transactionID, amount, myDate);
        this.destinationAccountID = destinationAccountID;
    }

    public boolean depositCash()
    {
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


    /* This method adds a new deposit to the database based on the accountID and amount of money that the user inputted*/
    public boolean addDepositToDatabase(){
        boolean depositSuccessful = false;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            statement.execute("INSERT INTO `atm`.`deposit`\n" +
                    "(`transactionID`,\n" +
                    "`destinationAccountID`,\n" +
                    "`amount`,\n" +
                    "`dateOfTransaction`)\n" +
                    "VALUES\n" +
                    "(" + this.transactionID + ",\n" +
                    this.destinationAccountID + ",\n" +
                    this.amount + ",\n'" +
                    ft.format(this.myDate) + "');\n");

            depositSuccessful = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return depositSuccessful;
    }

}