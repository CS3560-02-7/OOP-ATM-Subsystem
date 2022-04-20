import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main
{
    public static void main(String[] args)
    {
        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm", "root", "Sjkh83lasd87ds0por7Gjjd6l4");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM member");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}