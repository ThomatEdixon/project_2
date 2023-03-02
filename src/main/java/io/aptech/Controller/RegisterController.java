package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Enum.UserGender;
import io.aptech.Model.UserStatement;
import io.aptech.Validation.RegisterValidation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.mindrot.jbcrypt.BCrypt;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class RegisterController implements Initializable{
    @FXML private TextField res_fullname;
    @FXML private RadioButton res_male;
    @FXML private RadioButton res_female;
    @FXML private PasswordField res_password;
    @FXML private TextField res_email;
    @FXML private TextField res_phone;
    @FXML private Label err_fullname;
    @FXML private Label err_gender;
    @FXML private Label err_password;
    @FXML private Label err_email;
    @FXML private Label err_phone;
    @FXML private Button btn_register;

    @FXML private Circle avatarCircle;
    @FXML private FontIcon btnCamera;

    private final ToggleGroup gender = new ToggleGroup();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Image Avatar User
        AtomicReference<String> imagePath = new AtomicReference<>(String.valueOf(getClass().getResource("/Image/account.png")));
        Image image = new Image(imagePath.get(), false);
        avatarCircle.setFill(new ImagePattern(image));

        // Button Gender
        res_male.setToggleGroup(gender);
        res_female.setToggleGroup(gender);
        gender.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if(gender.getSelectedToggle() == res_male){
                    res_male.setSelected(true);
                }else {
                    res_female.setSelected(true);
                }
            }
        });

        // Register Avatar User
        avatarCircle.setOnMouseClicked(event -> {
            FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("Image Files ", "*.png");
            FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("All Files", "*.jpeg", "*.gif", "*.bmp", "*.webp", "*.jpg");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Avatar");
            fileChooser.getExtensionFilters().addAll(ex1, ex2);
            File selectedFile = fileChooser.showOpenDialog(btnCamera.getScene().getWindow());
            if(selectedFile!= null){
                imagePath.set(selectedFile.getAbsolutePath());;
                avatarCircle.setFill(new ImagePattern(new Image(imagePath.get(), false)));
                avatarCircle.setViewOrder(1);
                avatarCircle.setOpacity(0.8);
            }else {
//              String imagePath = String.valueOf(getClass().getResource("/Image/account.png"));
//              Image image = new Image(imagePath, false);
            }
        });

        btnCamera.setOnMouseClicked(event -> {
            FileChooser.ExtensionFilter ex1 = new FileChooser.ExtensionFilter("Image Files ", "*.png");
            FileChooser.ExtensionFilter ex2 = new FileChooser.ExtensionFilter("All Files", "*.jpeg", "*.gif", "*.bmp", "*.webp", "*.jpg");
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Avatar");
            fileChooser.getExtensionFilters().addAll(ex1, ex2);
            File selectedFile = fileChooser.showOpenDialog(btnCamera.getScene().getWindow());
            if(selectedFile!= null){
                imagePath.set(selectedFile.getAbsolutePath());;
                avatarCircle.setFill(new ImagePattern(new Image(imagePath.get(), false)));
                avatarCircle.setViewOrder(1);
                avatarCircle.setOpacity(0.8);
            }else {
//              String imagePath = String.valueOf(getClass().getResource("/Image/account.png"));
//              Image image = new Image(imagePath, false);
            }
        });

        btn_register.setOnAction(e->{
            String resFullName = res_fullname.getText();
            RadioButton resMale = res_male;
            RadioButton resFemale = res_female;
            String resPassword = res_password.getText();
            String resEmail = res_email.getText();
            String resPhone = res_phone.getText();
            Circle resAvatarCircle = avatarCircle;
            String passwordEncoder = BCrypt.hashpw(resPassword,BCrypt.gensalt(12));
            String checkFullName = "YES";
            String checkGender = "YES";
            String checkEmail = "YES";
            String checkPassword = "YES";
            String checkPhone = "YES";
            String checkAvatar = "YES";
            // check full name
            if(resFullName.length() == 0 ){
                err_fullname.setText("Full name is required ");
                err_fullname.setStyle("-fx-text-fill: #ff1744");
                checkFullName = "NO";
            } else if (RegisterValidation.checkFullName(resFullName)=="NO") {
                err_fullname.setText("Full name required 6-50 characters");
                err_fullname.setStyle("-fx-text-fill: #ff1744");
                checkFullName = "NO";
            }else{
                checkFullName = "YES";
                err_fullname.setText("");

            }
            //check gender
            if(!(resMale.isSelected()) && !(resFemale.isSelected())){
                err_gender.setText("Gender is required ");
                err_gender.setStyle("-fx-text-fill: #ff1744");
                checkGender = "NO";
            }else{
                checkGender = "YES";
                err_gender.setText("");
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

            // check Path Avatar
//          if(resAvatarCircle.con) {
//
//          }
            //check error before register and insert

            if(checkFullName=="YES" && checkGender=="YES" && checkPassword=="YES" && checkEmail=="YES"&& checkPhone=="YES"){
                User n_user = new User();
                n_user.setFullName(resFullName);
                if(resMale.isSelected()){
                    resMale.setUserData(UserGender.MALE);
                    n_user.setGender(UserGender.MALE);
                }else {
                    resFemale.setUserData(UserGender.FEMALE);
                    n_user.setGender(UserGender.FEMALE);
                }
                n_user.setPassword(passwordEncoder);
                n_user.setEmail(resEmail);
                n_user.setPhone(resPhone);
                n_user.setImage(imagePath.get());
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