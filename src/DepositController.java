import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

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

    private Member member;
    private dbConnection dbConn;

    @FXML
    Button backButton = new Button();

    @FXML
    Button backButtonSavings = new Button();

    @FXML
    Button eightButton = new Button();

    @FXML
    Label errorLabel = new Label();

    @FXML
    Button fiveButton = new Button();

    @FXML
    Button fourButton = new Button();

    @FXML
    Button nineButton = new Button();

    @FXML
    Button oneButton = new Button();

    @FXML
    Button resetButton = new Button();

    @FXML
    Button sevenButton = new Button();

    @FXML
    Button sixButton = new Button();

    @FXML
    Button threeButton = new Button();

    @FXML
    Button twoButton = new Button();

    @FXML
    Button withdrawButton = new Button();

    @FXML
    Label withdrawalValueLabel = new Label();

    @FXML
    Button zeroButton = new Button();


    public void initialize(URL url, ResourceBundle rb) {
        dbConn = new dbConnection();

        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        backButtonSavings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    private Member getMember() {
        return member;
    }


    public String getWithdrawalValueLabel(){
        return this.withdrawalValueLabel.getText();
    }
    public void setWithdrawalValueLabel(String number){
        this.withdrawalValueLabel.setText(number);
    }
    public void setErrorLabel(String text) {
        this.errorLabel.setText(text);
    }
    public dbConnection getDbconn() {
        return dbConn;
    }

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    public void processNumber(ActionEvent event) throws IOException {
        //  we are recording the text set to the button once it is pressed
        String buttonDigit = ((Button) event.getSource()).getText();

        //  get withdrawal label without '$' symbol in int
        String currentWithdrawalAmount = getWithdrawalValueLabel().substring(0);

        if (Integer.parseInt(currentWithdrawalAmount) == 0) {
            setWithdrawalValueLabel(buttonDigit);
        } else {
            String newWithdrawalAmount = currentWithdrawalAmount + buttonDigit;

            if (Integer.parseInt(newWithdrawalAmount) < 2000) {
                setWithdrawalValueLabel(newWithdrawalAmount);

            } else {
                setErrorLabel("YOU ARE ONLY ALLOWED TO WITHDRAWAL $2000");
                setWithdrawalValueLabel("$2000.00");
            }
        }
    }

    @FXML
    void reset(ActionEvent event) {

    }

    @FXML
    void withdraw(ActionEvent event) {
        float currentWithdrawalAmount = Float.parseFloat(getWithdrawalValueLabel().substring(1));
        //float customerFundsAfterWithdrawal = getMember().getBalance()-currentWithdrawalAmount;

    }

}