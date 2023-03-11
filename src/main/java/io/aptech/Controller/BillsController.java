package io.aptech.Controller;

import io.aptech.Entity.Bills;
import io.aptech.Entity.User;
import io.aptech.Model.BillsStatement;
import io.aptech.Model.UserStatement;
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
import org.kordamp.ikonli.javafx.FontIcon;

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
    @FXML private FontIcon transaction;
    @FXML private FontIcon addNew;
    @FXML private FontIcon planning;
    @FXML private FontIcon accountUser;
    @FXML private FontIcon home;
    @FXML private TableView<Bills> tbl_bills = new TableView<Bills>();
    @FXML private TableColumn<Bills,String> c_name;
    @FXML private TableColumn<Bills,Integer> c_spent;
    @FXML private TableColumn<Bills,Date> c_start;
    @FXML private TableColumn<Bills,Date> c_end;
    @FXML private TableColumn<Bills,Integer> c_id;
    private ObservableList<Bills> bills;
    @FXML private Label user_id;
    private static BillsStatement billsStatement = new BillsStatement();
    public void getUserId(int id){
        user_id.setText(String.valueOf(id));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
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
                editBillController.getUserId(Integer.parseInt(user_id.getText()));
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
        home.setOnMouseClicked(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            loadHomeWindow();
        });
        transaction.setOnMouseClicked(event -> {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadTransactionWindow();
        });

        addNew.setOnMouseClicked(event -> {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadAddNewWindow();
        });

        planning.setOnMouseClicked(event -> {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadPlanningWindow();
        });

        accountUser.setOnMouseClicked(event -> {
            Node node = (Node) event.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //Loading Main Widows
            loadAccountUserWindow();
        });
    }
    public void loadAddBillWindow(){
        try {
            Stage addBillStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Bill/addBill.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,600, 400);
            AddBillController addBillController = loader.getController();
            addBillController.getUserId(Integer.parseInt(user_id.getText()));
            addBillStage.setTitle("Add Bill");
            addBillStage.setScene(loginScene);
            addBillStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadHomeWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainWindow/homePage.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,700, 690);
            HomePageController controller = loader.getController();
            User user = new User();
            user.setId(Integer.parseInt(user_id.getText()));
            controller.getUser(user);
            loginStage.setTitle("Home Page");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadTransactionWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Transactions/listTransactions.fxml"));
            Parent root = loader.load();
            ListTransactionsController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
            Scene loginScene = new Scene(root,850, 640);
            loginStage.setTitle("transactions");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadAddNewWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Transactions/transaction.fxml"));
            Parent root = loader.load();
            AddTransactionsController transactionsController = loader.getController();
            transactionsController.getUserId(Integer.parseInt(user_id.getText()));
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Add New");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadPlanningWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Planning/planning.fxml"));
            Parent root = loader.load();
            PlanningController planningController = loader.getController();
            User user = new User();
            user.setId(Integer.parseInt(user_id.getText()));
            planningController.getUser(user);
            Scene loginScene = new Scene(root,730, 690);
            loginStage.setTitle("Planning");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadAccountUserWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainWindow/UserManagement.fxml"));
            Parent root = loader.load();
            UserManagementController userManagementController = loader.getController();
            UserStatement userStatement = new UserStatement();
            User user = userStatement.getUserById(Integer.parseInt(user_id.getText()));
            user.setId(user.getId());
            user.setFullName(user.getFullName());
            userManagementController.getUserById(user);
            Scene loginScene = new Scene(root,695, 770);
            loginStage.setTitle("Account User");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
