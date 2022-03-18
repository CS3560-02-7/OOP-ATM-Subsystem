public class SavingsAccount extends Account {
    private float withdrawLimit;
    private float interestRate;

    public SavingsAccount(int accountID, float balance, float overdraftFee, float minimumBalance,
                          float withdrawLimit, float interestRate){

        super(accountID, balance, overdraftFee, minimumBalance);
        this.withdrawLimit = withdrawLimit;
        this.interestRate = interestRate;
    }

}
