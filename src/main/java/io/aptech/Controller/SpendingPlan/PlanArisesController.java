package io.aptech.Controller.SpendingPlan;

import io.aptech.Controller.LoadMainWindows;
import io.aptech.Model.BudgetStatement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PlanArisesController implements Initializable {
    @FXML
    private Button d_arises;
    @FXML
    private Label m_balance;
    @FXML
    private Button b_back;
    private BudgetStatement budgetStatement = new BudgetStatement();
    private static  MoneyAriseController moneyAriseController = new MoneyAriseController();
    private LoadMainWindows window = new LoadMainWindows();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            ResultSet rs = (ResultSet) budgetStatement.getById(1);
            if(rs.next()){
                m_balance.setText(rs.getString("balance")+ "$");
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        d_arises.setOnAction(e -> {

            window.open("/spendingPlan/moneyArise.fxml","Money Arise","DAY",722,544,"2");
        });
        b_back.setOnAction(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            window.open("/Planning/planning.fxml","hello","null",500,400,"null");
        });

    }
    public  void setId(int id){

    }
}
