import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member
{
    private IntegerProperty memberID;
    private int pinNumber;
    private StringProperty firstName;
    private StringProperty lastName;
    private String address;

    public Member(String firstName, int pinNumber, String lastName, int memberID, String address)
    {
        this.firstName = new SimpleStringProperty(firstName);
        this.pinNumber = pinNumber;
        this.lastName = new SimpleStringProperty(lastName);
        this.memberID = new SimpleIntegerProperty(memberID);
        this.address = address;
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

    public IntegerProperty getmemberID() {
        return memberID;
    }

    public int getPinNumber() {
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
