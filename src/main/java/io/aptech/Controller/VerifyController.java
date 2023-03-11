package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Model.ForgotPasswordStatement;
import io.aptech.Model.UserStatement;
import io.aptech.Model.VerifyCodeStatement;
import io.aptech.Validation.RegisterValidation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class VerifyController implements Initializable {
    @FXML private Label user_id;
    @FXML private FontIcon icon_back_page;
    @FXML private TextField txtCode;
    @FXML private Button btnVerifyCode;
    @FXML private Label err_code;

    public void getUser(User user){
        user_id.setText(String.valueOf(user.getId()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // set time to drop table tempCode after 1 minutes
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {


        };
        executor.schedule(task, 1, TimeUnit.MINUTES);
        executor.shutdown();

        user_id.setVisible(false);

        btnVerifyCode.setOnAction(e -> {
            String checkCode = "YES";
            String verifyCode = txtCode.getText();

            //check code
            if (verifyCode.length()==0){
                checkCode = "NO";
                err_code.setText("Verify code is required");
                err_code.setStyle("-fx-text-fill: #ff1744");
            } else if (RegisterValidation.checkCode(verifyCode)=="NO") {
                checkCode = "NO";
                err_code.setText("Verify Code is invalid");
                err_code.setStyle("-fx-text-fill: #ff1744");
            }
            else {
                checkCode = "YES";
                err_code.setText("");
            }
            if(checkCode == "YES") {
                try {
                    VerifyCodeStatement verifyCodeStatement = new VerifyCodeStatement();
                    ResultSet rs = verifyCodeStatement.checkCode(verifyCode);

                    if(rs.next()) {
                        //close window
                        Node node = (Node) e.getSource();
                        Stage thisStage = (Stage) node.getScene().getWindow();
                        thisStage.close();
                        //load login window
                        loadCreateNewPasswordWindow();
                    }else {
                        err_code.setText("Verify Code is invalid");
                        err_code.setStyle("-fx-text-fill: #ff1744");
                        txtCode.setText("");
                        txtCode.setStyle("-fx-border-color: #ff1744");

                        // set time to drop table tempCode after 1 minutes
                        Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(1), event -> {
                            // perform the action to be taken after 1 minutes
                            //close window
                            Node node = (Node) e.getSource();
                            Stage thisStage = (Stage) node.getScene().getWindow();
                            thisStage.close();

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error");
                            alert.setHeaderText("Your verification code has expired");
                            alert.setContentText("Please try again.");
                            // Get the DialogPane of the Alert object
                            DialogPane dialogPane = alert.getDialogPane();

                            // Set the button types for the dialog
                            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialogPane.getButtonTypes().setAll(okButton);

                            // Set an event handler for the OK button
                            Button okBtn = (Button) dialogPane.lookupButton(okButton);
                            okBtn.setOnAction(event1 -> {
                                // Perform the action to be taken when the OK button is clicked
                                // load Login
                                loadLoginWindow();
                            });
                            alert.show();
                        }));

                        timeline.play();
                    }
                }catch (SQLException ex) {
                    throw new RuntimeException(ex);
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

    public void loadChangePasswordWindow(){
        try {
            Stage changePasswordStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/forgotPassword/changepass.fxml"));
            Parent root = loader.load();
            Scene changePasswordScene = new Scene(root,600, 400);
            changePasswordStage.setTitle("Change Password");
            changePasswordStage.setScene(changePasswordScene);
            changePasswordStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadCreateNewPasswordWindow(){
        try{
            Stage createNewPasswordStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/User/createNewPassword.fxml"));
            Parent root = loader.load();
            CreateNewPasswordController createNewPasswordController = loader.getController();
            User user = new User();
            user.setId(Integer.parseInt(user_id.getText()));
            createNewPasswordController.getUser(user);

            Scene createNewPasswordScene = new Scene(root,600, 400);
            createNewPasswordStage.setTitle("Create New Password");
            createNewPasswordStage.setScene(createNewPasswordScene);
            createNewPasswordStage.show();
        }catch (IOException e) {
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
