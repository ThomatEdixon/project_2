package io.aptech.Controller;

import io.aptech.Entity.Category;
import io.aptech.Entity.Transactions;
import io.aptech.Enum.CategoryTransaction;
import io.aptech.Model.TransactionStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class TransactionsController implements Initializable {
    @FXML private FontIcon icon_back_page;
    @FXML private TextField transaction_amount;
    @FXML private TextField transaction_description;
    @FXML private DatePicker transaction_date;
    @FXML private ComboBox<String> transaction_category;
    @FXML private ObservableList<String> categories;
    @FXML private Label err_amount;
    @FXML private Label err_description;
    @FXML private Label err_date;
    @FXML private Label err_category;
    @FXML private Button btn_add_transaction;
    private static TransactionStatement transactionStatement = new TransactionStatement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        categories = FXCollections.observableArrayList();
        categories.add("Recurring");
        categories.add("Arise");
        transaction_category.setItems(categories);
        btn_add_transaction.setOnAction(e->{
            String amount = transaction_amount.getText();
            String description = transaction_description.getText();
            Date date = Date.valueOf("1000-01-28");
            if(transaction_date.getValue() != null){
                date = Date.valueOf(transaction_date.getValue());
            }
            String category = transaction_category.getValue();
            String check_amount = "YES";
            String check_description = "YES";
            String check_date = "YES";
            String check_category = "YES";
            if(amount.length()<1){
                check_amount = "NO";
                err_amount.setText("Amount is required");
                err_amount.setStyle("-fx-text-fill: #ff1744");
            }
            if (description.length() < 1){
                check_description = "NO";
                err_description.setText("Description is required");
                err_description.setStyle("-fx-text-fill: #ff1744");
            }
            if(date.equals(Date.valueOf("1000-01-28"))){
                check_date = "NO";
                err_date.setText("Date is required");
                err_date.setStyle("-fx-text-fill: #ff1744");
            }
            if (category.length() < 1){
                check_category = "NO";
                err_category.setText("Category is required");
                err_category.setStyle("-fx-text-fill: #ff1744");
            }
            if(check_amount == "YES" && check_description == "YES" && check_date == "YES" && check_category == "YES"){
                Transactions transactions = new Transactions();
                transactions.setDate(date);
                transactions.setAmount(Integer.parseInt(amount));
                transactions.setDescription(description);
                Category category1 = new Category();
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DATE);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                Date today = Date.valueOf(year+"-"+month+"-"+day);
                category1.setStart(today);
                category1.setEnd(today);
                category1.setCategoryTransaction(CategoryTransaction.valueOf(category));
                transactions.setCategory(category1);
                transactionStatement.insert(transactions);
                // close window
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                // load home page
                loadHomePage();
            }
        });
        icon_back_page.setOnMouseClicked(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            loadHomePage();
        });
    }
    public void loadHomePage() {
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainWindow/homePage.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,719, 429);
            loginStage.setTitle("Add New");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
