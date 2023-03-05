package io.aptech.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SpendingManagementApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("/User/login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 700, 510);
        scene.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
        stage.setTitle("Login");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}