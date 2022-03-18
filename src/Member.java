public class Member {
    private int memberID;
    private int savingsAccountID;
    private int checkingAccountID;
    private int PIN;
    private String firstName;
    private String lastName;
    private String address;

    public Member(int memberID, String firstName, String lastName){
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
