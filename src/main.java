import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main
{
    public static void main(String[] args)
    {

        Withdrawal myWithdrawal = new Withdrawal(7, "100.00", "2022-05-07", 12345678);
        System.out.println(myWithdrawal.withdrawCash());



    }
}