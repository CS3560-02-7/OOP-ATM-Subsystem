import ATMPackage.Member;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import ATMPackage.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DepositController implements Initializable {

    public static Member myMember;
    public static CheckingAccount myCheckingAccount;
    public static SavingsAccount mySavingsAccount;
    private Member member;
    private dbConnection dbConn;

    @FXML Button backButton = new Button();
    @FXML Button backButtonSavings = new Button();
    @FXML Button eightButton = new Button();
    @FXML Label errorLabel = new Label();
    @FXML Button fiveButton = new Button();
    @FXML Button fourButton = new Button();
    @FXML Button nineButton = new Button();
    @FXML Button oneButton = new Button();
    @FXML Button resetButton = new Button();
    @FXML Button sevenButton = new Button();
    @FXML Button sixButton = new Button();
    @FXML Button threeButton = new Button();
    @FXML Button twoButton = new Button();
    @FXML Button depositButton = new Button();
    @FXML Label depositValueLabel = new Label();
    @FXML Button zeroButton = new Button();

    public void initialize(URL url, ResourceBundle rb) {
        dbConn = new dbConnection();
        myMember = Controller.getMember();
        myCheckingAccount = Controller.retChecking();
        mySavingsAccount = Controller.retSavings();

        depositValueLabel.setText("$0");

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

    public String getDepositValueLabel(){
        return this.depositValueLabel.getText();
    }

    public void setDepositValueLabel(String number){
        this.depositValueLabel.setText(number);
    }

    public void setErrorLabel(String text) {
        this.errorLabel.setText(text);
    }

    public dbConnection getDbconn() {
        return dbConn;
    }

    @FXML
    public void processNumber(ActionEvent event){
        String buttonDigit = ((Button) event.getSource()).getText();

        if(getDepositValueLabel()=="$0")
        {
            setDepositValueLabel("$"+buttonDigit);
        }
        //check if a decimal and two values have been entered
        else if (getDepositValueLabel().length()>2 && getDepositValueLabel().substring(getDepositValueLabel().length()-3).matches("[.][0-9][0-9]"))
        {
            setDepositValueLabel(getDepositValueLabel());
        }
        else
        {
            setDepositValueLabel(getDepositValueLabel()+buttonDigit);
        }
    }

    @FXML
    public void backSpace(ActionEvent event){
        if(getDepositValueLabel().equals("$"))
        {
            setDepositValueLabel("$");
        }
        else
        {
            setDepositValueLabel(getDepositValueLabel().substring(0,getDepositValueLabel().length()-1));
        }
    }

    @FXML
    public void addDecimal(ActionEvent event){
        if(getDepositValueLabel().contains("."))
        {
            setDepositValueLabel(getDepositValueLabel());
        }
        else
        {
            setDepositValueLabel(getDepositValueLabel()+".");
        }
    }



    @FXML
    void reset(ActionEvent event) {
        setDepositValueLabel("$0");
    }


    public void depositToSavings(ActionEvent event) {

    }

    public void depositToCheckings(ActionEvent event) {
    }
}