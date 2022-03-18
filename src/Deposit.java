import java.math.BigDecimal;

public class Deposit extends Transaction {
    private int destinationAccountID;

    public Deposit(int transactionID, BigDecimal amount, int date, int destinationAccountID){

        super(transactionID, amount, date);
        this.destinationAccountID = destinationAccountID;
    }

}