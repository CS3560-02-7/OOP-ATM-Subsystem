import java.math.BigDecimal;
public abstract class Transaction {
    protected int transactionID;
    protected BigDecimal amount;
    protected  int date;


    public Transaction(int transactionID, BigDecimal amount, int date){
        this.transactionID = transactionID;
        this.amount = amount;
        this.date = date;
    }
}
