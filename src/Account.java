public abstract class Account {
    protected int accountID;
    protected float balance;
    protected float overdraftFee;
    protected float minimumBalance;

    public Acount(int accountID, float balance, float overdraftFee, float minimumBalance){
        this.accountID = accountID;
        this.balance = balance;
        this.overdraftFee = overdraftFee;
        this.minimumBalance = minimumBalance;
    }
}
