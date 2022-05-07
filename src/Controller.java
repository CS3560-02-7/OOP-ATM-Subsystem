import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Controller implements Initializable {

    // Variables for member
    @FXML private TableView<Member> tableView;
    @FXML private TableColumn<Member, Integer> memberID;
    @FXML private TableColumn<Member, Integer> pinNumber;
    @FXML private TableColumn<Member, String> firstName;
    @FXML private TableColumn<Member, String> lastName;
    @FXML private TableColumn<Member, String> address;

    // Variables for Account
    @FXML private TableView<Account> accView;
    @FXML private TableColumn<Account, Integer> accountID;
    @FXML private TableColumn<Account, Integer> accmemberID;
    @FXML private TableColumn<Account, String> balance;
    @FXML private TableColumn<Account, String> overdraftFee;
    @FXML private TableColumn<Account, String> minimumBalance;

    // Buttons
    @FXML private Button button;
    @FXML private Button button2;
    @FXML private Button button3;

    private dbConnection dbconn;
    ObservableList<Member> memberlist;
    ObservableList<Account> accountlist;

    public void initialize(URL url, ResourceBundle rb) {
        dbconn = new dbConnection();
    }

    public void loadValues(ActionEvent event) throws SQLException {
        memberlist = FXCollections.observableArrayList();
        Connection c = dbconn.dbConnect();

        try{
            String query = "SELECT * FROM member";
            Statement statement = c.createStatement();;
            ResultSet resultSet = statement.executeQuery(query);;
            Member mem;

            while (resultSet.next()) {
                mem = new Member(resultSet.getInt("memberID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getInt("pinNumber"),
                        resultSet.getString("address"));
                memberlist.add(mem);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

         firstName.setCellValueFactory(new PropertyValueFactory<Member, String>("firstName"));
         pinNumber.setCellValueFactory(new PropertyValueFactory<Member, Integer>("pinNumber"));
         lastName.setCellValueFactory(new PropertyValueFactory<Member, String>("lastName"));
         memberID.setCellValueFactory(new PropertyValueFactory<Member, Integer>("memberID"));
         address.setCellValueFactory(new PropertyValueFactory<Member, String>("address"));
         tableView.setItems(memberlist);
    }

    public void loadScene(ActionEvent event) throws IOException {
        changeScenes();
    }

    public void loadAccounts(ActionEvent event) throws SQLException, IOException {
        accountlist = FXCollections.observableArrayList();
        Connection c = dbconn.dbConnect();

        try{
            String query = "SELECT * FROM account";
            Statement statement = c.createStatement();;
            ResultSet resultSet = statement.executeQuery(query);;
            Account acc;

            while (resultSet.next()) {
                acc = new Account(resultSet.getInt("accountID"),
                        resultSet.getInt("memberID"),
                        resultSet.getString("balance"),
                        resultSet.getString("overdraftFee"),
                        resultSet.getString("minimumBalance")) {
                };
                accountlist.add(acc);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        accountID.setCellValueFactory(new PropertyValueFactory<Account, Integer>("accountID"));
        accmemberID.setCellValueFactory(new PropertyValueFactory<Account, Integer>("memberID"));
        balance.setCellValueFactory(new PropertyValueFactory<Account, String>("balance"));
        overdraftFee.setCellValueFactory(new PropertyValueFactory<Account, String>("overdraftFee"));
        minimumBalance.setCellValueFactory(new PropertyValueFactory<Account, String>("minimumBalance"));
        accView.setItems(accountlist);

    }

    private void changeScenes() throws IOException {
        Parent accounts = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/DisplayAccounts.fxml")));
        Scene scene1 = new Scene(accounts, 800, 600);
        Stage stage = Main.retStage();
        stage.setScene(scene1);
        stage.show();
    }

}
