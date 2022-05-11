import ATMPackage.Member;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import ATMPackage.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;
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

    private static CheckingAccount myCheckingAccount;
    private static SavingsAccount mySavingsAccount;
    private dbConnection dbConn;
    private int currentAccount;

    @FXML Button toChecking = new Button();
    @FXML Button toSavings = new Button();
    @FXML Label withdrawValueLabel = new Label();
    @FXML Label hundredTracker = new Label();
    @FXML Label fiftyTracker = new Label();
    @FXML Label twentyTracker = new Label();
    @FXML Label tenTracker = new Label();
    @FXML Label fiveTracker = new Label();
    @FXML Label oneTracker = new Label();
    @FXML Label savingsCurrentBal = new Label();
    @FXML Label checkingCurrentBal = new Label();
    @FXML Label savingsRemainingWithdraw = new Label();
    @FXML Label checkingRemainingWithdraw = new Label();


    public void initialize(URL url, ResourceBundle rb) {
        dbConn = new dbConnection();
        myCheckingAccount = Controller.retChecking();
        mySavingsAccount = Controller.retSavings();

        withdrawValueLabel.setText("$0.00");
        if(!Objects.isNull(mySavingsAccount))
        {
            mySavingsAccount = Controller.getMember().getSavingsAccount();
            savingsCurrentBal.setText("$"+mySavingsAccount.balanceProperty().get());
            String a = String.valueOf(mySavingsAccount.getDailyRemainingWithdraw());
            savingsRemainingWithdraw.setText("$"+a);
            currentAccount = 1;
        }
        if(!Objects.isNull(myCheckingAccount))
        {
            myCheckingAccount = Controller.getMember().getCheckingAccount();
            checkingCurrentBal.setText("$"+myCheckingAccount.balanceProperty().get());
            String a = String.valueOf(myCheckingAccount.getDailyRemainingWithdraw());
            checkingRemainingWithdraw.setText("$"+a);
            currentAccount = 0;
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

    private String getWithdrawalValueLabel(){
        return this.withdrawValueLabel.getText();
    }

    private void setWithdrawalValueLabel(String number){
        this.withdrawValueLabel.setText(number);
    }

    private String getHundredTracker(){
        return this.hundredTracker.getText();
    }

    private void setHundredTracker(String number){
        this.hundredTracker.setText(number);
    }

    private String getFiftyTracker(){
        return this.fiftyTracker.getText();
    }

    private void setFiftyTracker(String number){
        this.fiftyTracker.setText(number);
    }

    private String getTwentyTracker(){
        return this.twentyTracker.getText();
    }

    private void setTwentyTracker(String number){
        this.twentyTracker.setText(number);
    }

    private String getTenTracker(){
        return this.tenTracker.getText();
    }

    private void setTenTracker(String number){
        this.tenTracker.setText(number);
    }

    private String getFiveTracker(){
        return this.fiveTracker.getText();
    }

    private void setFiveTracker(String number){
        this.fiveTracker.setText(number);
    }

    private String getOneTracker(){
        return this.oneTracker.getText();
    }

    private void setOneTracker(String number){
        this.oneTracker.setText(number);
    }

    private String decrementer(String currentVal, int decrementValue)
    {
        int currentNum = Integer.parseInt(currentVal);
        if(currentNum!=0)
        {
            currentNum-=1;
            String currentTotalwithdraw = getWithdrawalValueLabel();
            currentTotalwithdraw = currentTotalwithdraw.substring(1,currentTotalwithdraw.length()-3);
            int tempInt = Integer.parseInt(currentTotalwithdraw);
            tempInt-=decrementValue;
            setWithdrawalValueLabel("$"+String.valueOf(tempInt)+".00");
            return String.valueOf(currentNum);
        }
        else
        {
            return currentVal;
        }
    }

    private String incrementer(String currentVal, int incrementValue) throws IOException {
        int currentNum = Integer.parseInt(currentVal);
        String maxPossible = null;
        if(currentAccount==0)
        {
            maxPossible = checkingRemainingWithdraw.getText().substring(1);
        }
        else if (currentAccount==1)
        {
            maxPossible = savingsRemainingWithdraw.getText().substring(1);
        }

        String currentTotalwithdraw = getWithdrawalValueLabel();
        currentTotalwithdraw = currentTotalwithdraw.substring(1,currentTotalwithdraw.length()-3);
        int tempInt = Integer.parseInt(currentTotalwithdraw);
        BigDecimal newPossibleValue = new BigDecimal(tempInt+incrementValue);
        BigDecimal maxPossibleValue = new BigDecimal(maxPossible);

        //if new possbile value is greater than maximum withdraw dont allow increment
        if(newPossibleValue.compareTo(maxPossibleValue)>0)
        {
            alertScene(2);
            return currentVal;
        }
        else
        {
            tempInt = tempInt+incrementValue;
            setWithdrawalValueLabel("$"+String.valueOf(tempInt)+".00");
            return String.valueOf(currentNum+1);
        }



    }

    @FXML
    private void hundredDecrement()
    {
        String newValue = decrementer(getHundredTracker(),100);
        setHundredTracker(newValue);

    }

    @FXML
    private void fiftyDecrement()
    {
        String newValue = decrementer(getFiftyTracker(),50);
        setFiftyTracker(newValue);
    }

    @FXML
    private void twentyDecrement()
    {
        String newValue = decrementer(getTwentyTracker(),20);
        setTwentyTracker(newValue);
    }

    @FXML
    private void tenDecrement()
    {
        String newValue = decrementer(getTenTracker(),10);
        setTenTracker(newValue);
    }

    @FXML
    private void fiveDecrement()
    {
        String newValue = decrementer(getFiveTracker(),5);
        setFiveTracker(newValue);
    }

    @FXML
    private void oneDecrement()
    {
        String newValue = decrementer(getOneTracker(),1);
        setOneTracker(newValue);
    }

    @FXML
    private void hundredIncrement() throws IOException {
        String newValue = incrementer(getHundredTracker(),100);
        setHundredTracker(newValue);

    }

    @FXML
    private void fiftyIncrement() throws IOException {
        String newValue = incrementer(getFiftyTracker(),50);
        setFiftyTracker(newValue);
    }

    @FXML
    private void twentyIncrement() throws IOException {
        String newValue = incrementer(getTwentyTracker(),20);
        setTwentyTracker(newValue);
    }

    @FXML
    private void tenIncrement() throws IOException {
        String newValue = incrementer(getTenTracker(),10);
        setTenTracker(newValue);
    }

    @FXML
    private void fiveIncrement() throws IOException {
        String newValue = incrementer(getFiveTracker(),5);
        setFiveTracker(newValue);
    }

    @FXML
    private void oneIncrement() throws IOException {
        String newValue = incrementer(getOneTracker(),1);
        setOneTracker(newValue);
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
        if(alerttype==2)
        {
            label.setText("That would exceed your withdrawal limit.");
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

    @FXML
    private void savingsWithdraw(ActionEvent event) throws IOException {
        int transactionID = Transaction.getNextTransactionID();
        Date currentDate = new Date();
        Withdrawal newWithdrawal = new Withdrawal(transactionID,getWithdrawalValueLabel().substring(1),currentDate,mySavingsAccount.getaccountID().intValue());
        if(newWithdrawal.withdrawCash())
        {
            newWithdrawal.addWithdrawalToDatabase();
        }

        //let them know the withdrawal was successful and bo back to previous menu
        alertScene(1);
        try {
            changeScenes(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void checkingWithdraw(ActionEvent event) throws IOException {
        int transactionID = Transaction.getNextTransactionID();
        Date currentDate = new Date();
        Withdrawal newWithdrawal = new Withdrawal(transactionID,getWithdrawalValueLabel().substring(1),currentDate,myCheckingAccount.getaccountID().intValue());
        if(newWithdrawal.withdrawCash())
        {
            newWithdrawal.addWithdrawalToDatabase();
        }

        //let them know the withdrawal was successful and bo back to previous menu
        alertScene(1);
        try {
            changeScenes(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void reset(ActionEvent event) {
        setWithdrawalValueLabel("$0.00");
        setHundredTracker("0");
        setFiftyTracker("0");
        setTwentyTracker("0");
        setTenTracker("0");
        setFiveTracker("0");
        setOneTracker("0");
    }

}
