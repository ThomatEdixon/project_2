package io.aptech.Controller;

import io.aptech.Entity.Category;
import io.aptech.Entity.Transactions;
import io.aptech.Entity.User;
import io.aptech.Model.AddBudgetStatement;
import io.aptech.Model.TransactionStatement;
import io.aptech.Model.UserStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class ListTransactionsController implements Initializable {
    @FXML private FontIcon icon_back_page;
    @FXML private FontIcon icon_search;
    @FXML private FontIcon transaction;
    @FXML private FontIcon addNew;
    @FXML private FontIcon planning;
    @FXML private FontIcon accountUser;
    @FXML private FontIcon home;
    @FXML private Label balance;
    @FXML private Label last4;
    @FXML private Label last3;
    @FXML private Label last2;
    @FXML private Label last1;
    @FXML private Label last;
    @FXML private Label now;
    @FXML private Label future;
    @FXML private Label user_id;
    @FXML private Label null1;
    @FXML private Label null2;
    @FXML private TableView<Transactions> tbl_transaction;
    @FXML private TableColumn<Transactions , String> tran_description;
    @FXML private TableColumn<Transactions , Date> tran_date;
    @FXML private TableColumn<Transactions , Integer > tran_amount;
    @FXML private TableColumn<Transactions , Category> tran_category;
    private static Calendar cal = Calendar.getInstance();
    private static String date;
    private static UserStatement userStatement = new UserStatement();
    private static ObservableList<Transactions> transactions = FXCollections.observableArrayList();
    private static AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
    private static TransactionStatement transactionStatement = new TransactionStatement();

    public void getUserId(int id){
        user_id.setText(String.valueOf(id));
        int n_balance = addBudgetStatement.getBalance(id);
        String type = addBudgetStatement.getType(id);
        balance.setText(n_balance +" "+type);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        now.setText("This Month");
        last.setText("Last Month");
        future.setText("Future");
        if((month-2)<=0){
            last1.setText("11"+"/"+(year-1));
        }else {
            last1.setText((month-1)+"/"+year);
        }
        if((month-3)<=0){
            last2.setText("10"+"/"+(year-1));
        }else {
            last2.setText((month-2)+"/"+year);
        }
        if((month-4)<=0){
            last3.setText("9"+"/"+year);
        }else {
            last3.setText((month-3)+"/"+year);
        }
        if((month-5)<=0){
            last4.setText("8"+"/"+year);
        }else {
            last4.setText((month-4)+"/"+year);
        }
        date = cal.get(Calendar.YEAR)+"-"+"0"+cal.get(Calendar.MONTH);
        transactions = transactionStatement.getTransactions(date,Integer.parseInt(user_id.getText()));
        if(transactions.size() <1 ){
            null1.setVisible(true);
            null2.setVisible(true);
            tbl_transaction.setVisible(false);
        }else{
            null1.setVisible(false);
            null2.setVisible(false);
            tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
            tran_date.setCellValueFactory(new PropertyValueFactory<Transactions,Date>("date"));
            tran_amount.setCellValueFactory(new PropertyValueFactory<Transactions,Integer>("amount"));
            tran_category.setCellValueFactory(new PropertyValueFactory<Transactions,Category>("category"));
            tbl_transaction.setItems(transactions);
        }
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
        icon_search.setOnMouseClicked(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            loadSearchWindow();
        });
        future.setOnMouseClicked(e->{
        });
        now.setOnMouseClicked(e->{
            now.setStyle("-fx-text-fill: #000000");
            future.setStyle("-fx-text-fill: #d7d1d1");
            last.setStyle("-fx-text-fill: #d7d1d1");
            last1.setStyle("-fx-text-fill: #d7d1d1");
            last2.setStyle("-fx-text-fill: #d7d1d1");
            last3.setStyle("-fx-text-fill: #d7d1d1");
            last4.setStyle("-fx-text-fill: #d7d1d1");
            date = cal.get(Calendar.YEAR)+"-"+"0"+cal.get(Calendar.MONTH);
            transactions = transactionStatement.getTransactions(date,Integer.parseInt(user_id.getText()));
            if(transactions.size() <1 ){
                null1.setVisible(true);
                null2.setVisible(true);
                tbl_transaction.setVisible(false);
            }else{
                null1.setVisible(false);
                null2.setVisible(false);
                tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
                tran_date.setCellValueFactory(new PropertyValueFactory<Transactions,Date>("date"));
                tran_amount.setCellValueFactory(new PropertyValueFactory<Transactions,Integer>("amount"));
                tran_category.setCellValueFactory(new PropertyValueFactory<Transactions,Category>("category"));
                tbl_transaction.setItems(transactions);
            }
        });
        last.setOnMouseClicked(e->{
            now.setStyle("-fx-text-fill: #d7d1d1");
            future.setStyle("-fx-text-fill: #d7d1d1");
            last.setStyle("-fx-text-fill:#000000");
            last1.setStyle("-fx-text-fill: #d7d1d1");
            last2.setStyle("-fx-text-fill: #d7d1d1");
            last3.setStyle("-fx-text-fill: #d7d1d1");
            last4.setStyle("-fx-text-fill: #d7d1d1");
            if((cal.get(Calendar.MONTH)-1)<0){
                date = cal.get(Calendar.YEAR)+"-"+"0"+(cal.get(Calendar.MONTH)-1);
            }else{
                date = (cal.get(Calendar.YEAR)-1)+"-"+"12";
            }
            transactions = transactionStatement.getTransactions(date,Integer.parseInt(user_id.getText()));tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
            if(transactions.size() <1 ){
                null1.setVisible(true);
                null2.setVisible(true);
                tbl_transaction.setVisible(false);
            }else{
                null1.setVisible(false);
                null2.setVisible(false);
                tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
                tran_date.setCellValueFactory(new PropertyValueFactory<Transactions,Date>("date"));
                tran_amount.setCellValueFactory(new PropertyValueFactory<Transactions,Integer>("amount"));
                tran_category.setCellValueFactory(new PropertyValueFactory<Transactions,Category>("category"));
                tbl_transaction.setItems(transactions);
            }
        });

        last1.setOnMouseClicked(e->{
            now.setStyle("-fx-text-fill: #d7d1d1");
            future.setStyle("-fx-text-fill: #d7d1d1");
            last.setStyle("-fx-text-fill: #d7d1d1");
            last1.setStyle("-fx-text-fill: #000000");
            last2.setStyle("-fx-text-fill: #d7d1d1");
            last3.setStyle("-fx-text-fill: #d7d1d1");
            last4.setStyle("-fx-text-fill: #d7d1d1");
            date = last1.getText();
            transactions = transactionStatement.getTransactions(date,Integer.parseInt(user_id.getText()));
            if(transactions.size() <1 ){
                null1.setVisible(true);
                null2.setVisible(true);
                tbl_transaction.setVisible(false);
            }else{
                null1.setVisible(false);
                null2.setVisible(false);
                tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
                tran_date.setCellValueFactory(new PropertyValueFactory<Transactions,Date>("date"));
                tran_amount.setCellValueFactory(new PropertyValueFactory<Transactions,Integer>("amount"));
                tran_category.setCellValueFactory(new PropertyValueFactory<Transactions,Category>("category"));
                tbl_transaction.setItems(transactions);
            }
        });
        last2.setOnMouseClicked(e->{
            now.setStyle("-fx-text-fill: #d7d1d1");
            future.setStyle("-fx-text-fill: #d7d1d1");
            last.setStyle("-fx-text-fill: #d7d1d1");
            last1.setStyle("-fx-text-fill: #d7d1d1");
            last2.setStyle("-fx-text-fill: #000000");
            last3.setStyle("-fx-text-fill: #d7d1d1");
            last4.setStyle("-fx-text-fill: #d7d1d1");
            date = last2.getText();
            transactions = transactionStatement.getTransactions(date,Integer.parseInt(user_id.getText()));
            if(transactions.size() <1 ){
                null1.setVisible(true);
                null2.setVisible(true);
                tbl_transaction.setVisible(false);
            }else{
                null1.setVisible(false);
                null2.setVisible(false);
                tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
                tran_date.setCellValueFactory(new PropertyValueFactory<Transactions,Date>("date"));
                tran_amount.setCellValueFactory(new PropertyValueFactory<Transactions,Integer>("amount"));
                tran_category.setCellValueFactory(new PropertyValueFactory<Transactions,Category>("category"));
                tbl_transaction.setItems(transactions);
            }
        });
        last3.setOnMouseClicked(e->{
            now.setStyle("-fx-text-fill: #d7d1d1");
            future.setStyle("-fx-text-fill: #d7d1d1");
            last.setStyle("-fx-text-fill: #d7d1d1");
            last1.setStyle("-fx-text-fill: #d7d1d1");
            last2.setStyle("-fx-text-fill: #d7d1d1");
            last3.setStyle("-fx-text-fill: #000000");
            last4.setStyle("-fx-text-fill: #d7d1d1");
            date = last3.getText();
            transactions = transactionStatement.getTransactions(date,Integer.parseInt(user_id.getText()));
            if(transactions.size() <1 ){
                null1.setVisible(true);
                null2.setVisible(true);
                tbl_transaction.setVisible(false);
            }else{
                null1.setVisible(false);
                null2.setVisible(false);
                tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
                tran_date.setCellValueFactory(new PropertyValueFactory<Transactions,Date>("date"));
                tran_amount.setCellValueFactory(new PropertyValueFactory<Transactions,Integer>("amount"));
                tran_category.setCellValueFactory(new PropertyValueFactory<Transactions,Category>("category"));
                tbl_transaction.setItems(transactions);
            }
        });
        last4.setOnMouseClicked(e->{
            now.setStyle("-fx-text-fill: #d7d1d1");
            future.setStyle("-fx-text-fill: #d7d1d1");
            last.setStyle("-fx-text-fill: #d7d1d1");
            last1.setStyle("-fx-text-fill: #d7d1d1");
            last2.setStyle("-fx-text-fill: #d7d1d1");
            last3.setStyle("-fx-text-fill: #d7d1d1");
            last4.setStyle("-fx-text-fill: #000000");
            date = last4.getText();
            transactions = transactionStatement.getTransactions(date,Integer.parseInt(user_id.getText()));
            if(transactions.size() <1 ){
                null1.setVisible(true);
                null2.setVisible(true);
                tbl_transaction.setVisible(false);
            }else{
                null1.setVisible(false);
                null2.setVisible(false);
                tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
                tran_date.setCellValueFactory(new PropertyValueFactory<Transactions,Date>("date"));
                tran_amount.setCellValueFactory(new PropertyValueFactory<Transactions,Integer>("amount"));
                tran_category.setCellValueFactory(new PropertyValueFactory<Transactions,Category>("category"));
                tbl_transaction.setItems(transactions);
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
    public void loadHomeWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainWindow/homePage.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            HomePageController controller = loader.getController();
            UserStatement userStatement = new UserStatement();
            User user = userStatement.getUserById(Integer.parseInt(user_id.getText()));
            user.setId(user.getId());
            user.setFullName(user.getFullName());
            controller.getUser(user);
            loginStage.setTitle("transactions");
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
            loader.setLocation(getClass().getResource("/Planning/planning.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
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
            Scene loginScene = new Scene(root,730, 650);
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
    public void loadSearchWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Transactions/searchTransaction.fxml"));
            Parent root = loader.load();
            SearchTransactionController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
            Scene loginScene = new Scene(root,670, 440);
            loginStage.setTitle("Account User");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
