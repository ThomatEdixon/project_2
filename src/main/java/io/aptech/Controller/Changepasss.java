package io.aptech.Controller;

import io.aptech.Model.ChangpassStatement;
import io.aptech.Validation.RegisterValidation;
import javafx.event.ActionEvent;
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

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class Changepasss  implements Initializable {
    @FXML private  TextField emailid;

    @FXML private TextField phoneid;
    @FXML private Label err_emailid;
    @FXML private  Label err_phoneid;
    @FXML private PasswordField passchange;
    @FXML private Button btchange;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    btchange.setOnAction(e->{
        String checkEmail = "YES";
        String checkPhone = "YES";
        String pass = passchange.getText();
        String email = emailid.getText();
        String phone = phoneid.getText();
        if (email.length()==0){
            checkEmail = "NO";
            err_emailid.setText("Email is required");
            err_emailid.setStyle("-fx-text-fill: #ff1744");
        } else if (RegisterValidation.checkEmail(email)=="NO") {
            checkEmail = "NO";
            err_emailid.setText("Email is invalid");
            err_emailid.setStyle("-fx-text-fill: #ff1744");
        }else {
            checkEmail = "YES";
            err_emailid.setText("");
        }
        //check phone
        if (phone.length()==0){
            checkPhone = "YES";
            err_phoneid.setText("Phone is required");
            err_phoneid.setStyle("-fx-text-fill: #ff1744");
        } else if (RegisterValidation.checkPhone(phone)=="NO") {
            checkPhone = "YES";
            err_phoneid.setText("Phone is invalid");
            err_phoneid.setStyle("-fx-text-fill: #ff1744");
        }else {
            checkPhone = "YES";
            err_phoneid.setText("");
        }
        if(checkPhone == "YES" && checkEmail == "YES") {
            try {
                ChangpassStatement CptStatement = new ChangpassStatement();
                ResultSet rss = CptStatement.checkk(email, phone);
                if (rss.next()) {
                    CptStatement.changepasszz(pass, email);
                    Node node = (Node) e.getSource();
                    Stage thisStage = (Stage) node.getScene().getWindow();
                    thisStage.close();
                    loadLoginWindoww();
                }
            } catch (Exception e5) {
                e5.printStackTrace();
            }
        }

    });
        }
    public void loadLoginWindoww(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("//login.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Login");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void Enter123(ActionEvent actionEvent) {
    }
}
