package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Model.AddBudgetStatement;
import io.aptech.Model.UserStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddBalanceController implements Initializable {
    @FXML
    private FontIcon icon_back_page;
    @FXML private Label err_balance;
    @FXML private TextField add_balance;
    @FXML private Button btn_add_balance;
    @FXML private Label now_balance;
    @FXML private Label user_id;
    private AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
    private int balance = 0 ;
    public void getUserId(int userId) {
        user_id.setText(String.valueOf(userId));
        balance = addBudgetStatement.getBalance(userId);
        String type = addBudgetStatement.getType(userId);
        now_balance.setText("Your balance is : "+balance+" "+type);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        icon_back_page.setOnMouseClicked(e->{
            // close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            loadAccountUserWindow();
        });
        btn_add_balance.setOnAction(e->{
            String increased = add_balance.getText();
            String checkIncreased = "YES";
            if(increased.length() < 0){
                checkIncreased = "NO";
                err_balance.setText("Balance is required");
                err_balance.setStyle("-fx-text-fill: #ff1744");
            }
            if(checkIncreased == "YES"){
                balance += Integer.parseInt(increased);
                addBudgetStatement.updateBalance(balance);
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                loadAccountUserWindow();
            }
        });
    }
    public void loadAccountUserWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainWindow/UserManagement.fxml"));
            Parent root = loader.load();
            UserManagementController userManagementController = loader.getController();
            UserStatement userStatement = new UserStatement();
            User user = userStatement.getUserById(Integer.parseInt(user_id.getText()));
            user.setId(user.getId());
            user.setFullName(user.getFullName());
            userManagementController.getUserById(user);
            Scene loginScene = new Scene(root,695, 770);
            loginStage.setTitle("Account User");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
