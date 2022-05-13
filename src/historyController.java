import ATMPackage.Member;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import ATMPackage.*;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

public class historyController implements Initializable {
    private static CheckingAccount myCheckingAccount;
    private static SavingsAccount mySavingsAccount;
    @FXML Button backButtonSavings = new Button();
    @FXML Button backButton = new Button();
    private dbConnection dbConn;

    @FXML Label savingsCurrentBal = new Label();
    @FXML Label checkingCurrentBal = new Label();

    @FXML private Tab checkingDeposit;
    @FXML private Tab checkingWithdrawal;
    @FXML private Tab checkingIncomingTransfers;
    @FXML private Tab checkingOutgoingTransfers;

    @FXML private TableColumn<ObservableList, String> dTransactionID;
    @FXML private TableColumn<ObservableList, String> dAmount;
    @FXML private TableColumn<ObservableList, String> dDate;
    @FXML private TableView<ObservableList> depositTableView;


    @FXML private TableColumn<ObservableList, String> wTransactionID;
    @FXML private TableColumn<ObservableList, String> wAmount;
    @FXML private TableColumn<ObservableList, String> wDate;
    @FXML private TableView<ObservableList> withdrawTableView;

    @FXML private TableColumn<ObservableList, String> oTransactionID;
    @FXML private TableColumn<ObservableList, String> oAmount;
    @FXML private TableColumn<ObservableList, String> oDate;
    @FXML private TableColumn<ObservableList, String> destination;
    @FXML private TableView<ObservableList> outTableView;

    @FXML private TableColumn<ObservableList, String> iTransactionID;
    @FXML private TableColumn<ObservableList, String> iAmount;
    @FXML private TableColumn<ObservableList, String> iDate;
    @FXML private TableColumn<ObservableList, String> source;
    @FXML private TableView<ObservableList> inTableView;

