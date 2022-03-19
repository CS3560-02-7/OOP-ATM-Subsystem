import java.math.BigDecimal;

public class Transfer extends Transaction {
        private int destinationAccountID;
        private int sourceAccountID;

        public Transfer(BigDecimal amount, int transactionID, int date, int destinationAccountID, int sourceAccountID) {
            super(amount, transactionID, date);

            this.destinationAccountID = destinationAccountID;
            this.sourceAccountID = sourceAccountID;
        }
}