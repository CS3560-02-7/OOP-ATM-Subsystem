import java.math.BigDecimal;

public abstract class Transaction
{
    protected int transactionID;
    protected BigDecimal amount;
    protected String date;


    public Transaction(int transactionID, String amount, String date)
    {
        this.transactionID = transactionID;
        this.amount =  new BigDecimal(amount);
        this.date = date;
    }

    public BigDecimal getAmount(){
        return this.amount;
    }
}