    public void initialize(URL url, ResourceBundle rb) {
        dbConn = new dbConnection();
        myCheckingAccount = Controller.retChecking();
        mySavingsAccount = Controller.retSavings();


        if(!Objects.isNull(mySavingsAccount))
        {
            mySavingsAccount = Controller.getMember().getSavingsAccount();
            savingsCurrentBal.setText("$"+mySavingsAccount.balanceProperty().get());
            try {
                displayINSavingHistory();
                displayDepositSavingHistory();
                displayOutSavingHistory();
                displayWithdrawSavingHistory();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(!Objects.isNull(myCheckingAccount))
        {
            myCheckingAccount = Controller.getMember().getCheckingAccount();
            checkingCurrentBal.setText("$"+myCheckingAccount.balanceProperty().get());
            try {
                displayDepositCheckingHistory();
                displayWithdrawCheckingHistory();
                displayOutCheckingHistory();
                displayINCheckingHistory();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        backButton.setOnAction(event -> {
            try {
                changeScenes(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButtonSavings.setOnAction(event -> {
            try {
                changeScenes(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void changeScenes(int sceneNum) throws IOException {

        Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/Login.fxml")));
        if (sceneNum == 0) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/CheckingAccount.fxml")));
        }
        if (sceneNum == 1) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/SavingsAccount.fxml")));
        }

        Scene scene = new Scene(page, 800, 600);
        Stage stage = Main.retStage();
        stage.setScene(scene);
        stage.show();
    }


    private void displayDepositSavingHistory() throws SQLException {
        ObservableList<ObservableList> rows = FXCollections.observableArrayList();
        ResultSet a = mySavingsAccount.getDeposits();
        while (a.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= a.getMetaData().getColumnCount(); i++) {
                row.add(a.getString(i));


            }

            rows.add(row);

        }
        dTransactionID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(0).toString()));
        dAmount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(1).toString()));
        dDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(2).toString()));
        depositTableView.getItems().addAll(rows);

    }

    private void displayWithdrawSavingHistory() throws SQLException
    {
        ObservableList<ObservableList> rows2 = FXCollections.observableArrayList();
        ResultSet a = mySavingsAccount.getWithdrawals();
        while (a.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= a.getMetaData().getColumnCount(); i++) {
                row.add(a.getString(i));
            }
            rows2.add(row);
        }
        wTransactionID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(0).toString()));
        wAmount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(1).toString()));
        wDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(2).toString()));
        withdrawTableView.getItems().addAll(rows2);
    }

    private void displayOutSavingHistory() throws SQLException
    {
        ObservableList<ObservableList> rows2 = FXCollections.observableArrayList();
        ResultSet a = mySavingsAccount.getAllOutgoingTransfers();
        while (a.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= a.getMetaData().getColumnCount(); i++) {
                row.add(a.getString(i));
            }
            rows2.add(row);
        }
        oTransactionID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(0).toString()));
        oAmount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(1).toString()));
        oDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(2).toString()));
        destination.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(3).toString()));
        outTableView.getItems().addAll(rows2);
    }

    private void displayINSavingHistory() throws SQLException
    {
        ObservableList<ObservableList> rows2 = FXCollections.observableArrayList();
        ResultSet a = mySavingsAccount.getAllIncomingTranfsers();
        while (a.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= a.getMetaData().getColumnCount(); i++) {
                row.add(a.getString(i));
            }
            rows2.add(row);
        }
        iTransactionID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(0).toString()));
        iAmount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(1).toString()));
        iDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(2).toString()));
        source.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(3).toString()));
        inTableView.getItems().addAll(rows2);
    }

    private void displayDepositCheckingHistory() throws SQLException {
        ObservableList<ObservableList> rows = FXCollections.observableArrayList();
        ResultSet a = myCheckingAccount.getDeposits();
        while (a.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= a.getMetaData().getColumnCount(); i++) {
                row.add(a.getString(i));


            }

            rows.add(row);

        }
        dTransactionID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(0).toString()));
        dAmount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(1).toString()));
        dDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(2).toString()));
        depositTableView.getItems().addAll(rows);

    }

    private void displayWithdrawCheckingHistory() throws SQLException
    {
        ObservableList<ObservableList> rows2 = FXCollections.observableArrayList();
        ResultSet a = myCheckingAccount.getWithdrawals();
        while (a.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= a.getMetaData().getColumnCount(); i++) {
                row.add(a.getString(i));
            }
            rows2.add(row);
        }
        wTransactionID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(0).toString()));
        wAmount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(1).toString()));
        wDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(2).toString()));
        withdrawTableView.getItems().addAll(rows2);
    }

    private void displayOutCheckingHistory() throws SQLException
    {
        ObservableList<ObservableList> rows2 = FXCollections.observableArrayList();
        ResultSet a = myCheckingAccount.getAllOutgoingTransfers();
        while (a.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= a.getMetaData().getColumnCount(); i++) {
                row.add(a.getString(i));
            }
            rows2.add(row);
        }
        oTransactionID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(0).toString()));
        oAmount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(1).toString()));
        oDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(2).toString()));
        destination.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(3).toString()));
        outTableView.getItems().addAll(rows2);
    }

    private void displayINCheckingHistory() throws SQLException
    {
        ObservableList<ObservableList> rows2 = FXCollections.observableArrayList();
        ResultSet a = myCheckingAccount.getAllIncomingTranfsers();
        while (a.next()) {

            ObservableList<String> row = FXCollections.observableArrayList();

            for (int i = 1; i <= a.getMetaData().getColumnCount(); i++) {
                row.add(a.getString(i));
            }
            rows2.add(row);
        }
        iTransactionID.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(0).toString()));
        iAmount.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(1).toString()));
        iDate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(2).toString()));
        source.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>) param ->
                new SimpleStringProperty(param.getValue().get(3).toString()));
        inTableView.getItems().addAll(rows2);
    }
}
