package io.aptech.Controller;

import io.aptech.Entity.Events;
import io.aptech.Model.AddBudgetStatement;
import io.aptech.Model.AddEventStatement;
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
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.Date;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {
    @FXML private TextField addEventName;
    @FXML private TextField addEventSpent;
    @FXML private DatePicker startDateEvent;
    @FXML private DatePicker endDateEvent;
    @FXML private Button btn_addEvent;
    @FXML private ImageView exits_addevent;
    @FXML private Label err_add_name;
    @FXML private Label err_add_spent;
    @FXML private Label err_add_startdate;
    @FXML private Label err_add_enddate;
    @FXML private Label user_id;
    private static AddEventStatement addEventStatement = new AddEventStatement();
    private static AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
    public void getUserId(int id){
        user_id.setText(String.valueOf(id));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        exits_addevent.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                //load login window
                loadEventWindow();
            }
        });
        btn_addEvent.setOnAction(e->{
            String eventName  = addEventName.getText();
            String eventSpent = addEventSpent.getText();
            Date eventStartDate = Date.valueOf("1000-01-28");
            Date eventEndDate = Date.valueOf("1000-01-28");
            int balance = addBudgetStatement.getBalance(Integer.parseInt(user_id.getText()));
            if(startDateEvent.getValue() != null){
                eventStartDate = Date.valueOf(startDateEvent.getValue());
            }
            if(endDateEvent.getValue() != null){
                eventEndDate = Date.valueOf(endDateEvent.getValue());
            }
            String checkEventName = "YES";
            String checkEventSpent = "YES";
            String checkEventStart = "YES";
            String checkEventEnd = "YES";
            if(eventName.length()<1){
                checkEventName = "NO";
                err_add_name.setText("Event name is required");
                err_add_name.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkEventName = "YES";
                err_add_name.setText("");
            }
            if(eventSpent.length()<1){
                checkEventSpent = "NO";
                err_add_spent.setText("Event spent is required");
                err_add_spent.setStyle("-fx-text-fill: #ff1744");
            }else if (balance < Integer.parseInt(eventSpent)) {
                checkEventSpent = "NO";
                err_add_spent.setText("Balance not enough");
                err_add_spent.setStyle("-fx-text-fill: #ff1744");
            }else {
                balance -= Integer.parseInt(eventSpent);
                checkEventSpent = "YES";
                err_add_spent.setText("");
            }
            if(eventStartDate.equals(Date.valueOf("1000-01-28")) ){
                checkEventStart = "NO";
                err_add_startdate.setText("Event start date is required");
                err_add_startdate.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkEventStart = "YES";
                err_add_startdate.setText("");
            }
            if(eventEndDate.equals(Date.valueOf("1000-01-28"))){
                checkEventEnd = "NO";
                err_add_enddate.setText("Event end date is required");
                err_add_enddate.setStyle("-fx-text-fill: #ff1744");
            }else {
                checkEventEnd = "YES";
                err_add_enddate.setText("");
            }
            if(checkEventName == "YES" && checkEventSpent == "YES" && checkEventStart == "YES" && checkEventEnd == "YES"){
                Events event = new Events();
                event.setName(eventName);
                event.setSpent(Integer.parseInt(eventSpent));
                event.setStartDate(eventStartDate);
                event.setEndDate(eventEndDate);
                addEventStatement.insert(event);
                // update balance
                addBudgetStatement.updateBalance(balance);
                //close window
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                //load login window
                loadEventWindow();
            }
        });
    }
    public void loadEventWindow(){
        try {
            Stage eventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Events/events.fxml"));
            Parent root = loader.load();
            Scene eventScene = new Scene(root,730, 690);
            EventsController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
            eventStage.setTitle("Events");
            eventStage.setScene(eventScene);
            eventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
