import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SavingsAccount extends Account
{
    private final BigDecimal withdrawLimit;
    private final float interestRate;

    public SavingsAccount(int accountID, int memberID, String balance, String overdraftFee, String minimumBalance)
    {
        super(accountID, memberID, balance, overdraftFee, minimumBalance);
        this.withdrawLimit = new BigDecimal("2000.00");
        this.interestRate = (float) 0.06;
    }

    private void applyInterestRate()
    {
        this.balance=this.balance;
    }

    public static SavingsAccount createSavingsAccountFromDatabase(int accountID){
        SavingsAccount accountFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet accountInfo = statement.executeQuery("SELECT * FROM account WHERE accountID = " + accountID);

            if(accountInfo.next() != false) {
                accountFromDatabase = new SavingsAccount(accountInfo.getInt(1), accountInfo.getInt(2),
                        accountInfo.getString(3), accountInfo.getString(4), accountInfo.getString(5));
            } else {
                return null;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountFromDatabase;

    }


}