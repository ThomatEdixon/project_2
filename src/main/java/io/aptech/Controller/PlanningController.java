package io.aptech.Controller;

import io.aptech.Model.EventsStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlanningController implements Initializable {
    @FXML private AnchorPane planning_event;
    private static EventsStatement eventsStatement = new EventsStatement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        planning_event.setOnMouseClicked(e->{
            // close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            if(eventsStatement.getAll() != null){
                loadEventWindow();
            }else {
                loadNullEventWindow();
            }
        });
    }
    public void loadEventWindow(){
        try{
            Stage eventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Events/events.fxml"));
            Parent root = loader.load();
            Scene eventScene = new Scene(root,700,690);
            eventStage.setTitle("Events");
            eventStage.setScene(eventScene);
            eventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadNullEventWindow(){
        try{
            Stage nullEventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Events/nullevents.fxml"));
            Parent root = loader.load();
            Scene eventScene = new Scene(root,700,690);
            nullEventStage.setTitle("Events");
            nullEventStage.setScene(eventScene);
            nullEventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
