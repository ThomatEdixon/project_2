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
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewPasswordController implements Initializable {
    @FXML private FontIcon icon_back_page;
    @FXML private Label user_id;
    @FXML private PasswordField newPassword;
    @FXML private PasswordField confirmNewPassword;
    @FXML private Label err_new_pass;
    @FXML private Label err_confirm_pass;
    @FXML private Button btnConfirmPassword;

    public void getUser(User user){
        user_id.setText(String.valueOf(user.getId()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);

        btnConfirmPassword.setOnAction(event -> {
            String checkNewPassword = "YES";
            String checkConfirmPassword = "YES";

            String newPass = newPassword.getText();
            String confirmPass = confirmNewPassword.getText();

            String passwordEncoder = BCrypt.hashpw(newPass,BCrypt.gensalt(12));

            //check new password
            if (newPass.length()==0){
                checkNewPassword = "NO";
                newPassword.setStyle("-fx-border-color: red;");
                err_new_pass.setText("New password is required");
                err_new_pass.setStyle("-fx-text-fill: #ff1744");
            } else if (RegisterValidation.checkPassword(newPass)=="NO") {
                checkNewPassword = "NO";
                newPassword.setStyle("-fx-border-color: red;");
                err_new_pass.setText("New password is invalid");
                err_confirm_pass.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkNewPassword = "YES";
                err_new_pass.setText("");
            }

            // check confirm password
            if(confirmPass.length()==0){
                checkConfirmPassword = "NO";
                confirmNewPassword.setStyle("-fx-border-color: red;");
                err_confirm_pass.setText("Confirm password is required");
                err_confirm_pass.setStyle("-fx-text-fill: #ff1744");
            }else if(!confirmPass.equals(newPass)){
                checkConfirmPassword = "NO";
                confirmNewPassword.setStyle("-fx-border-color: red;");
                err_confirm_pass.setText("Confirm password does not match");
                err_confirm_pass.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkConfirmPassword = "YES";
                err_confirm_pass.setText("");
            }

            // check new password and confirm password
            if(checkNewPassword.equals("YES") && checkConfirmPassword.equals("YES")) {
                UserStatement ust = new UserStatement();
                ust.updatePasswordById(passwordEncoder, Integer.parseInt(user_id.getText()));

                //close window
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                //load login window
                loadLoginWindow();
            }
        });

        icon_back_page.setOnMouseClicked(e -> {
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadLoginWindow();
        });
    }

    public void loadLoginWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/User/login.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,700, 510);
            loginScene.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
            loginStage.setTitle("Login");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
