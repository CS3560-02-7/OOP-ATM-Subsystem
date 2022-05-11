package ATMPackage;

import javafx.beans.property.SimpleStringProperty;

import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SavingsAccount extends Account
{

    private final float interestRate;

    //get withdraw limit from database?
    public SavingsAccount(int accountID, int memberID, String balance, String overdraftFee, BigDecimal withdrawLimit, BigDecimal minimumBalance, BigDecimal interestRate) throws SQLException {
        super(accountID, memberID, balance, overdraftFee, withdrawLimit, minimumBalance);
        this.mBal = new SimpleStringProperty(minimumBalance.toString());
        this.interestRate = interestRate.floatValue();
    }

    private void applyInterestRate()
    {
        this.balance=this.balance;
    }

/*    public static SavingsAccount createSavingsAccountFromDatabase(int accountID){
        SavingsAccount accountFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet accountInfo = statement.executeQuery("SELECT * FROM account WHERE accountID = " + accountID);

            if(accountInfo.next() != false) {
                accountFromDatabase = new SavingsAccount(accountInfo.getInt(1), accountInfo.getInt(2),
                        accountInfo.getString(3), accountInfo.getString(4));
            } else {
                return null;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountFromDatabase;

    }*/

/*    @Override
    public BigDecimal getDailyRemainingWithdraw()
    {
        //get todays date
        Date today = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        List<BigDecimal> bdList = new ArrayList<>();

        //get all withdrawals made today from database
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();

            //grab all withdrawls that match the given account and the current date
            ResultSet withdrawalsToday = statement.executeQuery("SELECT * FROM withdrawal WHERE dateOfTransaction = \'" + ft.format(today)+"\' AND sourceAccountID = "+this.accountID.get());
            //grab all outgoing transfers from savings
            ResultSet transferOuts = statement.executeQuery("SELECT * FROM ")

            while(withdrawalsToday.next())
            {
                BigDecimal currentAmount = new BigDecimal(withdrawalsToday.getString(3));
                bdList.add(currentAmount);
            }

        } catch (Exception e){
            System.out.println("connection not made");
        }

        //sum of all withdrawals made today by this account
        BigDecimal result = bdList.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

        if(result.floatValue()>=withdrawLimit.floatValue())
        {
            return new BigDecimal(0);
        }
        else
        {
            return withdrawLimit.subtract(result);
        }
    }*/

}