import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class WithdrawalSceneController implements Initializable {

    @FXML
    Button backButton = new Button();

    @FXML
    Button backButtonSavings = new Button();

    @FXML
    Button depositButton = new Button();

    @FXML
    Text depositText = new Text();

    @FXML
    Label depositValueLabel = new Label();

    @FXML
    Button fiftyButton = new Button();

    @FXML
    Button fiveButton = new Button();

    @FXML
    Button hundredButton = new Button();

    @FXML
    Button resetButton = new Button();

    @FXML
    Button tenButton = new Button();

    @FXML
    Button twentyButton = new Button();


    private float moneyToDeposit;

    private Member member;


    public void initialize(URL url, ResourceBundle rb) {
        // dbconn = new dbConnection();

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

