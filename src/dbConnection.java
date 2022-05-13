import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import ATMPackage.*;

public class dbConnection
{
    public Connection connection;

    public Connection dbConnect(){
        String user = "root";
        String pass = "Sjkh83lasd87ds0por7Gjjd6l4";
        String url = "jdbc:mysql://localhost:3306/atm";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

}
