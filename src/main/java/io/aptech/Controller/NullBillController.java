package io.aptech.Controller;

import io.aptech.Entity.Bills;
import io.aptech.Model.BillsStatement;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NullBillController implements Initializable {
    @FXML private Button btn_add_bill;
    @FXML private ImageView events_back_page;
    @FXML private Label bill_all;
    @FXML private Label bills_running;
    @FXML private Label bills_finished;
    private ObservableList<Bills> bills;
    private static BillsStatement billsStatement = new BillsStatement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bill_all.setOnMouseClicked(e->{
            bill_all.setStyle("-fx-text-fill: #000000");
            bills_running.setStyle("-fx-text-fill: #f0f0f0");
            bills_finished.setStyle("-fx-text-fill: #f0f0f0");
        });
        bills_running.setOnMouseClicked(e->{
            bill_all.setStyle("-fx-text-fill: #f0f0f0");
            bills_finished.setStyle("-fx-text-fill: #f0f0f0");
            bills_running.setStyle("-fx-text-fill: #000000");
        });
        bills_finished.setOnMouseClicked(e->{
            bills_running.setStyle("-fx-text-fill: #f0f0f0");
            bill_all.setStyle("-fx-text-fill: #f0f0f0");
            bills_finished.setStyle("-fx-text-fill: #000000");
        });
        events_back_page.setOnMouseClicked(e->{
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadPlanningWindow();
        });
        btn_add_bill.setOnAction(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadAddBillWindow();
        });
    }
    public void loadAddBillWindow(){
        try {
            Stage dddEventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Bill/addBill.fxml"));
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
            Scene loginScene = new Scene(root,730, 670);
            planningStage.setTitle("Planning");
            planningStage.setScene(loginScene);
            planningStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
