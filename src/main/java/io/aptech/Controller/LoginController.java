package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Model.AddBudgetStatement;
import io.aptech.Model.UserStatement;
import io.aptech.Validation.RegisterValidation;
import io.aptech.project.FxmlLoader;
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
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private TextField lg_email;
    @FXML
    private TextField lg_password;
    @FXML
    private Label err_email;
    @FXML
    private Label err_password;
    @FXML
    private Button lg_submit;
    @FXML
    private Label err_login;
    @FXML
    private Button change_password;
    @FXML
    private Button lg_register;
    private AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
    private int budget = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lg_submit.setOnAction(e ->{
            String checkEmail = "YES";
            String checkPassword = "YES";
            String email = lg_email.getText();
            String password = lg_password.getText();
            if(email.length() == 0){
                lg_email.setStyle("-fx-border-color:  red");
                checkEmail = "NO";
            }else if(RegisterValidation.checkEmail(email).equals("NO")){
                err_email.setText("Email is required");
                checkEmail = "NO";
            }else {
                lg_email.setStyle("-fx-border-color: black");
                err_email.setText("");
                checkEmail = "YES";
            }
            if(password.length() == 0){
                checkPassword = "NO";
                lg_password.setStyle("-fx-border-color:  red");
            }else{
                checkPassword = "YES";
                lg_password.setStyle("-fx-border-color: black");
            }
            if(checkEmail.equals("YES") && checkPassword.equals("YES")){
                UserStatement rs = new UserStatement();
                ResultSet user = rs.getByEmail(email);
                try {
                    if(user.next()){
                        if(BCrypt.checkpw(password,user.getString("user_password"))) {
                            User user1 = new User();
                            user1.setId(user.getInt("id"));
                            user1.setFullName(user.getString("full_name"));
                            budget = addBudgetStatement.getBalance(user1.getId());
                            //close window
                            Node node = (Node) e.getSource();
                            Stage thisStage = (Stage) node.getScene().getWindow();
                            thisStage.close();
                            //Loading Main Widows
                            try {
                                Stage loginStage = new Stage();
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("/MainWindow/homePage.fxml"));
                                Parent root = loader.load();
                                HomePageController homePageController = loader.getController();
                                homePageController.getUser(user1);
                                Scene loginScene = new Scene(root,700, 690);
                                loginStage.setTitle("Home Page");
                                loginStage.setScene(loginScene);
                                loginStage.show();
                            }catch (IOException e1){
                                e1.printStackTrace();
                            }
                            if(budget == 0){
                                try {
                                    Stage loginStage = new Stage();
                                    FXMLLoader loader = new FXMLLoader();
                                    loader.setLocation(getClass().getResource("/Budget/budget.fxml"));
                                    Parent root = loader.load();
                                    AddBudgetController budgetController = loader.getController();
                                    budgetController.getUserId(user1.getId());
                                    Scene loginScene = new Scene(root,505, 350);
                                    loginStage.setTitle("Budget");
                                    loginStage.setScene(loginScene);
                                    loginStage.show();
                                }catch (IOException e2){
                                    e2.printStackTrace();
                                }
                            }
                        }else{
                            err_login.setText("UserName or Password is invalid");
                            err_login.setStyle("-fx-text-fill: #E53935");
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        change_password.setOnAction(e ->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            loadMainWindows("/User/forgotPassword.fxml");
        });
        lg_register.setOnAction(e ->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadMainWindows("/User/register.fxml");
        });
    }
    public void loadMainWindows(String path){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,600, 502);
            loginStage.setTitle("Login");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
