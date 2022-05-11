import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import ATMPackage.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class transferviewController implements Initializable {

    private dbConnection dbConn;

    @FXML Button toChecking = new Button();
    @FXML Button toSavings = new Button();
    @FXML Button backc = new Button();
    @FXML Button backs = new Button();

    @FXML private TableView<Integer> tableView;
    @FXML private TableColumn<Integer, Integer> amount;
    @FXML private TableColumn<String, String> transactionID;

    public void initialize(URL url, ResourceBundle rb) {
        dbConn = new dbConnection();

        toChecking.setOnAction(event -> {
            try {
                changeScenes(2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        toSavings.setOnAction(event -> {
            try {
                changeScenes(3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        backc.setOnAction(event -> {
            try {
                changeScenes(2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        backs.setOnAction(event -> {
            try {
                changeScenes(3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void changeScenes(int sceneNum) throws IOException {

        Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/Login.fxml")));
        if (sceneNum == 2) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/CheckingAccount.fxml")));
        }
        if (sceneNum == 3) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/SavingsAccount.fxml")));
        }
        Scene scene = new Scene(page, 800, 600);
        Stage stage = Main.retStage();
        stage.setScene(scene);
        stage.show();
    }

    private void alertScene(int alerttype) throws IOException{

        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Warning");
        alertWindow.setMinWidth(250);
        alertWindow.setMinHeight(130);
        Label label = new Label();

        if(alerttype == 1) {
            label.setText("Incorrect account credentials.\nPlease try again.\n");
        }
        if(alerttype == 2) {
            label.setText("Field(s) empty");
        }

        Button close = new Button("OK\n");
        close.setOnAction(event -> alertWindow.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        Scene alert = new Scene(layout);
        alertWindow.setScene(alert);
        alertWindow.showAndWait();
    }

    public void loadValues(ActionEvent event) throws SQLException {

        ObservableList<Integer> transactionlist = FXCollections.observableArrayList();
        Connection c = dbConn.dbConnect();
        int currentHighestID = 0;

        try {
            String query = "SELECT * FROM transaction ";
            Statement statement1 = c.createStatement();
            Statement statement2 = c.createStatement();
            Statement statement3 = c.createStatement();

            ResultSet transfers = statement1.executeQuery("SELECT * FROM transfer");
            ResultSet deposits = statement2.executeQuery("SELECT * FROM deposit");
            ResultSet withdrawals = statement3.executeQuery("SELECT * FROM withdrawal");

            int transaction;

            while (transfers.next()) {
                transaction = transfers.getInt(1);
                transactionlist.add(transaction);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        transactionID.setCellValueFactory(new PropertyValueFactory<String, String>("transactionID"));
        tableView.setItems(transactionlist);

    }

    @FXML
    public void checkingTransfer(){

    }

    @FXML
    public void savingsTransfer(){

    }

    @FXML
    public void checkingHistory(){

    }

    @FXML
    public void savingsHistory(){

    }

    @FXML
    public void checkingBalance(){

    }

    @FXML
    public void savingsBalance(){

    }

}
