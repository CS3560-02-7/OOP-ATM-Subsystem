import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;


public class DepositController implements Initializable {

    @FXML
    private Button backButton = new Button();

    @FXML
    private Button depositButton = new Button();


    @FXML
    private Label depositValueLabel;

    private float moneyToDeposit;

    private Member member;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // dbconn = new dbConnection();
    }

    public float getMoneyToDeposit(){
        return this.moneyToDeposit;
    }

    public void setMoneyToDeposit(float moneyToDeposit) {
        this.moneyToDeposit = moneyToDeposit;
    }

    public Member getMember() {
        return member;
    }

    public void setDepositValueLabel(String text){
        this.depositValueLabel.setText(text);
    }

    public void processDeposit(ActionEvent event){

        // get value to be added without the '+' symbol
        float buttonPressed = Float.parseFloat(((Button) event.getSource()).getText().substring(1));
        float newMoneyToDeposit = getMoneyToDeposit()+buttonPressed;


        if (newMoneyToDeposit <= 2000){
            setMoneyToDeposit(newMoneyToDeposit);
            //setDepositValueLabel(Helper.formatCurrency(newMoneyToDeposit));
        } else {
            //displayError();
        }
    }


    @FXML
    void deposit(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {

    }

    @FXML
    void switchingBankScene(ActionEvent event) {

    }

}

