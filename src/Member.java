public class Member
{
    private int memberID;
    private int savingsAccountID;
    private int checkingAccountID;
    private int pin;
    private String firstName;
    private String lastName;
    private String address;

    public Member(int memberID, String firstName, String lastName)
    {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /*This method will check if the user inputted valid login credentials and return true if they did, and false
      if they did not. The user will be notified if they entered incorrect credentials.
     */
    public boolean logMemberIn(int possibleMemberID, int possiblePin){
        boolean isCorrectLogin = false;
        return isCorrectLogin;
    }
}
