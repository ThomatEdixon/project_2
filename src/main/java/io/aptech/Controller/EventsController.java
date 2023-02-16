package io.aptech.Controller;

import io.aptech.Entity.Events;
import io.aptech.Model.EventsStatement;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EventsController implements Initializable {
    @FXML private AnchorPane main_view_event;
    @FXML private Button btn_add_event;
    @FXML private ImageView events_back_page;
    @FXML private Label event_all;
    @FXML private Label events_running;
    @FXML private Label events_finished;
    @FXML private TableView<Events> tbl_events = new TableView<Events>();
    @FXML private TableColumn<Events,String> c_name;
    @FXML private TableColumn<Events,Integer> c_spent;
    @FXML private TableColumn<Events,Date> c_start;
    @FXML private TableColumn<Events,Date> c_end;
    private ObservableList<Events> events;
    private EventsStatement eventsStatement = new EventsStatement();

    public EventsController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        eventsStatement.create();
        events = eventsStatement.getAll();
        c_name.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
        c_spent.setCellValueFactory(new PropertyValueFactory<Events,Integer>("spent"));
        c_start.setCellValueFactory(new PropertyValueFactory<Events, Date>("startDate"));
        c_end.setCellValueFactory(new PropertyValueFactory<Events,Date>("endDate"));
        tbl_events.setItems(events);
        event_all.setOnMouseClicked(e->{
            events = eventsStatement.getAll();
            event_all.setStyle("-fx-text-fill: #000000");
            events_running.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #f0f0f0");
            c_name.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Events,Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Events, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Events,Date>("endDate"));
            tbl_events.setItems(events);
        });
        events_running.setOnMouseClicked(e->{
            events = eventsStatement.getRunning();
            event_all.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #f0f0f0");
            events_running.setStyle("-fx-text-fill: #000000");
            c_name.setCellValueFactory(new PropertyValueFactory<Events, String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Events, Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Events, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Events, Date>("endDate"));
            tbl_events.setItems(events);
        });
        events_finished.setOnMouseClicked(e->{
            events = eventsStatement.getFinished();
            events_running.setStyle("-fx-text-fill: #f0f0f0");
            event_all.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #000000");
            c_name.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Events,Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Events, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Events,Date>("endDate"));
            tbl_events.setItems(events);
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
            loader.setLocation(getClass().getResource("/Planning/planning.fxml"));
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
