package io.aptech.Controller;

import io.aptech.Entity.Bills;
import io.aptech.Entity.Events;
import io.aptech.Model.AddBillStatement;
import io.aptech.Model.AddBudgetStatement;
import io.aptech.Model.AddEventStatement;
import io.aptech.Model.BillsStatement;
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
import java.util.ResourceBundle;

public class AddBillController implements Initializable {
    @FXML private TextField addBillName;
    @FXML private TextField addBillSpent;
    @FXML private DatePicker startDateBill;
    @FXML private DatePicker endDateBill;
    @FXML private Button btn_addBill;
    @FXML private ImageView exits_addbills;
    @FXML private Label err_add_name;
    @FXML private Label err_add_spent;
    @FXML private Label err_add_startdate;
    @FXML private Label err_add_enddate;
    @FXML private Label user_id;
    private static AddBillStatement addBillStatement = new AddBillStatement();
    private static AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
    private static int balance = 0;
    public void getUserId(int id){
        user_id.setText(String.valueOf(id));
        balance = addBudgetStatement.getBalance(id);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        exits_addbills.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node node = (Node) event.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                //load login window
                loadBillWindow();
            }
        });
        btn_addBill.setOnAction(e->{
            String eventName  = addBillName.getText();
            String eventSpent = addBillSpent.getText();
            Date eventStartDate = Date.valueOf("1000-01-28");
            Date eventEndDate = Date.valueOf("1000-01-28");
            if(startDateBill.getValue() != null){
                eventStartDate = Date.valueOf(startDateBill.getValue());
            }
            if(endDateBill.getValue() != null){
                eventEndDate = Date.valueOf(endDateBill.getValue());
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
            } else if (balance < Integer.parseInt(eventSpent)) {
                checkEventSpent = "NO";
                err_add_spent.setText("Balance not enough");
                err_add_spent.setStyle("-fx-text-fill: #ff1744");
            } else {
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
                Bills bills = new Bills();
                bills.setName(eventName);
                bills.setSpent(Integer.parseInt(eventSpent));
                bills.setStartDate(eventStartDate);
                bills.setEndDate(eventEndDate);
                addBillStatement.insert(bills);
                // update balance
                addBudgetStatement.updateBalance(balance);
                //close window
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                //load login window
                loadBillWindow();
            }
        });
    }
    public void loadBillWindow(){
        try {
            Stage eventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Bill/bills.fxml"));
            Parent root = loader.load();
            Scene eventScene = new Scene(root,730, 690);
            BillsController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
            eventStage.setTitle("Events");
            eventStage.setScene(eventScene);
            eventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
