package io.aptech.Controller.SpendingPlan;

import io.aptech.Controller.LoadMainWindows;
import io.aptech.Entity.MoneyFixed;
import io.aptech.Model.BudgetStatement;
import io.aptech.Model.MoneyPlanStatement;

import io.aptech.Validation.RegisterValidation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MoneyFixedController implements Initializable {
    private String type;
    private static int id;
    public  MoneyFixedController(){}
    public  MoneyFixedController(String _type){
        this.type = _type;
    }

    public  String getType() {
        return type;
    }
    @FXML
    private Button add_months;

    private LoadMainWindows window = new LoadMainWindows();
    @FXML private TableView<MoneyFixed> m_month= new TableView<>();
    private ObservableList<MoneyFixed> moneyFixeds;
    @FXML
    private TextField month_txt;
    @FXML
    private Label month_title;
    @FXML
    private Button month_update;
    @FXML
    private  Button month_delete;
    @FXML
    private String check = "No";
    @FXML
    private Label m_balance;
    @FXML
    private static Label g_id;
    @FXML
    private MoneyPlanStatement moneyPlanStatement = new MoneyPlanStatement();
    private BudgetStatement budgetStatement = new BudgetStatement();
    private static  String cerruntId;
    @FXML
    private Label m_total;

    public static void setId(String _id) {
        id = Integer.valueOf(_id);
    }

    public static int getId() {
        return id;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moneyPlanStatement.create();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            try{
                ResultSet rs = (ResultSet) budgetStatement.getById(id);
                if(rs.next()){
                    int deduct = rs.getInt("balance") - Integer.valueOf(m_total.getText());
                    if(deduct >= 0){
                        System.out.println(deduct);
                        budgetStatement.updateBalance(id,deduct);
                    }
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        };
        executor.schedule(task, 30, TimeUnit.DAYS);
        executor.shutdown();
        add_months.setOnAction(e ->{
            ItemController item = new ItemController("MONTH",this);
            window.open("/spendingPlan/items.fxml","MONTH","null",225,400,"null");
        });

        ResultSet rs = moneyPlanStatement.getById(id);

        try {
            if(rs.next()){
                moneyFixeds = FXCollections.observableArrayList(new MoneyFixed(rs.getString("o_foodDrink"),rs.getString("o_clothes"),rs.getString("o_petroleum")));

                moneyFixeds.forEach(v ->{
                    if(v.getO_foodDrink() != null){
                        TableColumn<MoneyFixed, String> col = new TableColumn<>("Foods & Drinks");
                        col.setCellValueFactory(new PropertyValueFactory<MoneyFixed,String>("o_foodDrink"));
                        col.setId("o_foodDrink");
                        m_month.getColumns().add(col);
                    }
                    if(v.getO_clothes() != null){
                        TableColumn<MoneyFixed, String> col = new TableColumn<>("Clothes");
                        col.setCellValueFactory(new PropertyValueFactory<MoneyFixed,String>("o_clothes"));
                        col.setId("o_clothes");
                        m_month.getColumns().add(col);
                    }
                    if(v.getO_petroleum() != null){
                        TableColumn<MoneyFixed, String> col = new TableColumn<>("Petroleum");
                        col.setCellValueFactory(new PropertyValueFactory<MoneyFixed,String>("o_petroleum"));
                        col.setId("o_petroleum");
                        m_month.getColumns().add(col);
                    }
                });

                m_month.setItems(moneyFixeds);
                m_total.setText("Total : " + moneyFixeds.get(0).getCerruntMoney() + "$");

            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

        // set time to drop table tempCode after 1 minutes



        m_month.getFocusModel().focusedCellProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("okkk");
            if(newVal.getTableColumn() != null){
                MoneyFixed p = m_month.getSelectionModel().getSelectedItem();
                if( newVal.getTableColumn().getCellObservableValue(p)!= null){
                        month_txt.setText((String) newVal.getTableColumn().getCellObservableValue(p).getValue());
                        month_title.setText(newVal.getTableColumn().getText());
                        cerruntId = newVal.getTableColumn().getId();
                        System.out.println(cerruntId + newVal.getTableColumn().getId());
                }
            }
        });
        month_update.setOnAction(e ->{
            if(cerruntId != null && month_txt.getText() != null && RegisterValidation.ints(month_txt.getText()).equals("YES")){
                moneyPlanStatement.updateType(cerruntId,month_txt.getText(),id);
                ResultSet rss = moneyPlanStatement.getById(id);

                try {
                    if(rss.next()) {
                        moneyFixeds = FXCollections.observableArrayList(new MoneyFixed(rss.getString("o_foodDrink"), rss.getString("o_clothes"), rss.getString("o_petroleum")));
                        m_total.setText("Total : " + moneyFixeds.get(0).getCerruntMoney() + "$");
                        m_month.setItems(moneyFixeds);
                    }
                }catch (SQLException ex){
                    throw  new RuntimeException(ex);
                }
                m_month.setItems(moneyFixeds);
            }

        });
        month_delete.setOnAction(e->{
            if(cerruntId != null && month_txt.getText() != null){
                moneyPlanStatement.updateType(cerruntId,null,id);
                ResultSet rss = moneyPlanStatement.getById(id);
                try {
                    if(rss.next()) {
                        moneyFixeds = FXCollections.observableArrayList(new MoneyFixed(rss.getString("o_foodDrink"), rss.getString("o_clothes"), rss.getString("o_petroleum")));
                        m_total.setText("Total : " + moneyFixeds.get(0).getCerruntMoney() + "$");
                        m_month.setItems(moneyFixeds);
                    }
                }catch (SQLException ex){
                    throw  new RuntimeException(ex);
                }
                m_month.setItems(moneyFixeds);
            }
        });


    }
    public void setType(String _type,String id) {
        check = "NO";


       m_month.getColumns().forEach(v->{
           System.out.println(v.getText().equals(_type) + v.getText() + _type);
           if(v.getText().equals(_type)){
               check = "Yes";
           }
       });
       if(check.equals("NO")){
           moneyFixeds = FXCollections.observableArrayList(new MoneyFixed("0$","2$","0$"));
           TableColumn<MoneyFixed, String> col = new TableColumn<>(_type);
           col.setCellValueFactory(new PropertyValueFactory<MoneyFixed,String>(id));
           col.setId(id);
           m_month.getColumns().add(col);
       }

    }

}
