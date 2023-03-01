package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Enum.UserGender;
import io.aptech.Model.UserProfileStatement;
import io.aptech.Model.UserStatement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;

public class UserProfileController implements Initializable {
    @FXML private TextField acc_fullname;
    @FXML private RadioButton gender_male;
    @FXML private RadioButton gender_female;
    @FXML private TextField acc_email;
    @FXML private TextField acc_phone;
    @FXML private Button acc_edit;
    @FXML private Circle avatarCircle;
    @FXML private FontIcon btnCamera;
    @FXML private FontIcon icon_back_page;
    @FXML private Label user_id;
    private static UserProfileStatement userProfileStatement = new UserProfileStatement();

    private final ToggleGroup gender = new ToggleGroup();

    public void getUserById(User user) {
        user_id.setText(String.valueOf(user.getId()));
    }

    public void getUser(User user) {
        User user1 = userProfileStatement.getUserById(user.getId());
        user_id.setText(String.valueOf(user1.getId()));
        acc_fullname.setText(user1.getFullName());

        if(user1.getGender() == UserGender.MALE) {
            gender_male.setSelected(true);
        }else {
            gender_female.setSelected(true);
        }
        acc_email.setText(user1.getEmail());
        acc_phone.setText(user1.getPhone());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        icon_back_page.setOnMouseClicked(e->{
            // close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            loadAccountUserWindow();
        });
        AtomicReference<String> imagePath = new AtomicReference<>(String.valueOf(getClass().getResource("/Image/account.png")));
        Image image = new Image(imagePath.get(), false);
        avatarCircle.setFill(new ImagePattern(image));

        // Button Gender
        gender_male.setToggleGroup(gender);
        gender_female.setToggleGroup(gender);
        gender.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
                if(gender.getSelectedToggle() == gender_male){
                    gender_male.setSelected(true);
                }else {
                    gender_female.setSelected(true);
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

        acc_edit.setOnMouseClicked(event -> {
            User newUser = new User();
            newUser.setId(Integer.parseInt(user_id.getText()));
            newUser.setImage(imagePath.get());
            newUser.setFullName(acc_fullname.getText());
            if(gender_male.isSelected()){
                gender_male.setUserData(UserGender.MALE);
                newUser.setGender(UserGender.MALE);
            }else {
                gender_female.setUserData(UserGender.FEMALE);
                newUser.setGender(UserGender.FEMALE);
            }
            newUser.setEmail(acc_email.getText());
            newUser.setPhone(acc_phone.getText());
            userProfileStatement.update(newUser);

            //close window
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadHomePageWindow();
        });
    }

    public void loadHomePageWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainWindow/homePage.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Home Page");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
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