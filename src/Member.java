public class Member
{
    private final int memberID;
    private int savingsAccountID;
    private int checkingAccountID;
    private int pin;
    private final String firstName;
    private final String lastName;
    private String address;

    public Member(int memberID, String firstName, String lastName)
    {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
