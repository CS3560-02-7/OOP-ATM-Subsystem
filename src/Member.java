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

    /*This method will verify a members login information and return true if it is a valid combination
     */
    private boolean verifyCredentials(int possibleMemberId, int possiblePin)
    {
        boolean validCredentials = false;
        return validCredentials;
    }
}
