package io.aptech.Controller;

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
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML private FontIcon transaction;
    @FXML private FontIcon addNew;
    @FXML private FontIcon planning;
    @FXML private FontIcon accountUser;
    @FXML private FontIcon btnEye;
    @FXML private Label moneyBalance;
    private static int count = 0;

    public HomePageController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
            loader.setLocation(getClass().getResource("/io/aptech/project/hello-view.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Account User");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
