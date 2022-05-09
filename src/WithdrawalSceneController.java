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
//import Validator;
//import Member;
//import Withdrawal;

public class WithdrawalSceneController implements Initializable {

    private Member member;
    private dbConnection dbconn;

    @FXML
    Button toChecking = new Button();

    @FXML
    Button toSavings = new Button();

    @FXML
    private Label errorLabel, withdrawalValueLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbconn= new dbConnection();

        toChecking.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        toSavings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Member getMember() {
        return member;
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
        return dbconn;
    }

    @FXML
    void back(ActionEvent event) {

    }

    @FXML
    void processNumber(ActionEvent event) {
        //  we are recording the text set to the button once it is pressed
        String buttonDigit = ((Button) event.getSource()).getText();

        //  get withdrawal label without '$' symbol in int
        String currentWithdrawalAmount = Validator.toCleanNumber(getWithdrawalValueLabel().substring(1));

        if (Integer.parseInt(currentWithdrawalAmount) == 0) {
            setWithdrawalValueLabel("$" + buttonDigit + ".00");
        } else {
            String newWithdrawalAmount = currentWithdrawalAmount + buttonDigit;

            if (Integer.parseInt(newWithdrawalAmount) < 2000) {
                setWithdrawalValueLabel("$" + newWithdrawalAmount + ".00");

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
