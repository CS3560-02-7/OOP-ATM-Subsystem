package ATMPackage;

import javafx.beans.property.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Account
{
    protected IntegerProperty accountID;
    protected IntegerProperty memberID;
    protected BigDecimal balance;
    protected StringProperty bal;
    protected BigDecimal overdraftFee;
    protected StringProperty oFee;
    protected BigDecimal minimumBalance;
    protected StringProperty mBal;
    protected final BigDecimal withdrawLimit;

    public Account(int accountID, int memberID, String balance, String overdraftFee, BigDecimal withdrawLimit, BigDecimal minimumBalance)
    {
        this.accountID = new SimpleIntegerProperty(accountID);
        this.memberID = new SimpleIntegerProperty(memberID);
        this.balance = new BigDecimal(balance);
        this.bal = new SimpleStringProperty(balance);
        this.overdraftFee = new BigDecimal(overdraftFee);
        this.oFee = new SimpleStringProperty(overdraftFee);
        this.withdrawLimit = withdrawLimit;
    }

    /*
    This method will gather the transactions for this particular account and display them in the GUI.
     */
    protected void showTransactionHistory()
    {

    }

    public IntegerProperty getaccountID() {
        return accountID;
    }

    public IntegerProperty getmemberID() {
        return memberID;
    }

    public BigDecimal getoverdraftFee() {
        return overdraftFee;
    }
    public BigDecimal getminBalance() {
        return minimumBalance;
    }
    protected BigDecimal getBalance(){
        return this.balance;
    }

    public IntegerProperty accountIDProperty() {
        if (accountID == null) {
            accountID = new SimpleIntegerProperty(this, "accountID");
        }
        return accountID;
    }
    public IntegerProperty memberIDProperty() {
        if (memberID == null) {
            memberID = new SimpleIntegerProperty(this, "memberID");
        }
        return memberID;
    }

   public StringProperty balanceProperty(){
       if (balance == null) {
           bal = new SimpleStringProperty(bal.toString());
       }
       return bal;
   }
    public StringProperty overdraftFeeProperty(){
        if (overdraftFee == null) {
            oFee = new SimpleStringProperty(oFee.toString());
        }
        return oFee;
    }
    public StringProperty minimumBalanceProperty(){
        if (minimumBalance == null) {
            mBal = new SimpleStringProperty(mBal.toString());
        }
        return mBal;
    }

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

            //grab all withdrawls that match the given account and the current date
            ResultSet withdrawalsToday = statement.executeQuery("SELECT * FROM withdrawal WHERE dateOfTransaction = \'" + ft.format(today)+"\' AND sourceAccountID = "+this.accountID.get());

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
    }
}