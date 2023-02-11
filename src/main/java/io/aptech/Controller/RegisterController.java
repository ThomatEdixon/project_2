package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Model.UserStatement;
import io.aptech.Validation.RegisterValidation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    @FXML private TextField res_fullname;
    @FXML private TextField res_username;
    @FXML private PasswordField res_password;
    @FXML private TextField res_email;
    @FXML private TextField res_phone;
    @FXML private Button btn_register;
    @FXML private Label err_fullname;
    @FXML private Label err_username;
    @FXML private Label err_password;
    @FXML private Label err_email;
    @FXML private Label err_phone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_register.setOnAction(e->{
            String checkFullName = "YES";
            String checkUserName = "YES";
            String checkEmail = "YES";
            String checkPassword = "YES";
            String checkPhone = "YES";
            String resFullName = res_fullname.getText();
            String resUsername = res_username.getText();
            String resPassword = res_password.getText();
            String resEmail = res_email.getText();
            String resPhone = res_phone.getText();

            String passwordEncoder = BCrypt.hashpw(resPassword,BCrypt.gensalt(12));
            // check full name
            if(resFullName.length() == 0 ){
                err_fullname.setText("Full name is required ");
                err_fullname.setStyle("-fx-text-fill: #ff1744");
                checkFullName = "NO";
            } else if (RegisterValidation.checkFullName(resFullName)=="NO") {
                err_fullname.setText("Full name required 6-50 characters");
                err_fullname.setStyle("-fx-text-fill: #ff1744");
                checkFullName = "NO";
            }else {
                checkFullName = "YES";
                err_fullname.setText("");
            }
            //check username
            if(resUsername.length() == 0 ){
                err_username.setText("Username is required ");
                err_username.setStyle("-fx-text-fill: #ff1744");
                checkUserName = "NO";
            } else if (RegisterValidation.checkUsername(resUsername)=="NO") {
                err_username.setText("Username required 6-50 characters");
                err_username.setStyle("-fx-text-fill: #ff1744");
                checkUserName = "NO";
            }else{
                checkUserName = "YES";
                err_username.setText("");
            }
            //check password
            if(resPassword.length() == 0 ){
                err_password.setText("Password is required ");
                err_password.setStyle("-fx-text-fill: #ff1744");
                checkPassword = "NO";
            }else if (RegisterValidation.checkPassword(resPassword)=="NO"){
                err_password.setText("Password required 6-50 include number and characters");
                err_password.setStyle("-fx-text-fill: #ff1744");
                checkPassword = "NO";
            }else{
                checkPassword = "YES";
                err_password.setText("");
            }
            //check email
            if (resEmail.length()==0){
                err_email.setText("Email is required");
                err_email.setStyle("-fx-text-fill: #ff1744");
                checkEmail = "NO";
            } else if (RegisterValidation.checkEmail(resEmail)=="NO") {
                err_email.setText("Email is invalid");
                err_email.setStyle("-fx-text-fill: #ff1744");
                checkEmail = "NO";
            }else{
                checkEmail = "YES";
                err_email.setText("");
            }
            //check phone
            if (resPhone.length()==0){
                err_phone.setText("Phone is required");
                err_phone.setStyle("-fx-text-fill: #ff1744");
                checkPhone= "NO";
            } else if (RegisterValidation.checkPhone(resPhone)=="NO") {

                err_phone.setText("Phone is invalid");
                err_phone.setStyle("-fx-text-fill: #ff1744");
                checkPhone= "NO";
            }else{
                checkPhone= "YES";
                err_phone.setText("");
            }
            //check error before register and insert
            if(checkFullName.equals("YES") && checkUserName.equals("YES") && checkPassword.equals("YES") && checkEmail.equals("YES")&& checkPhone.equals("YES")){
                User n_user = new User();
                n_user.setFullName(resFullName);
                n_user.setUsername(resUsername);
                n_user.setPassword(passwordEncoder);
                n_user.setEmail(resEmail);
                n_user.setPhone(resPhone);
                UserStatement ust = new UserStatement();
                ust.insert(n_user);
                //close window
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                //load login window
                loadLoginWindow();
            }
        });
    }
    public void loadLoginWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/User/login.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Login");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
