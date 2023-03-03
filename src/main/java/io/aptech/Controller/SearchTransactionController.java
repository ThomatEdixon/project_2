package io.aptech.Controller;

import io.aptech.Entity.Category;
import io.aptech.Entity.Transactions;
import io.aptech.Model.AddTransactionStatement;
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
import java.util.ResourceBundle;

public class SearchTransactionController implements Initializable {
    @FXML private FontIcon icon_back_page;
    @FXML private TextField search_amount;
    @FXML private TextField search_description;
    @FXML private DatePicker search_date;
    @FXML private ComboBox<Category> search_category;
    @FXML private ObservableList<Category> categories;
    @FXML private Label user_id;
    @FXML private Button btn_search_transaction;
    private static AddTransactionStatement addTransactionStatement = new AddTransactionStatement();
    private static TransactionStatement transactionStatement = new TransactionStatement();
    private static ObservableList<Transactions> transactions1 = FXCollections.observableArrayList();
    public void getUserId(int id){
        user_id.setText(String.valueOf(id));
        ObservableList<Category> category = addTransactionStatement.getCategories(id);
        categories = FXCollections.observableArrayList(
                category.get(0),
                category.get(1)
        );
        search_category.setItems(categories);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        icon_back_page.setOnMouseClicked(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            loadTransactionWindow();
        });
        btn_search_transaction.setOnAction(e->{
            String amount = search_amount.getText();
            String description = search_description.getText();
            Date date = Date.valueOf("1000-01-28");
            if(search_date.getValue() != null){
                date = Date.valueOf(search_date.getValue());
            }
            String category = String.valueOf(search_category.getValue());
            Transactions transactions = new Transactions();
            transactions.setDescription(description);
            if(amount.length() == 0){
                amount = "0";
            }
            transactions.setAmount(Integer.parseInt(amount));
            transactions.setDate(date);
            Category cat = addTransactionStatement.getCategoriesByCategoryTransaction(Integer.parseInt(user_id.getText()),category);
            transactions.setCategory(cat);
            transactions1 = transactionStatement.searchTransactions(Integer.parseInt(user_id.getText()),transactions);
            // close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            // load home page
            try {
                Stage loginStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Transactions/resultSearch.fxml"));
                Parent root = loader.load();
                ResultSearchController controller = loader.getController();
                controller.getUserId(Integer.parseInt(user_id.getText()),transactions1);
                Scene loginScene = new Scene(root,840, 565);
                loginStage.setTitle("transactions");
                loginStage.setScene(loginScene);
                loginStage.show();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        });
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
}
