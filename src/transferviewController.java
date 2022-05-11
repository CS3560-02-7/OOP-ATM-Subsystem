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
    private static CheckingAccount myCheckingAccount;
    private static SavingsAccount mySavingsAccount;
    private dbConnection dbConn;

    @FXML Button backButton = new Button();
    @FXML Button backButtonSavings = new Button();
    @FXML Button eightButton = new Button();
    @FXML Button fiveButton = new Button();
    @FXML Button fourButton = new Button();
    @FXML Button nineButton = new Button();
    @FXML Button oneButton = new Button();
    @FXML Button resetButton = new Button();
    @FXML Button sevenButton = new Button();
    @FXML Button sixButton = new Button();
    @FXML Button threeButton = new Button();
    @FXML Button twoButton = new Button();
    @FXML Button transferButton = new Button();
    @FXML Label transferValueLabel = new Label();
    @FXML Label savingsCurrentBal = new Label();
    @FXML Label checkingCurrentBal = new Label();
    @FXML Button zeroButton = new Button();

    @FXML private TableView<Integer> tableView;
    @FXML private TableColumn<Integer, Integer> amount;
    @FXML private TableColumn<String, String> transactionID;

    public void initialize(URL url, ResourceBundle rb) {
        dbConn = new dbConnection();
        myCheckingAccount = Controller.retChecking();
        mySavingsAccount = Controller.retSavings();

        transferValueLabel.setText("$0");
        if(!Objects.isNull(mySavingsAccount))
        {
            mySavingsAccount = Controller.getMember().getSavingsAccount();
            System.out.println(mySavingsAccount.balanceProperty().get());
            savingsCurrentBal.setText("$"+mySavingsAccount.balanceProperty().get());
        }
        if(!Objects.isNull(myCheckingAccount))
        {
            myCheckingAccount = Controller.getMember().getCheckingAccount();
            System.out.println(myCheckingAccount.balanceProperty().get());
            checkingCurrentBal.setText("$"+myCheckingAccount.balanceProperty().get());
        }


        backButton.setOnAction(event -> {
            try {
                changeScenes(2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        backButtonSavings.setOnAction(event -> {
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

    private String gettransferValueLabel(){
        return this.transferValueLabel.getText();
    }

    private void settransferValueLabel(String number){
        this.transferValueLabel.setText(number);
    }

    @FXML
    private void processNumber(ActionEvent event){
        String buttonDigit = ((Button) event.getSource()).getText();

        if(gettransferValueLabel()=="$0")
        {
            settransferValueLabel("$"+buttonDigit);
        }
        //check if a decimal and two values have been entered
        else if (gettransferValueLabel().length()>2 && gettransferValueLabel().substring(gettransferValueLabel().length()-3).matches("[.][0-9][0-9]"))
        {
            settransferValueLabel(gettransferValueLabel());
        }
        else
        {
            settransferValueLabel(gettransferValueLabel()+buttonDigit);
        }
    }

    @FXML
    private void backSpace(ActionEvent event){
        if(gettransferValueLabel().equals("$"))
        {
            settransferValueLabel("$");
        }
        else
        {
            settransferValueLabel(gettransferValueLabel().substring(0,gettransferValueLabel().length()-1));
        }
    }

    @FXML
    private void addDecimal(ActionEvent event){
        if(gettransferValueLabel().contains("."))
        {
            settransferValueLabel(gettransferValueLabel());
        }
        else
        {
            settransferValueLabel(gettransferValueLabel()+".");
        }
    }

    private void alertScene(int alerttype) throws IOException{

        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Warning");
        alertWindow.setMinWidth(250);
        alertWindow.setMinHeight(130);
        Label label = new Label();

        if(alerttype == 1) {
            label.setText(gettransferValueLabel()+" was transferred to your savings account.");
        }
        if(alerttype == 2) {
            label.setText(gettransferValueLabel()+" was transferred to your checking account.");
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
            ResultSet deposits = statement2.executeQuery("SELECT * FROM transfer");
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
    private void reset(ActionEvent event) {
        settransferValueLabel("$0");
    }
    
    @FXML
    private void transferToSavings(ActionEvent event) throws IOException {
        int transactionID = Transaction.getNextTransactionID();
        Date currentDate = new Date();

        //get savings account for this member
        mySavingsAccount = Controller.getMember().getSavingsAccount();
        Transfer newTransfer = new Transfer(transactionID,gettransferValueLabel().substring(1),currentDate,mySavingsAccount.getaccountID().intValue(),myCheckingAccount.getaccountID().intValue());
        newTransfer.transferCash();
        newTransfer.addTransferToDatabase();
        alertScene(1);
        try {
            changeScenes(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mySavingsAccount = null;
    }

}
