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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import Validator;
//import ATMPackage.Member;
//import ATMPackage.Withdrawal;

public class WithdrawalSceneController implements Initializable {

    public static CheckingAccount myCheckingAccount;
    public static SavingsAccount mySavingsAccount;
    private dbConnection dbConn;

    @FXML Button toChecking = new Button();
    @FXML Button toSavings = new Button();
    @FXML Label withdrawValueLabel = new Label();
    @FXML Label savingsCurrentBal = new Label();
    @FXML Label checkingCurrentBal = new Label();


    public void initialize(URL url, ResourceBundle rb) {
        dbConn = new dbConnection();
        myCheckingAccount = Controller.getMember().getCheckingAccount();
        mySavingsAccount = Controller.getMember().getSavingsAccount();

        withdrawValueLabel.setText("$0");
        if(!Objects.isNull(mySavingsAccount))
        {
            System.out.println(mySavingsAccount.balanceProperty().get());
            savingsCurrentBal.setText("$"+mySavingsAccount.balanceProperty().get());
        }
        if(!Objects.isNull(myCheckingAccount))
        {
            System.out.println(myCheckingAccount.balanceProperty().get());
            checkingCurrentBal.setText("$"+myCheckingAccount.balanceProperty().get());
        }

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
        return this.withdrawValueLabel.getText();
    }

    public void setWithdrawalValueLabel(String number){
        this.withdrawValueLabel.setText(number);
    }

    private void alertScene(int alerttype) throws IOException{

        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Withdrawal info");
        alertWindow.setMinWidth(250);
        alertWindow.setMinHeight(130);
        Label label = new Label();

        if(alerttype == 1) {
            label.setText("Your withdrawal of "+getWithdrawalValueLabel()+" was successful");
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

    public dbConnection getDbconn() {
        return dbConn;
    }



    @FXML
    void reset(ActionEvent event) {
        setWithdrawalValueLabel("$0");
    }

}
