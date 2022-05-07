import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckingAccount extends Account
{
    private final BigDecimal monthlyFee;

    public CheckingAccount(int accountID, int memberID, String balance, String overdraftFee)
    {
        super(accountID, memberID, balance, overdraftFee);
        this.minimumBalance = new BigDecimal("50.00");
        this.monthlyFee = new BigDecimal("10.00");
    }

    /*
    every month this method will be run and will deduct monthlyFee from balance
     */
    private void deductMontlyFee()
    {
        this.balance = this.balance.subtract(monthlyFee);
    }

    /* create a new checking account instance from the database using acocuntID, returns null if not possible*/
    public static CheckingAccount createCheckingAccountFromDatabase(int accountID){
        CheckingAccount accountFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet accountInfo = statement.executeQuery("SELECT * FROM account WHERE accountID = " + accountID);


            if(accountInfo.next() != false) {
                accountFromDatabase = new CheckingAccount(accountInfo.getInt(1), accountInfo.getInt(2),
                        accountInfo.getString(3), accountInfo.getString(4));
            } else {
                return null;
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountFromDatabase;

    }
}