package io.aptech.Controller;

import io.aptech.Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable {
    @FXML private Circle imageUserProfile;
    @FXML private Label lableUserProfile;
    @FXML private Label userProfileName;
    @FXML private FontIcon btnLogOut;
    @FXML private FontIcon iconUserProfile;
    @FXML private FontIcon btnUserProfile;
    @FXML private Label lableUserProfile2;
    @FXML private Label user_id;

    public void getUserById(User user) {
        user_id.setText(String.valueOf(user.getId()));
        userProfileName.setText(user.getFullName());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        imageUserProfile.setOnMousePressed(e -> {
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadUserProfileWindow();
        });

        btnLogOut.setOnMouseClicked(e -> {
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadLogOutWindow();
        });

        lableUserProfile.setOnMouseClicked(e -> {
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadUserProfileWindow();
        });

        userProfileName.setOnMouseClicked(e -> {
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadUserProfileWindow();
        });

        btnUserProfile.setOnMouseClicked(e -> {
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadUserProfileWindow();
        });

        iconUserProfile.setOnMouseClicked(e -> {
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadUserProfileWindow();
        });

        lableUserProfile2.setOnMouseClicked(e -> {
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadUserProfileWindow();
        });

    }

    public void loadUserProfileWindow() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainWindow/UserProfile.fxml"));
            Parent root = loader.load();
            UserProfileController userProfileController = loader.getController();
            User user = new User();
            user.setId(Integer.parseInt(user_id.getText()));
            userProfileController.getUser(user);
            Scene scene = new Scene(root, 545, 610);
            stage.setTitle("User Profile");
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadLogOutWindow() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/User/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 545, 610);
            stage.setTitle("User Profile");
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
