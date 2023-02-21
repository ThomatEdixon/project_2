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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class BillsController implements Initializable {
    @FXML
    private Button btn_add_bill;
    @FXML private ImageView events_back_page;
    @FXML private Label bill_all;
    @FXML private Label bills_running;
    @FXML private Label bills_finished;
    @FXML private TableView<Bills> tbl_bills = new TableView<Bills>();
    @FXML private TableColumn<Bills,String> c_name;
    @FXML private TableColumn<Bills,Integer> c_spent;
    @FXML private TableColumn<Bills,Date> c_start;
    @FXML private TableColumn<Bills,Date> c_end;
    @FXML private TableColumn<Bills,Integer> c_id;
    private ObservableList<Bills> bills;
    private static BillsStatement billsStatement = new BillsStatement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        billsStatement.create();
        bills = billsStatement.getAll();
        tbl_bills.getColumns().get(4).setVisible(false);
        c_name.setCellValueFactory(new PropertyValueFactory<Bills,String>("name"));
        c_spent.setCellValueFactory(new PropertyValueFactory<Bills,Integer>("spent"));
        c_start.setCellValueFactory(new PropertyValueFactory<Bills, Date>("startDate"));
        c_end.setCellValueFactory(new PropertyValueFactory<Bills,Date>("endDate"));
        c_id.setCellValueFactory(new PropertyValueFactory<Bills,Integer>("id"));
        tbl_bills.setItems(bills);
        bill_all.setOnMouseClicked(e->{
            bills = billsStatement.getAll();
            bill_all.setStyle("-fx-text-fill: #000000");
            bills_running.setStyle("-fx-text-fill: #f0f0f0");
            bills_finished.setStyle("-fx-text-fill: #f0f0f0");
            tbl_bills.getColumns().get(4).setVisible(false);
            c_name.setCellValueFactory(new PropertyValueFactory<Bills,String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Bills,Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Bills, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Bills,Date>("endDate"));
            c_id.setCellValueFactory(new PropertyValueFactory<Bills,Integer>("id"));
            tbl_bills.setItems(bills);
        });
        bills_running.setOnMouseClicked(e->{
            bills = billsStatement.getRunning();
            bill_all.setStyle("-fx-text-fill: #f0f0f0");
            bills_finished.setStyle("-fx-text-fill: #f0f0f0");
            bills_running.setStyle("-fx-text-fill: #000000");
            tbl_bills.getColumns().get(4).setVisible(false);
            c_name.setCellValueFactory(new PropertyValueFactory<Bills,String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Bills,Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Bills, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Bills,Date>("endDate"));
            c_id.setCellValueFactory(new PropertyValueFactory<Bills,Integer>("id"));
            tbl_bills.setItems(bills);
        });
        bills_finished.setOnMouseClicked(e->{
            bills = billsStatement.getFinished();
            bills_running.setStyle("-fx-text-fill: #f0f0f0");
            bill_all.setStyle("-fx-text-fill: #f0f0f0");
            bills_finished.setStyle("-fx-text-fill: #000000");
            tbl_bills.getColumns().get(4).setVisible(false);
            c_name.setCellValueFactory(new PropertyValueFactory<Bills,String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Bills,Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Bills, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Bills,Date>("endDate"));
            c_id.setCellValueFactory(new PropertyValueFactory<Bills,Integer>("id"));
            tbl_bills.setItems(bills);
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
        tbl_bills.setOnMouseClicked(e ->{
            try{
                Bills editBills = tbl_bills.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Bill/editBill.fxml"));
                Pane viewEdit = loader.load();
                Scene scene = new Scene(viewEdit);
                EditBillController editBillController = loader.getController();
                editBillController.getBills(editBills);
                stage.setTitle("Edit Bill");
                stage.setScene(scene);
                stage.show();
                // close window
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
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
