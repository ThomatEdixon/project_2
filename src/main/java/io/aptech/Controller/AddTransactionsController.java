package io.aptech.Controller;

import io.aptech.Entity.Category;
import io.aptech.Entity.Transactions;
import io.aptech.Entity.User;
import io.aptech.Model.AddBudgetStatement;
import io.aptech.Model.AddTransactionStatement;
import io.aptech.Model.UserStatement;
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
import java.util.ResourceBundle;

public class AddTransactionsController implements Initializable {
    @FXML private FontIcon icon_back_page;
    @FXML private TextField transaction_amount;
    @FXML private TextField transaction_description;
    @FXML private DatePicker transaction_date;
    @FXML private ComboBox<Category> transaction_category;
    @FXML private ObservableList<Category> categories;
    @FXML private Label err_amount;
    @FXML private Label err_description;
    @FXML private Label err_date;
    @FXML private Label err_category;
    @FXML private Label user_id;
    @FXML private Button btn_add_transaction;
    private static AddTransactionStatement transactionStatement = new AddTransactionStatement();
    private static AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
    public void getUserId(int id){
        user_id.setText(String.valueOf(id));
        ObservableList<Category> category = transactionStatement.getCategories(id);
        categories = FXCollections.observableArrayList(
                category.get(0),
                category.get(1)
        );
        transaction_category.setItems(categories);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        btn_add_transaction.setOnAction(e->{
            String amount = transaction_amount.getText();
            String description = transaction_description.getText();
            Date date = Date.valueOf("1000-01-28");
            int balance = addBudgetStatement.getBalance(Integer.parseInt(user_id.getText()));
            if(transaction_date.getValue() != null){
                date = Date.valueOf(transaction_date.getValue());
            }
            String category = String.valueOf(transaction_category.getValue());
            String check_amount = "YES";
            String check_description = "YES";
            String check_date = "YES";
            String check_category = "YES";
            if(amount.length()<1){
                check_amount = "NO";
                err_amount.setText("Amount is required");
                err_amount.setStyle("-fx-text-fill: #ff1744");
            } else if (balance < Integer.parseInt(amount)) {
                check_amount = "NO";
                err_amount.setText("Balance not enough");
                err_amount.setStyle("-fx-text-fill: #ff1744");
            }else{
                balance -= Integer.parseInt(amount);
                check_amount ="YES";
                err_amount.setText("");
            }
            if (description.length() < 1){
                check_description = "NO";
                err_description.setText("Description is required");
                err_description.setStyle("-fx-text-fill: #ff1744");
            }else {
                check_description = "YES";
                err_description.setText("");
            }
            if(date.equals(Date.valueOf("1000-01-28"))){
                check_date = "NO";
                err_date.setText("Date is required");
                err_date.setStyle("-fx-text-fill: #ff1744");
            }else{
                check_date = "YES";
                err_date.setText("");
            }
            if (category.length()<1) {
                check_category = "NO";
                err_category.setText("Category is required");
                err_category.setStyle("-fx-text-fill: #ff1744");
            }else{
                check_category = "YES";
                err_category.setText("");
            }
            if(check_amount == "YES" && check_description == "YES" && check_date == "YES" && check_category == "YES"){
                Transactions transactions = new Transactions();
                transactions.setDescription(description);
                transactions.setAmount(Integer.parseInt(amount));
                transactions.setDate(date);
                Category cat = transactionStatement.getCategoriesByCategoryTransaction(Integer.parseInt(user_id.getText()),category);
                transactions.setCategory(cat);
                transactionStatement.insert(transactions);
                // update balance
                addBudgetStatement.updateBalance(balance);
                // close window
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
                // load home page
                loadHomeWindow();
            }
        });
        icon_back_page.setOnMouseClicked(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            loadHomeWindow();
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
}
