package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Model.BillsStatement;
import io.aptech.Model.EventsStatement;
import io.aptech.Model.MoneyPlanStatement;
import io.aptech.Model.UserStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlanningController implements Initializable {
    @FXML private AnchorPane planning_event;
    @FXML private AnchorPane planning_bill;
    @FXML private AnchorPane planning_budget;
    @FXML private AnchorPane planning_recurring;
    @FXML private FontIcon transaction;
    @FXML private FontIcon addNew;
    @FXML private FontIcon planning;
    @FXML private FontIcon accountUser;
    @FXML private FontIcon home;
    @FXML private FontIcon icon_back_page;
    @FXML private Label user_id;
    private static EventsStatement eventsStatement = new EventsStatement();
    private static BillsStatement billsStatement = new BillsStatement();
    private static MoneyPlanStatement moneyPlanStatement = new MoneyPlanStatement();
    public void getUser(User user){
        user_id.setText(String.valueOf(user.getId()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        icon_back_page.setOnMouseClicked(e->{
            // close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            loadHomeWindow();
        });
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
        planning_recurring.setOnMouseClicked(e->{
            moneyPlanStatement.create();
            // close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            loadBudgetWindow();
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

    public void loadEventWindow(){
        try{
            Stage eventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Events/events.fxml"));
            Parent root = loader.load();
            EventsController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
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
            NullEventController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
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
            BillsController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
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
            NullBillController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
            nullEventStage.setTitle("Bills");
            nullEventStage.setScene(eventScene);
            nullEventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadBudgetWindow(){
        try{
            Stage nullEventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/spendingPlan/plan.fxml"));
            Parent root = loader.load();
            Scene eventScene = new Scene(root,600,400);
            nullEventStage.setTitle("Bills");
            nullEventStage.setScene(eventScene);
            nullEventStage.show();
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
            UserStatement userStatement = new UserStatement();
            User user = userStatement.getUserById(Integer.parseInt(user_id.getText()));
            user.setId(user.getId());
            user.setFullName(user.getFullName());
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
