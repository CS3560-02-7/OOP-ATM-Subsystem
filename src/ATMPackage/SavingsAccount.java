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

}