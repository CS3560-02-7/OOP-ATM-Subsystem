import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member
{
    private final int memberID;
    private int pinNumber;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private StringProperty address;

    public Member(int memberID, int pinNumber, String firstName, String lastName, String address)
    {
        this.memberID = memberID;
        this.pinNumber = pinNumber;
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);;
        this.address = new SimpleStringProperty(address);;
    }

    /*This method will attempt to log in a member and return true if the login is sucessful
     */
    public boolean logMemberIn(int possibleMemberID, int possiblePin){
        boolean loginSuccessful = false;
        //calls verifyCredentials to make sure that the members account info is valid
        
        return loginSuccessful;
    }

    /*This method will log out a member and return true if the logout is successful
     */
    public boolean logMemberOut(int possibleMemberID, int possiblePin){
        boolean logoutSuccessful = false;
        return logoutSuccessful;
    }


    /*This method will verify a members login information and return true if it is a valid combination
     */
    private boolean verifyCredentials(int possibleMemberId, int possiblePin)
    {
        boolean validCredentials = false;
        return validCredentials;
    }

    /*
    This method will require the user to re-enter their login credentials, and then will display a message saying
    that their request to change pin has been sent to the bank and they will receive their new pin in the mail
    in the next few business days.
     */
    public boolean requestChangePin(int possibleMemberId, int possiblePin)
    {
        boolean requestMade = false;
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

    // Getter methods
    public StringProperty getfirstName() {
        return firstName;
    }

    public StringProperty getlastName() {
        return lastName;
    }

    public int getmemberID() {
        return memberID;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public StringProperty getAddress(){
        return address;
    }


}
