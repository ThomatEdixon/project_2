package io.aptech.Controller;

import io.aptech.Entity.Budget;
import io.aptech.Entity.Category;
import io.aptech.Entity.User;
import io.aptech.Enum.CategoryTransaction;
import io.aptech.Model.AddBudgetStatement;
import io.aptech.Model.BudgetStatement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.Date;
import java.util.Calendar;
import java.util.ResourceBundle;

public class AddBudgetController implements Initializable {
    @FXML private FontIcon icon_back_page;
    @FXML private TextField budget_balance;
    @FXML private TextField budget_type;
    @FXML private Label err_balance;
    @FXML private Label err_type;
    @FXML private Label user_id;
    @FXML private Button btn_add_budget;
    @FXML private AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        icon_back_page.setOnMouseClicked(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
        });
        btn_add_budget.setOnAction(e->{
            String balance = budget_balance.getText();
            String type = budget_type.getText();
            String checkBalance = "YES";
            String checkType = "YES";
            if(balance.length() < 1){
                checkBalance = "NO";
                err_balance.setText("Balance is required");
                err_balance.setStyle("-fx-text-fill: #ff1744");
            }
            if (type.length()<1){
                checkType = "NO";
                err_type.setText("Type currency is required");
                err_type.setStyle("-fx-text-fill: #ff1744");
            }
            if(checkBalance.equals("YES")&& checkType.equals("YES")){
                Budget budget = new Budget();
                User user = new User();
                user.setId(Integer.parseInt(user_id.getText()));
                budget.setUser(user);
                budget.setType(type);
                budget.setBalance(Double.parseDouble(balance));
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DATE);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);
                Date today = Date.valueOf(year+"-"+month+"-"+day);
                budget.setDate(today);
                addBudgetStatement.insert(budget);
                // create category transaction for budget
                Category category1 = new Category();
                Budget budget1 = addBudgetStatement.getById(Integer.parseInt(user_id.getText()));
                System.out.println(budget1.getId());
                category1.setBudget(budget1);
                category1.setCategoryTransaction(CategoryTransaction.recurring);
                category1.setStart(today);
                category1.setEnd(today);
                //
                Category category2 = new Category();
                category2.setBudget(budget1);
                category2.setCategoryTransaction(CategoryTransaction.arise);
                category2.setStart(today);
                category2.setEnd(today);
                addBudgetStatement.insertCategory(category1);
                addBudgetStatement.insertCategory(category2);
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
            }
        });
    }
    public void getUserId(int id){
        user_id.setText(String.valueOf(id));
    }
}
