import java.util.BigDecimal;

public class Withdrawal extends Transaction {

    public Withdrawal(int transactionID, BigDecimal amount, int date, int sourceAccountID,
                      BigDecimal maxWithdrawalAmount) {

        super(transactionID, amount, date);
        this.sourceAccountID = sourceAccountID;
        this.maxWithdrawalAmount = maxWithdrawalAmount;
    }
}
