import javafx.beans.property.*;

import java.math.BigDecimal;

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

    public Account(int accountID, int memberID, String balance, String overdraftFee, String minimumBalance)
    {
        this.accountID = new SimpleIntegerProperty(accountID);
        this.memberID = new SimpleIntegerProperty(memberID);
        this.balance = new BigDecimal(balance);
        this.bal = new SimpleStringProperty(balance);
        this.overdraftFee = new BigDecimal(overdraftFee);
        this.oFee = new SimpleStringProperty(overdraftFee);
        this.minimumBalance = new BigDecimal(minimumBalance);
        this.mBal = new SimpleStringProperty(minimumBalance);
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
}