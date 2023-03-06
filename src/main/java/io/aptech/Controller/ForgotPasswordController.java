package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Mail.SendMail;
import io.aptech.Model.ForgotPasswordStatement;
import io.aptech.Validation.RegisterValidation;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ForgotPasswordController implements Initializable {
    @FXML private FontIcon icon_back_page;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPhone;
    @FXML private Label err_email;
    @FXML private Label err_phone;
    @FXML private Button btnForgotPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnForgotPassword.setOnAction(e -> {
            String check_Email = "YES";
            String check_Phone = "YES";

            String email = txtEmail.getText();
            String phone = txtPhone.getText();

            //check email
            if (email.length()==0){
                check_Email = "NO";
                err_email.setText("Email is required");
                err_email.setStyle("-fx-text-fill: #ff1744");
            } else if (RegisterValidation.checkEmail(email)=="NO") {
                check_Email = "NO";
                err_email.setText("Email is invalid");
                err_email.setStyle("-fx-text-fill: #ff1744");
            }else {
                check_Email = "YES";
                err_email.setText("");
            }
            //check phone
            if (phone.length()==0){
                check_Phone = "YES";
                err_phone.setText("Phone is required");
                err_phone.setStyle("-fx-text-fill: #ff1744");
            } else if (RegisterValidation.checkPhone(phone)=="NO") {
                check_Phone = "YES";
                err_phone.setText("Phone is invalid");
                err_phone.setStyle("-fx-text-fill: #ff1744");
            }else {
                check_Phone = "YES";
                err_phone.setText("");
            }

            // check phone and email
            if(check_Phone == "YES" && check_Email == "YES") {
                try {
                    ForgotPasswordStatement forgotPasswordStatement = new ForgotPasswordStatement();
                    ResultSet rs = forgotPasswordStatement.check(email, phone);
                    if (rs.next()) {
                        forgotPasswordStatement.create();
                        forgotPasswordStatement.insertTempCode(email, String.valueOf(SendMail.send()));
                        // set time to drop table tempCode after 1 minutes
                        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

                        Runnable task = () -> {
                            forgotPasswordStatement.dropTempCode();
                        };
                        executor.schedule(task, 1, TimeUnit.MINUTES);
                        executor.shutdown();
                        //close window
                        Node node = (Node) e.getSource();
                        Stage thisStage = (Stage) node.getScene().getWindow();
                        thisStage.close();
                        //load login window
                        loadVerifyCodeWindow();
                    } else {
                        err_phone.setText("Email or Phone is not match");
                        err_phone.setStyle("-fx-text-fill: #ff1744");
                    }
                }catch (SQLException e1) {
                    e1.printStackTrace();
                }
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

    public void loadVerifyCodeWindow(){
        try {
            Stage verifyCodeStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/User/verifyCode.fxml"));
            Parent root = loader.load();
            Scene verifyCodeScene = new Scene(root,475, 240);
            verifyCodeStage.setTitle("Verify Code");
            verifyCodeStage.setScene(verifyCodeScene);
            verifyCodeStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
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
