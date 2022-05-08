import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import Validator;
//import Member;
//import Withdrawal;

public class WithdrawalSceneController implements Initializable {

    private Member member;
    private dbConnection dbconn;


    @FXML
    private Label errorLabel, withdrawalValueLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbConnection= new dbconn;
    }

    private Member getMember() {
        return member;
    }
    public void setMember(Member, member){
        this.member = member;
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
