package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Model.UserStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML private FontIcon transaction;
    @FXML private FontIcon addNew;
    @FXML private FontIcon planning;
    @FXML private FontIcon accountUser;
    @FXML private FontIcon btnEye;
    @FXML private Label moneyBalance;
    @FXML private Label user_name;
    @FXML private Label user_id;
    private static int count = 0;
    public HomePageController() {
    }

    public void getUser(User user) {
        user_id.setText(String.valueOf(user.getId()));
        user_name.setText("Hello " + user.getFullName());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);

        btnEye.setOnMouseClicked(event -> {
            count+=1;
            if(count%2!=0){
                moneyBalance.setText("******** VND");
                btnEye.setIconLiteral("fas-eye-slash");
            } else {
                moneyBalance.setText("500.000 VND");
                btnEye.setIconLiteral("fas-eye");
            }
        });

        transaction.setOnMouseClicked(event -> {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadTransactionWindow();
        });

        addNew.setOnMouseClicked(event -> {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadAddNewWindow();
        });

        planning.setOnMouseClicked(event -> {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadPlanningWindow();
        });

        accountUser.setOnMouseClicked(event -> {
            User user1 = new User();

            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadAccountUserWindow();
        });
    }

    public void loadTransactionWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/aptech/project/hello-view.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("transactions");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadAddNewWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/aptech/project/hello-view.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Add New");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadPlanningWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/io/aptech/project/hello-view.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Planning");
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
            User user = new User();
            user.setId(Integer.parseInt(user_id.getText()));
            String info[] = user_name.getText().split(" ");
            user.setFullName(info[1]);
            userManagementController.getUserById(user);
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Account User");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
