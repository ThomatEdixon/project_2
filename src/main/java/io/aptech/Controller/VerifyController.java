package io.aptech.Controller;

import io.aptech.Model.ForgotPasswordStatement;
import io.aptech.Model.VerifyCodeStatement;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class VerifyController implements Initializable {
    @FXML private TextField txtCode;
    @FXML private Button btnVerifyCode;
    @FXML private Label err_code;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            }else {
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
                        loadChangePasswordWindow();
                    }
                }catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    public void loadChangePasswordWindow(){
        try {
            Stage changePasswordStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/forgotPassword/changepass.fxml"));
            Parent root = loader.load();
            Scene changePasswordScene = new Scene(root,320, 240);
            changePasswordStage.setTitle("Change Password");
            changePasswordStage.setScene(changePasswordScene);
            changePasswordStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
