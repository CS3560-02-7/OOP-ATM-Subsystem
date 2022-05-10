package ATMPackage;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Transaction
{
    protected int transactionID;
    protected BigDecimal amount;
    protected String date;


    public Transaction(int transactionID, String amount, String date)
    {
        this.transactionID = transactionID;
        this.amount =  new BigDecimal(amount);
        this.date = date;
    }

    public BigDecimal getAmount(){
        return this.amount;
    }


    /* This method checks every existing transaction in the database and finds the largest transactionID, it then returns the largest
        transactionID plus one for a new transaction that is going to be added to the database */

    public static int getNextTransactionID() {

        int currentHighestID = 0;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();

            ResultSet transfers = statement1.executeQuery("SELECT * FROM transfer");
            ResultSet deposits = statement2.executeQuery("SELECT * FROM deposit");
            ResultSet withdrawals = statement3.executeQuery("SELECT * FROM withdrawal");

            while(transfers.next()){
                if(transfers.getInt(1) > currentHighestID)
                    currentHighestID = transfers.getInt(1);
            }

            while(deposits.next()){
                if(deposits.getInt(1) > currentHighestID)
                    currentHighestID = deposits.getInt(1);
            }

            while(withdrawals.next()){
                if(withdrawals.getInt(1) > currentHighestID)
                    currentHighestID = withdrawals.getInt(1);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

        return currentHighestID + 1;

    }
}