package io.aptech.Controller;

import io.aptech.Entity.Events;
import io.aptech.Model.EventsStatement;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class NullEventController implements Initializable {
    @FXML private Button btn_add_event;
    @FXML private ImageView events_back_page;
    @FXML private Label event_all;
    @FXML private Label events_running;
    @FXML private Label events_finished;
    private ObservableList<Events> events;
    private EventsStatement eventsStatement = new EventsStatement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        event_all.setOnMouseClicked(e->{
            event_all.setStyle("-fx-text-fill: #000000");
            events_running.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #f0f0f0");
        });
        events_running.setOnMouseClicked(e->{
            event_all.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #f0f0f0");
            events_running.setStyle("-fx-text-fill: #000000");
        });
        events_finished.setOnMouseClicked(e->{
            events_running.setStyle("-fx-text-fill: #f0f0f0");
            event_all.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #000000");
        });
        events_back_page.setOnMouseClicked(e->{
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadPlanningWindow();
        });
        btn_add_event.setOnAction(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadAddEventWindow();
        });
    }
    public void loadAddEventWindow(){
        try {
            Stage dddEventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Events/addEvent.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,600, 400);
            dddEventStage.setTitle("Add Event");
            dddEventStage.setScene(loginScene);
            dddEventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadPlanningWindow(){
        try {
            Stage planningStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Planning/planning.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,600, 400);
            planningStage.setTitle("Add Event");
            planningStage.setScene(loginScene);
            planningStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
