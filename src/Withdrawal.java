import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Withdrawal extends Transaction
{
    private final int sourceAccountID;
    private final BigDecimal maxWithdrawalAmount;

    public Withdrawal(int transactionID, String amount, String date, int sourceAccountID)
    {
        super(transactionID, amount, date);
        this.sourceAccountID = sourceAccountID;
        maxWithdrawalAmount = new BigDecimal("2000.00");

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

        if(checkWithdrawalAmount()){
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

    private boolean checkWithdrawalAmount()
    {
        boolean exceedsLimit = true;
        // return true if withdrawal amount is above the withdrawal limit, else return false

        if(this.amount.compareTo(maxWithdrawalAmount) < 0)
            exceedsLimit = false;

        return exceedsLimit;
    }

    /* Creates a new instance of withdrawal from database based on the given transactionID, returns null if this is not possible*/
    public static Withdrawal createWithdrawalFromDatabase(int transactionID){
        Withdrawal withdrawalFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet transactionInfo = statement.executeQuery("SELECT * FROM withdrawal WHERE transactionID = " + transactionID);

            if(transactionInfo.next() != false) {
                withdrawalFromDatabase = new Withdrawal(transactionInfo.getInt(1), transactionInfo.getString(3),
                        transactionInfo.getString(4), transactionInfo.getInt(2));
            } else {
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return withdrawalFromDatabase;

    }

    /* This method adds a new withdrawal to the database based on the accountID and amount of money that the user inputted*/
    public static boolean addWithdrawalToDatabase(int sourceID, String amount){

        boolean withdrawSuccessful = false;
        Date myDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal withdrawAmount = new BigDecimal(amount);

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            statement.execute("INSERT INTO `atm`.`withdrawal`\n" +
                    "(`transactionID`,\n" +
                    "`sourceAccountID`,\n" +
                    "`amount`,\n" +
                    "`dateOfTransaction`)\n" +
                    "VALUES\n" +
                    "(" + Transaction.getNextTransactionID() + ",\n" +
                    sourceID + ",\n" +
                    withdrawAmount + ",\n'" +
                    ft.format(myDate) + "');\n");

                    withdrawSuccessful = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return withdrawSuccessful;
    }
}