import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import ATMPackage.Member;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import ATMPackage.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {

    private static Member myMember;
    private static CheckingAccount myCheckingAccount;
    private static SavingsAccount mySavingsAccount;

    private dbConnection dbconn;

    @FXML TextField memberIDfield = new TextField();
    @FXML PasswordField pinIDfield = new PasswordField();
    @FXML TextField street = new TextField();
    @FXML TextField memID = new TextField();
    @FXML Button logins = new Button();
    @FXML Button savingsBackbtn = new Button();
    @FXML Button checkingBackbtn = new Button();
    @FXML Button logOut = new Button();
    @FXML Button toChecking = new Button();
    @FXML Button toSavings = new Button();
    @FXML Button toWithdrawal = new Button();
    @FXML Button toDeposit = new Button();
    @FXML Button toWithdrawalSavings = new Button();
    @FXML Button toDepositSavings = new Button();
    @FXML Button tohistoryChecking = new Button();
    @FXML Button tohistorySavings = new Button();
    @FXML Button tobalanceChecking = new Button();
    @FXML Button tobalanceSavings = new Button();
    @FXML Button totransferChecking = new Button();
    @FXML Button totrasnferSavings = new Button();
    @FXML Button submit = new Button("Submit");
    @FXML Button closepin = new Button("Close");

    public void initialize(URL url, ResourceBundle rb) {

        dbconn = new dbConnection();

        toDeposit.setOnAction(event -> {
            try {
                changeScenes(4);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        toDepositSavings.setOnAction(event -> {
            try {
                changeScenes(5);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        toWithdrawal.setOnAction(event -> {
            try {
                changeScenes(6);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        toWithdrawalSavings.setOnAction(event -> {
            try {
                changeScenes(7);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tohistoryChecking.setOnAction(event -> {
            try {
                changeScenes(8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tohistorySavings.setOnAction(event -> {
            try {
                changeScenes(9);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        totransferChecking.setOnAction(event -> {
            try {
                changeScenes(12);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        totrasnferSavings.setOnAction(event -> {
            try {
                changeScenes(13);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void changeScenes(int sceneNum) throws IOException {

        Parent page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/Login.fxml")));
        if (sceneNum == 0) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/Login.fxml")));
        }
        if (sceneNum == 1) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/AccountSelection.fxml")));
        }
        if (sceneNum == 2) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/CheckingAccount.fxml")));
        }
        if (sceneNum == 3) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/SavingsAccount.fxml")));
        }
        if (sceneNum == 4) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/checkingDeposit.fxml")));
        }
        if (sceneNum == 5) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/savingsDeposit.fxml")));
        }
        if (sceneNum == 6) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/checkingWithdraw.fxml")));
        }
        if (sceneNum == 7) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/savingsWithdrawal.fxml")));
        }
        if (sceneNum == 8) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/checkingHistory.fxml")));
        }
        if (sceneNum == 9) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/savingsHistory.fxml")));
        }
        if (sceneNum == 12) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/checkingTransfer.fxml")));
        }
        if (sceneNum == 13) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/savingsTransfer.fxml")));
        }
        Scene scene = new Scene(page, 800, 600);
        Stage stage = Main.retStage();
        stage.setScene(scene);
        stage.show();
    }

    private void alertScene(int alerttype) throws IOException{

        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Warning");
        alertWindow.setMinWidth(250);
        alertWindow.setMinHeight(130);
        Label label = new Label();


        if(alerttype == 1) {
            label.setText("Incorrect account credentials.\nPlease try again.\n");
        }
        if(alerttype == 2) {
            label.setText("Field(s) empty");
        }
        if(alerttype == 3)
        {
            label.setText("You do not have a savings account.");
        }
        if(alerttype == 4)
        {
            label.setText("Request Recieved. New pin will be mailed out.");
        }
        if(alerttype == 5)
        {
            label.setText("   Current checking account balance: $"+myCheckingAccount.balanceProperty().get()+"   ");
        }
        if(alerttype == 6)
        {
            label.setText("   Current savings account balance: $"+mySavingsAccount.balanceProperty().get()+"   ");
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
    public void login(ActionEvent event) throws IOException {

        String mID = memberIDfield.getText().trim();
        String pID = pinIDfield.getText().trim();
        myMember =  new Member(0,"","",0,"");
        if(mID.equals("")||pID.equals("")) {
            alertScene(2);
        }
        else {
            int memberID = Integer.parseInt(mID);
            System.out.println(memberID); //testing

            int pinID = Integer.parseInt(pID);
            System.out.println(pinID);  //testing

            //myMember = new Member(0,"","",0,"");
            boolean istrue = myMember.logMemberIn(memberID, pinID);

            if(!istrue) {
                try {
                    alertScene(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myMember = null;
            }
            else {
                try {
                    changeScenes(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void logout(ActionEvent event)throws IOException {
        myMember.logMemberOut();
        try {
            changeScenes(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        myMember = null;
    }

    @FXML
    public void forgotPin(ActionEvent event) throws IOException {

        Stage forgotpin = new Stage();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        street.setPromptText("Enter Address ");
        street.setPrefColumnCount(10);
        street.getText();
        GridPane.setConstraints(street, 0, 0);
        grid.getChildren().add(street);

        memID.setPrefColumnCount(15);
        memID.setPromptText("Enter Member ID");
        GridPane.setConstraints(memID, 0, 1);
        grid.getChildren().add(memID);

        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        GridPane.setConstraints(closepin, 1, 1);
        grid.getChildren().add(closepin);
        closepin.setOnAction(eee -> forgotpin.close());

        myMember =  new Member(0,"","",0,"");

        submit.setOnAction(ee -> {
            try {
                String addr = street.getText().trim();
                String mid = memID.getText().trim();
                if(mid.equals("")||addr.equals("")) {
                    alertScene(2);
                }else{
                    int memberID = Integer.parseInt(mid);
                    boolean istrue = myMember.forgotPin(memberID, addr);

                    if(!istrue) {
                        try {
                            alertScene(1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            alertScene(4);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        forgotpin.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        Scene alert = new Scene(grid);
        forgotpin.setScene(alert);
        forgotpin.showAndWait();
        myMember = null;
    }

    @FXML
    public void changePin(ActionEvent event) throws IOException {

        Stage forgotpin = new Stage();

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        street.setPromptText("Enter Current PIN ");
        street.setPrefColumnCount(10);
        street.getText();

        GridPane.setConstraints(street, 0, 0);
        grid.getChildren().add(street);

        GridPane.setConstraints(submit, 1, 0);
        grid.getChildren().add(submit);

        GridPane.setConstraints(closepin, 1, 1);
        grid.getChildren().add(closepin);
        closepin.setOnAction(eee -> forgotpin.close());

        submit.setOnAction(ee -> {
            try {
                String addr = street.getText().trim();
                if(addr.equals("")) {
                    alertScene(2);
                }else{
                    int pID = Integer.parseInt(addr);
                    boolean istrue = myMember.requestChangePin(pID);

                    if(!istrue) {
                        try {
                            alertScene(1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            alertScene(4);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        forgotpin.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        Scene alert = new Scene(grid);
        forgotpin.setScene(alert);
        forgotpin.showAndWait();
        closepin.requestFocus();
    }


    public void getCheckingAccount(ActionEvent event)throws IOException {
        myCheckingAccount = myMember.getCheckingAccount();
        try {
            changeScenes(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mySavingsAccount = null;
    }

    public void getSavingsAccount(ActionEvent event)throws IOException {
        mySavingsAccount = myMember.getSavingsAccount();
        if(mySavingsAccount!=null)
        {
            try {
                changeScenes(3);
            } catch (IOException e) {
                e.printStackTrace();
            }
            myCheckingAccount = null;
        }
        else
        {
            try {
                alertScene(3);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exitAccounts(ActionEvent event) throws IOException{
        myCheckingAccount = null;
        mySavingsAccount = null;
        try {
            changeScenes(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCheckingBal(ActionEvent event) throws IOException{
        try {
            alertScene(5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSavingsBal(ActionEvent event) throws IOException{
        try {
            alertScene(6);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Member getMember(){
        return myMember;
    }

    public static CheckingAccount retChecking()
    {
        return myCheckingAccount;
    }

    public static SavingsAccount retSavings()
    {
        return mySavingsAccount;
    }


}

