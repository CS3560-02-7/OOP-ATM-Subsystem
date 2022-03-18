import java.math.BigDecimal

public class CheckingAccount extends Account {
    private BigDecimal monthlyFee;

    public CheckingAccount(int accountID, String balance, String overdraftFee, String minimumBalance,
                           String monthlyfee){
        super(accountID, balance, overdraftFee, minimumBalance);
        this.monthlyFee = new BigDecimal(monthlyfee);
    }
}