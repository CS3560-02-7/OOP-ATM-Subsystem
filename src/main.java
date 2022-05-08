import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main
{
    public static void main(String[] args)
    {

        Transfer myTransfer = new Transfer(7, "10", "today", 12345678, 23456789);
        System.out.println(myTransfer.transferCash());





    }
}