package ATMPackage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Member
{
    private IntegerProperty memberID;
    private StringProperty firstName;
    private StringProperty lastName;
    private int pinNumber;
    private String address;

    public Member(int memberID, String firstName, String lastName, int pinNumber, String address) {
        this.memberID = new SimpleIntegerProperty(memberID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.pinNumber = pinNumber;
        this.address = address;
    }

    public boolean logMemberIn(int possibleMemberID, int possiblePin){
        //check if that account exists for that address
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");
            Statement statement = connection.createStatement();
            //grab all withdrawals that match the given account and the current date
            ResultSet memberInfo = statement.executeQuery("SELECT * FROM member WHERE memberID = " + possibleMemberID +" AND pinNumber = "+possiblePin);
            if (memberInfo.next() != false) {
                this.memberID = new SimpleIntegerProperty(memberInfo.getInt(1));
                return true;
            }
            else
            {
                return false;
            }
        } catch (Exception e){
            System.out.println("connection not made");
        }
        return false;
    }

    /*This method will log out a member
     */
    public void logMemberOut(){
        this.memberID = new SimpleIntegerProperty(0);
        this.firstName = new SimpleStringProperty("");
        this.lastName = new SimpleStringProperty("");
        this.pinNumber = 0;
        this.address = "";
    }


    /*
    This method will require the user to re-enter their login credentials, and then will display a message saying
    that their request to change pin has been sent to the bank and they will receive their new pin in the mail
    in the next few business days.
     */
    public boolean requestChangePin(int possiblePin) {
        //check if that account exists for that address
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");
            Statement statement = connection.createStatement();
            //grab all withdrawals that match the given account and the current date
            ResultSet memberInfo = statement.executeQuery("SELECT * FROM member WHERE memberID = " + this.memberID +" AND pinNumber = "+possiblePin);
            if (memberInfo.next() != false) {
                this.memberID = new SimpleIntegerProperty(memberInfo.getInt(1));
                return true;
            }
            else
            {
                return false;
            }

        } catch (Exception e){
            System.out.println("connection not made");
        }
        return false;
    }

    /*
    Get checking account for this member
     */
    public CheckingAccount getCheckingAccount() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");
            Statement statement = connection.createStatement();
            //grab the account for this member
            //SimpleStringProperty accountType = new SimpleStringProperty("\'checking\'");
            ResultSet accountInfo = statement.executeQuery("SELECT * FROM account WHERE accountType = \"checking\" AND memberID = "+this.memberID.getValue().intValue());
            if(accountInfo.next() != false) {
                CheckingAccount myCheckingAccount = new CheckingAccount(accountInfo.getInt(1), accountInfo.getInt(2),
                        accountInfo.getString(3), accountInfo.getString(4));
                return myCheckingAccount;
            } else {
                return null;
            }
        } catch (Exception e){
            System.out.println("connection not made");
        }
        return null;
    }

    /*
    Get savings account for this member
     */
    public SavingsAccount getSavingsAccount() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");
            Statement statement = connection.createStatement();
            //grab the account for this member
            ResultSet accountInfo = statement.executeQuery("SELECT * FROM account WHERE accountType = \"savings\" AND memberID = "+this.memberID.getValue().intValue());
            if(accountInfo.next() != false) {
                SavingsAccount mySavingsAccount = new SavingsAccount(accountInfo.getInt(1), accountInfo.getInt(2),
                        accountInfo.getString(3), accountInfo.getString(4));
                return mySavingsAccount;
            } else {
                return null;
            }
        } catch (Exception e){
            System.out.println("connection not made");
        }
        return null;
    }

    /* This method will return a new instance of the ATMPackage.Member class with data from the database, and return null
        if there was no matching data based on the input memberID
     */
    public static Member createMemberFromDatabase(int memberID){
        Member memberFromDatabase = new Member(0,"","",0,"");
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");
            Statement statement = connection.createStatement();
            ResultSet memberInfo = statement.executeQuery("SELECT * FROM member WHERE memberID = " + memberID);
            memberInfo.next();
            memberFromDatabase = new Member(memberInfo.getInt(1),memberInfo.getString(3),
                    memberInfo.getString(4), memberInfo.getInt(2), memberInfo.getString(5));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberFromDatabase;
    }

    public boolean forgotPin(int possibleID, String possibleAddress)
    {
        //check if that account exists for that address
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");
            Statement statement = connection.createStatement();
            //grab all withdrawals that match the given account and the current date
            ResultSet memberInfo = statement.executeQuery("SELECT * FROM member WHERE memberID = " + possibleID +" AND address = \'"+possibleAddress+"\'");
            if (memberInfo.next() != false) {
                this.memberID = new SimpleIntegerProperty(memberInfo.getInt(1));
                return true;
            }
            else
            {
                return false;
            }

        } catch (Exception e){
            System.out.println("connection not made");
        }
        return false;
    }

    // Getter methods
    public StringProperty getfirstName() {
        return firstName;
    }

    public int getMemberID() {
        return memberID.getValue();
    }

    public String getlastName() {
        return lastName.toString();
    }

    public int getPin() {
        return pinNumber;
    }

    public String getAddress(){
        return address;
    }

    public StringProperty firstNameProperty() {
        if (firstName == null) {
            firstName = new SimpleStringProperty(this, "firstName");
        }
        return firstName;
    }

    public StringProperty lastNameProperty() {
        if (lastName == null) {
            lastName = new SimpleStringProperty(this, "lastName");
        }
        return lastName;
    }

    public IntegerProperty memberIDProperty() {
        if (memberID == null) {
            memberID = new SimpleIntegerProperty(this, "memberID");
        }
        return memberID;
    }
}
