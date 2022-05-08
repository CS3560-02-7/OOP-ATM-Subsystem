import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transfer extends Transaction {
    private final int destinationAccountID;
    private final int sourceAccountID;

    public Transfer(int transactionID, String amount, String date, int destinationAccountID, int sourceAccountID) {
        super(transactionID, amount, date);
        this.destinationAccountID = destinationAccountID;
        this.sourceAccountID = sourceAccountID;
    }

    public boolean transferCash() {
        //this.amount is the amount to transfer to the destinationAccount
        //update amount in the bank accounts;
        //if transfer is successful, return true, else false
        boolean transferSuccessful = false;
        //run availableBalanceInSourceAccount to check if there is enough money in the source account to attempt transfer

        BigDecimal sourceBalance = new BigDecimal("0");
        BigDecimal destinationBalance = new BigDecimal("0");

       if(!availableBalanceInSourceAccount())
           return transferSuccessful;

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();

            ResultSet destinationAccountInfo = statement1.executeQuery("SELECT * FROM account WHERE accountID = " + this.destinationAccountID);
            ResultSet sourceAccountInfo = statement2.executeQuery("SELECT * FROM account WHERE accountID = " + this.sourceAccountID);

            if(destinationAccountInfo.next() == false)
                return transferSuccessful;

            destinationBalance = new BigDecimal(destinationAccountInfo.getString(3));

            if(sourceAccountInfo.next() == false)
                return transferSuccessful;

            sourceBalance = new BigDecimal(sourceAccountInfo.getString(3));


        } catch (Exception e){
            System.out.println("connection not made");
        }

        BigDecimal newDestinationAccountBalance = destinationBalance.add(amount);
        BigDecimal newSourceAccountBalance = sourceBalance.subtract(amount);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();

            statement1.execute("UPDATE atm.account SET balance = " + newDestinationAccountBalance.setScale(2, RoundingMode.CEILING).toString() + " WHERE accountID = " + this.destinationAccountID);
            statement2.execute("UPDATE atm.account SET balance = " + newSourceAccountBalance.setScale(2, RoundingMode.CEILING).toString() + " WHERE accountID = " + this.sourceAccountID);

            transferSuccessful = true;


        } catch (Exception e){
            e.printStackTrace();
        }

        return transferSuccessful;
    }

    private boolean availableBalanceInSourceAccount()
    {
        boolean fundsAvailable = false;
        BigDecimal accountBalance = new BigDecimal("0");
        //check if the transfer is possible, else return false

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement1 = connection.createStatement();

            ResultSet accountInfo = statement1.executeQuery("SELECT * FROM account WHERE accountID = " + this.sourceAccountID);

            if(accountInfo.next() == false)
                return fundsAvailable;

            accountBalance = new BigDecimal(accountInfo.getString(3));

        } catch (Exception e){
            System.out.println("connection not made");
        }

        if (accountBalance.compareTo(amount) > 0)
            fundsAvailable = true;

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

    /* This method adds a new transfer to the database based on the accountIDs and amount of money that the user inputted. It also
        creates new entries for transferDestinationAccount and transferSourceAccount*/
    public static boolean addTransferToDatabase(int sourceID, int destinationID, String amount){

        boolean transferSuccessful = false;
        Date myDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        BigDecimal transferAmount = new BigDecimal(amount);
        int newTransactionID = Transaction.getNextTransactionID();

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();

            statement1.execute("INSERT INTO `atm`.`transfer`\n" +
                    "(`transactionID`,\n" +
                    "`amount`,\n" +
                    "`dateOfTransaction`)\n" +
                    "VALUES\n" +
                    "(" + newTransactionID + ",\n" +
                    transferAmount + ",\n'" +
                    ft.format(myDate) + "');\n");

            statement2.execute("INSERT INTO `atm`.`transfersourceaccount`\n" +
                    "(`transactionID`,\n" +
                    "`sourceAccountID`)\n" +
                    "VALUES\n" +
                    "(" + newTransactionID + ",\n" +
                    sourceID + ");\n");

            statement3.execute("INSERT INTO `atm`.`transferdestinationaccount`\n" +
                    "(`transactionID`,\n" +
                    "`destinationAccountID`)\n" +
                    "VALUES\n" +
                    "(" + newTransactionID + ",\n" +
                    destinationID + ");\n");



            transferSuccessful = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return transferSuccessful;
    }

}