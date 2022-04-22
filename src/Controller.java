import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class Controller implements Initializable {

    @FXML private TableView<Member> tableView;
    @FXML private TableColumn<Member, Integer> memberID;
    @FXML private TableColumn<Member, Integer> pinNumber;
    @FXML private TableColumn<Member, String> firstName;
    @FXML private TableColumn<Member, String> lastName;
    @FXML private TableColumn<Member, String> address;
    @FXML private Button button;

    private dbConnection dbconn;
    ObservableList<Member> memberlist;

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
         tableView.setItems(null);
         tableView.setItems(memberlist);
    }

}

// firstName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Member, String>, ObservableValue<String>>(){
//     @Override
//     public ObservableValue<String> call(TableColumn.CellDataFeatures<Member, String> param) {
//         return param.getValue().firstNameProperty();
//     }
// });