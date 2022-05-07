import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Transfer extends Transaction {
    private final int destinationAccountID;
    private final int sourceAccountID;

    public Transfer(int transactionID, String amount, String date, int destinationAccountID, int sourceAccountID) {
        super(transactionID, amount, date);
        this.destinationAccountID = destinationAccountID;
        this.sourceAccountID = sourceAccountID;
        transferCash();
    }

    private boolean transferCash() {
        //this.amount is the amount to transfer to the destinationAccount
        //update amount in the bank accounts;
        //if transfer is successful, return true, else false
        boolean transferSuccessful = false;
        //run availableBalanceInSourceAccount to check if there is enough money in the source account to attempt transfer
        return transferSuccessful;
    }

    private boolean availableBalanceInSourceAccount()
    {
        boolean fundsAvailable = false;
        //check if the transfer is possible, else return false
        return fundsAvailable;
    }

    /* Creates an instance of Transfer with data from the database, returns null if this is not possible */
    public static Transfer createTransferFromDatabase(int transactionID){
        Transfer transferFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();

            ResultSet transactionInfo = statement1.executeQuery("SELECT * FROM transfer WHERE transactionID = " + transactionID);

            ResultSet destinationInfo = statement2.executeQuery("SELECT * FROM transferdestinationaccount WHERE transactionID = " + transactionID);

            ResultSet sourceInfo = statement3.executeQuery("SELECT * FROM transfersourceaccount WHERE transactionID = " + transactionID);

            if(transactionInfo.next() != false && destinationInfo.next() != false && sourceInfo.next() != false) {
                transferFromDatabase = new Transfer(transactionInfo.getInt(1), transactionInfo.getString(2),
                        transactionInfo.getString(3), destinationInfo.getInt(2), sourceInfo.getInt(2));
            } else {
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return transferFromDatabase;

    }
}