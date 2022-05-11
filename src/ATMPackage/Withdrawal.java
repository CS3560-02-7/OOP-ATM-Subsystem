package ATMPackage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Withdrawal extends Transaction
{
    private final int sourceAccountID;

    public Withdrawal(int transactionID, String amount, Date date, int sourceAccountID)
    {
        super(transactionID, amount, date);
        this.sourceAccountID = sourceAccountID;

    }


    private boolean availableBalanceInAccount()
    {
        boolean fundsAvailable = false;
        BigDecimal accountBalance = BigDecimal.ZERO;
        //check if the withdrawal is possible, if so return true, else return false
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet accountInfo = statement.executeQuery("SELECT * FROM account WHERE accountID = " + this.sourceAccountID);

            if(accountInfo.next() == false)
                return fundsAvailable;

            accountBalance = new BigDecimal(accountInfo.getString(3));
        } catch (Exception e){
            System.out.println("connection not made");
        }

        if(this.amount.compareTo(accountBalance) < 0)
            fundsAvailable = true;

        return fundsAvailable;
    }

    public boolean withdrawCash()
    {
        //this.amount is the amount to withdraw from the account
        //update amount in the bank account;
        //if withdraw is successful, return true, else false

        BigDecimal accountBalance = new BigDecimal("0");
        boolean withdrawSuccessful = false;

        //run availableBalance to check if enough money is available
        //and run checkWithdrawalAmount to check if the withdrawal exceeds limit

        if (!availableBalanceInAccount()) {
            return withdrawSuccessful;
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet accountInfo = statement.executeQuery("SELECT * FROM account WHERE accountID = " + this.sourceAccountID);

            if(accountInfo.next() == false)
                return withdrawSuccessful;

            accountBalance = new BigDecimal(accountInfo.getString(3));
        } catch (Exception e){
            System.out.println("connection not made");
        }

        BigDecimal newAccountBalance = accountBalance.subtract(this.amount);


        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            statement.execute("UPDATE atm.account SET balance = " + newAccountBalance.setScale(2, RoundingMode.CEILING).toString() + " WHERE accountID = " + this.sourceAccountID);

            withdrawSuccessful = true;


        } catch (Exception e){
            e.printStackTrace();
        }

        return withdrawSuccessful;
    }



    /* This method adds a new withdrawal to the database based on the accountID and amount of money that the user inputted*/
    public boolean addWithdrawalToDatabase(){

        boolean withdrawSuccessful = false;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            statement.execute("INSERT INTO `atm`.`withdrawal`\n" +
                    "(`transactionID`,\n" +
                    "`sourceAccountID`,\n" +
                    "`amount`,\n" +
                    "`dateOfTransaction`)\n" +
                    "VALUES\n" +
                    "(" + this.transactionID + ",\n" +
                    this.sourceAccountID + ",\n" +
                    this.amount + ",\n'" +
                    ft.format(this.myDate) + "');\n");

            withdrawSuccessful = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return withdrawSuccessful;
    }
}