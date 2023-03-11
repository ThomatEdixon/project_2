package io.aptech.Controller;

import io.aptech.Entity.Category;
import io.aptech.Entity.Transactions;
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
import java.util.ResourceBundle;

public class ResultSearchController implements Initializable {
    @FXML
    private FontIcon icon_back_page;
    @FXML private TableView<Transactions> tbl_result;
    @FXML private TableColumn<Transactions, String> tran_description;
    @FXML private TableColumn<Transactions , Date> tran_date;
    @FXML private TableColumn<Transactions , Integer > tran_amount;
    @FXML private TableColumn<Transactions , Category> tran_category;
    @FXML private Label user_id;
    @FXML private Label no_tran;
    private static ObservableList<Transactions> transactions = FXCollections.observableArrayList();
    public void getUserId(int id,ObservableList<Transactions> transaction){
        user_id.setText(String.valueOf(id));
        transactions = transaction;
        if(transactions.size() <1 ){
            no_tran.setVisible(true);
            tbl_result.setVisible(false);
        }else{
            no_tran.setVisible(false);
            tran_description.setCellValueFactory(new PropertyValueFactory<Transactions,String>("description"));
            tran_date.setCellValueFactory(new PropertyValueFactory<Transactions,Date>("date"));
            tran_amount.setCellValueFactory(new PropertyValueFactory<Transactions,Integer>("amount"));
            tran_category.setCellValueFactory(new PropertyValueFactory<Transactions,Category>("category"));
            tbl_result.setItems(transactions);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        icon_back_page.setOnMouseClicked(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load window
            loadSearchWindow();
        });
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
