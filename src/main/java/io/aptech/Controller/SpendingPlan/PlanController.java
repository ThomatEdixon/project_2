package io.aptech.Controller.SpendingPlan;

import io.aptech.Controller.LoadMainWindows;
import io.aptech.Model.BudgetStatement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;


import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlanController implements Initializable {

    @FXML
    private Button m_fixed;
    @FXML

    private Label m_balance;
    @FXML
    private Button b_back;
    private BudgetStatement budgetStatement = new BudgetStatement();
    private LoadMainWindows window = new LoadMainWindows();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_back.setOnAction(e -> {
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            window.open("/Planning/planning.fxml", "Planning", "null", 679, 577, "null");

        });
        try {
            ResultSet rs = (ResultSet) budgetStatement.getById(1);
            if (rs.next()) {
                m_balance.setText(rs.getString("balance") + "$");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        m_fixed.setOnAction(e -> {

            window.open("/spendingPlan/moneyFixed.fxml", "Money Fixed", "MONTH", 722, 544, "1");
        });
    }
}
