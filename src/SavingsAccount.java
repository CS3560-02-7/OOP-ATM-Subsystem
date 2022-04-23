import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SavingsAccount extends Account
{
    private final BigDecimal withdrawLimit;
    private final float interestRate;

    public SavingsAccount(int accountID, int memberID, String balance, String overdraftFee, String minimumBalance,
                          String withdrawLimit, float interestRate)
    {
        super(accountID, memberID, balance, overdraftFee, minimumBalance);
        this.withdrawLimit = new BigDecimal(withdrawLimit);
        this.interestRate = interestRate;
    }

    /*
    Every year apply the interest rate to the balance and increase it.

    FOR STUDENTS:
    see this link on how to calculate interest rate with BigDecimal
    https://stackoverflow.com/questions/9188887/bigdecimal-class-java
     */
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

            accountInfo.next();
            accountFromDatabase = new SavingsAccount(accountInfo.getInt(1), accountInfo.getInt(2),
                    accountInfo.getString(3), accountInfo.getString(4), accountInfo.getString(5),
                    accountInfo.getString(6), accountInfo.getFloat(7));



        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountFromDatabase;

    }


}
