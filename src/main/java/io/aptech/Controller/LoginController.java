package io.aptech.Controller;

import io.aptech.Entity.User;
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
    private TextField lg_userName;
    @FXML
    private TextField lg_password;
    @FXML
    private Label err_userName;
    @FXML
    private  Label err_password;
    @FXML
    private Button lg_submit;
    @FXML
    private Label err_login;
@FXML
private Button change_passowrd;
    @FXML
    private Button lg_register;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lg_submit.setOnAction(e ->{
            String checkUserName = "YES";
            String checkPassword = "YES";
            String userName = lg_userName.getText();
            String password = lg_password.getText();
            if(userName.length() == 0){
                lg_userName.setStyle("-fx-border-color:  red");
                checkUserName = "NO";
            }else if(RegisterValidation.checkEmail(userName).equals("NO")){
                err_userName.setText("Email is required");
                checkUserName = "NO";
            }else  {
                lg_userName.setStyle("-fx-border-color: black");
                err_userName.setText("");
                checkUserName = "YES";
            }
            if(password.length() == 0){
                checkPassword = "NO";
                lg_password.setStyle("-fx-border-color:  red");
            }else{
                checkPassword = "YES";
                lg_password.setStyle("-fx-border-color: black");
            }
            if(checkUserName.equals("YES") && checkPassword.equals("YES")){
                UserStatement rs = new UserStatement();
                ResultSet user = rs.getBYUserName(userName);
                try {
                    if(user.next()){
                        if(BCrypt.checkpw(password,user.getString("user_password"))) {
                            Node node = (Node) e.getSource();
                            Stage thisStage = (Stage) node.getScene().getWindow();
                            thisStage.close();
                            //Loading Main Widows
                            loadMainWindows("/io/aptech/project/hello-view.fxml");
                            //User info
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
        change_passowrd.setOnAction(e ->{
            loadMainWindows("/io/aptech/project/hello-view.fxml");
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
