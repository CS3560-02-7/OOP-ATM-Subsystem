import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Member
{
    private final int memberID;
    private int pin;
    private final String firstName;
    private final String lastName;
    private String address;


    public Member(int memberID, String firstName, String lastName, int pin, String address)
    {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.address = address;
    }


    /*This method will attempt to log in a member and return true if the login is successful
     */
    public boolean logMemberIn(int possibleMemberID, int possiblePin){
        //calls verifyCredentials to make sure that the members account info is valid

        boolean loginSuccessful = verifyCredentials(possibleMemberID, possiblePin);
        return loginSuccessful;
    }

    /*This method will log out a member and return true if the logout is successful
     */
    public boolean logMemberOut(int possibleMemberID, int possiblePin){
        boolean logoutSuccessful = false;
        return logoutSuccessful;
    }


    /*This method will verify a member's login information and return true if it is a valid combination
     */
    private boolean verifyCredentials(int possibleMemberId, int possiblePin)
    {
        boolean validCredentials = false;

        Member possibleMember = Member.createMemberFromDatabase(possibleMemberId);

        if(possibleMember != null && possibleMember.getMemberID() == possibleMemberId && possibleMember.getPin() == possiblePin){
            validCredentials = true;
        }

        return validCredentials;
    }

    /*
    This method will require the user to re-enter their login credentials, and then will display a message saying
    that their request to change pin has been sent to the bank and they will receive their new pin in the mail
    in the next few business days.
     */
    public boolean requestChangePin(int possibleMemberId, int possiblePin)
    {
        boolean requestMade = verifyCredentials(possibleMemberId, possiblePin);
        //run verifyCredentials, and if they are valid, popup the message that the request was made
        return requestMade;
    }

    /*
    takes in a 1 for checking account and 2 for savings account. Is called when a button on the GUI is pressed
    with the appropriate number. Based on the number will open the new correct GUI page
     */
    public void selectAccountType(int accountType)
    {

    }


    /* This method will return a new instance of the Member class with data from the database, and return null
        if there was no matching data based on the input memberID
     */
    public static Member createMemberFromDatabase(int memberID){
        Member memberFromDatabase = null;
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet memberInfo = statement.executeQuery("SELECT * FROM member WHERE memberID = " + memberID);



            if (memberInfo.next() != false) {
                memberFromDatabase = new Member(memberInfo.getInt(1), memberInfo.getString(3),
                        memberInfo.getString(4), memberInfo.getInt(2), memberInfo.getString(5));
            } else {
                return null;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberFromDatabase;

    }

    public int getMemberID(){
        return this.memberID;
    }

    public int getPin(){
        return this.pin;
    }

    public String getFullName(){
        return (this.firstName + " " + this.lastName);
    }

    public String getAddress(){
        return this.address;
    }

}
