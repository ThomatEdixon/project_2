package io.aptech.Controller;

import io.aptech.Entity.Events;
import io.aptech.Model.EditStatement;
import io.aptech.Model.EventsStatement;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class EditEventController implements Initializable {
    @FXML
    private  TextField editEventName;
    @FXML private TextField editEventSpent;
    @FXML private DatePicker editStartDateEvent;
    @FXML private DatePicker editEndDateEvent;
    @FXML private Button btn_editEvent;
    @FXML private Button btn_deleteEvent;
    @FXML private ImageView exits_editevent;
    @FXML private Label edit_id;
    @FXML private Label err_edit_name;
    @FXML private Label err_edit_spent;
    @FXML private Label err_edit_startdate;
    @FXML private Label err_edit_enddate;
    private static EditStatement editStatement = new EditStatement();
    private static EventsStatement eventsStatement = new EventsStatement();
    public void getEvents(Events event){
        edit_id.setText(String.valueOf(event.getId()));
        editEventName.setText(event.getName());
        editEventSpent.setText(String.valueOf(event.getSpent()));
        editStartDateEvent.setValue(asLocalDate(event.getStartDate()));
        editEndDateEvent.setValue(asLocalDate(event.getEndDate()));
    }
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exits_editevent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
            }
        });
        btn_editEvent.setOnAction(e->{
            int id = Integer.parseInt(edit_id.getText());
            String eventName  = editEventName.getText();
            String eventSpent = editEventSpent.getText();
            Date eventStartDate = Date.valueOf("1000-01-28");
            Date eventEndDate = Date.valueOf("1000-01-28");
            if(editStartDateEvent.getValue() != null){
                eventStartDate = Date.valueOf(editStartDateEvent.getValue());
            }
            if(editEndDateEvent.getValue() != null){
                eventEndDate = Date.valueOf(editEndDateEvent.getValue());
            }
            String checkEventName = "YES";
            String checkEventSpent = "YES";
            String checkEventStart = "YES";
            String checkEventEnd = "YES";
            if(eventName.length()<1){
                checkEventName = "NO";
                err_edit_name.setText("Event name is required");
                err_edit_name.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkEventName = "YES";
                err_edit_name.setText("");
            }
            if(eventSpent.length()<1){
                checkEventSpent = "NO";
                err_edit_spent.setText("Event spent is required");
                err_edit_spent.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkEventSpent = "YES";
                err_edit_spent.setText("");
            }
            if(eventStartDate.equals(Date.valueOf("1000-01-28")) ){
                checkEventStart = "NO";
                err_edit_startdate.setText("Event start date is required");
                err_edit_startdate.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkEventStart = "YES";
                err_edit_startdate.setText("");
            }
            if(eventEndDate.equals(Date.valueOf("1000-01-28"))){
                checkEventEnd = "NO";
                err_edit_enddate.setText("Event end date is required");
                err_edit_enddate.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkEventEnd = "YES";
                err_edit_enddate.setText("");
            }
            if(checkEventName == "YES" && checkEventSpent == "YES" && checkEventStart == "YES" && checkEventEnd == "YES"){
                Events event = new Events();
                event.setName(eventName);
                event.setSpent(Integer.parseInt(eventSpent));
                event.setStartDate(eventStartDate);
                event.setEndDate(eventEndDate);
                event.setId(id);
                editStatement.update(event);
                //close window
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                //load window
                loadEventWindow();
            }
        });
        btn_deleteEvent.setOnAction(e->{
            Events event = new Events();
            event.setId(Integer.parseInt(edit_id.getText()));
            editStatement.delete(event);
            // close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            if(eventsStatement.getAll().size() >= 1){
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
            loader.setLocation(getClass().getResource("/Events/nullEvents.fxml"));
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
