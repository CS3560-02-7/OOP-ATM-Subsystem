import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.geometry.Pos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller implements Initializable {

    private dbConnection dbconn;

    // ObservableList<Member> memberlist;
    // ObservableList<Account> accountlist;

    @FXML
    TextField memberIDfield = new TextField();
    @FXML
    PasswordField pinIDfield = new PasswordField();
    @FXML
    Button logins = new Button();
    @FXML
    Button savingsBackbtn = new Button();
    @FXML
    Button checkingBackbtn = new Button();
    @FXML
    Button logOut = new Button();
    @FXML
    Button toChecking = new Button();
    @FXML
    Button toSavings = new Button();
    @FXML
    Button toWithdrawal = new Button();
    @FXML
    Button toDeposit = new Button();
    @FXML
    Button toWithdrawalSavings = new Button();
    @FXML
    Button toDepositSavings = new Button();

    public void initialize(URL url, ResourceBundle rb) {

        dbconn = new dbConnection();
        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        savingsBackbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        checkingBackbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
        toWithdrawal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        toDeposit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        toWithdrawalSavings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(7);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        toDepositSavings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    changeScenes(6);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/checkingWithdraw.fxml")));
        }
        if (sceneNum == 6) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/savingsDeposit.fxml")));
        }
        if (sceneNum == 7) {
            page = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BankUI/savingsWithdrawal.fxml")));
        }
        Scene scene = new Scene(page, 800, 600);
        Stage stage = Main.retStage();
        stage.setScene(scene);
        stage.show();
    }

    private void alertScene(int alerttype) throws IOException{

        Stage alertWindow = new Stage();
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle("Alert");
        alertWindow.setMinWidth(250);
        alertWindow.setMinHeight(130);
        Label label = new Label();

        if(alerttype == 1){
            label.setText("Incorrect account credentials.\nPlease try again.\n");
        }
        if(alerttype == 2){
            label.setText("");
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
        int memberID = Integer.parseInt(mID);
        System.out.println(memberID); //testing

        String pID = pinIDfield.getText().trim();
        int pinID = Integer.parseInt(pID);
        System.out.println(pinID);  //testing

        Member myMember = Member.createMemberFromDatabase(memberID);
        boolean istrue = false;
        istrue = myMember.logMemberIn(memberID, pinID);

        if(!istrue){
            try {
                alertScene(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                changeScenes(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}

