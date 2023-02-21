package io.aptech.Controller;

import io.aptech.Model.BillsStatement;
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
    @FXML private AnchorPane planning_bill;
    private static EventsStatement eventsStatement = new EventsStatement();
    private static BillsStatement billsStatement = new BillsStatement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        planning_event.setOnMouseClicked(e->{
            eventsStatement.create();
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
        planning_bill.setOnMouseClicked(e-> {
            billsStatement.create();
            // close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            if (billsStatement.getAll().size() >= 1) {
                loadBillWindow();
            } else {
                loadNullBillWindow();
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
    public void loadBillWindow(){
        try{
            Stage eventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Bill/bills.fxml"));
            Parent root = loader.load();
            Scene eventScene = new Scene(root,700,690);
            eventStage.setTitle("Bills");
            eventStage.setScene(eventScene);
            eventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadNullBillWindow(){
        try{
            Stage nullEventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Bill/nullBills.fxml"));
            Parent root = loader.load();
            Scene eventScene = new Scene(root,700,690);
            nullEventStage.setTitle("Bills");
            nullEventStage.setScene(eventScene);
            nullEventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
