package io.aptech.Controller.SpendingPlan;

import io.aptech.Controller.LoadMainWindows;
import io.aptech.Entity.MoneyFixed;
import io.aptech.Model.BudgetStatement;
import io.aptech.Model.MoneyPlanAriseStatement;
import io.aptech.Model.UserStatement;
import io.aptech.Validation.RegisterValidation;
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

public class MoneyAriseController implements Initializable {
    private String type;
    private static int id;
    private static  String cerruntId;
    public MoneyAriseController(){}
    public MoneyAriseController(String _type,int _id){
        this.type = _type;
        this.id = _id;
    }

    public int getIds() {
        return id;
    }

    public static void setIds(String _sid) {
        id = Integer.valueOf(_sid);
    }

    public  String getType() {
        return type;
    }

    private BudgetStatement budgetStatement = new BudgetStatement();
    private LoadMainWindows window = new LoadMainWindows();
    private MoneyPlanAriseStatement moneyPlanAriseStatement = new MoneyPlanAriseStatement();
    @FXML private TableView<MoneyFixed> m_today= new TableView<>();
    private ObservableList<MoneyFixed> moneyFixeds;
    @FXML
    private Button add_days;
    @FXML
    private TextField day_txt;
    @FXML
    private Label day_title;
    @FXML
    private Button day_update;
    @FXML
    private  Button day_delete;
    @FXML
    private String check = "No";
    @FXML
    private Label g_id;
    @FXML
    private Label m_total;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        moneyPlanAriseStatement.create();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable task = () -> {
            try{

                ResultSet rs = (ResultSet) budgetStatement.getById(Integer.valueOf(g_id.getText()));
                if(rs.next()){
                    int deduct = rs.getInt("balance") - Integer.valueOf(m_total.getText());
                    if(deduct >= 0){
                        budgetStatement.updateBalance(id,deduct);
                    }
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        };
        executor.schedule(task, 1, TimeUnit.DAYS);
        executor.shutdown();
        add_days.setOnAction(e ->{
            ItemController item = new ItemController("DAY",this);
            window.open("/spendingPlan/items.fxml","DAY","null",225,400,"null");
        });
        ResultSet rs = moneyPlanAriseStatement.getById(id);

        try {
            if(rs.next()){
                moneyFixeds = FXCollections.observableArrayList(new MoneyFixed(rs.getString("o_foodDrink"),rs.getString("o_clothes"),rs.getString("o_petroleum")));

                moneyFixeds.forEach(v ->{
                    if(v.getO_foodDrink() != null){
                        TableColumn<MoneyFixed, String> col = new TableColumn<>("Foods & Drinks");
                        col.setCellValueFactory(new PropertyValueFactory<MoneyFixed,String>("o_foodDrink"));
                        col.setId("o_foodDrink");
                        m_today.getColumns().add(col);
                    }
                    if(v.getO_clothes() != null){
                        TableColumn<MoneyFixed, String> col = new TableColumn<>("Clothes");
                        col.setCellValueFactory(new PropertyValueFactory<MoneyFixed,String>("o_clothes"));
                        col.setId("o_clothes");
                        m_today.getColumns().add(col);
                    }
                    if(v.getO_petroleum() != null){
                        TableColumn<MoneyFixed, String> col = new TableColumn<>("Petroleum");
                        col.setCellValueFactory(new PropertyValueFactory<MoneyFixed,String>("o_petroleum"));
                        col.setId("o_petroleum");
                        m_today.getColumns().add(col);
                    }
                });
                m_today.setItems(moneyFixeds);
                m_total.setText("Total :" + moneyFixeds.get(0).getCerruntMoney() + "$");
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
        // set time to drop table tempCode after 1 minutes
        m_today.getFocusModel().focusedCellProperty().addListener((obs, oldVal, newVal) -> {
            if(newVal.getTableColumn() != null){
                MoneyFixed p = m_today.getSelectionModel().getSelectedItem();
                if( newVal.getTableColumn().getCellObservableValue(p)!= null){
                    day_txt.setText((String) newVal.getTableColumn().getCellObservableValue(p).getValue());
                    day_title.setText(newVal.getTableColumn().getText());
                    cerruntId = newVal.getTableColumn().getId();
                    System.out.println(cerruntId + newVal.getTableColumn().getId());
                }
            }
        });
        day_update.setOnAction(e ->{
            if(cerruntId != null && day_txt.getText() != null && RegisterValidation.ints(day_txt.getText()).equals("YES")){
                moneyPlanAriseStatement.updateType(cerruntId,day_txt.getText(),id);
                ResultSet rss = moneyPlanAriseStatement.getById(id);

                try {
                    if(rss.next()) {
                        moneyFixeds = FXCollections.observableArrayList(new MoneyFixed(rss.getString("o_foodDrink"), rss.getString("o_clothes"), rss.getString("o_petroleum")));
                        m_total.setText(" Total : " + moneyFixeds.get(0).getCerruntMoney() + "$");
                        m_today.setItems(moneyFixeds);
                    }
                }catch (SQLException ex){
                    throw  new RuntimeException(ex);
                }
                m_today.setItems(moneyFixeds);
            }

        });
        day_delete.setOnAction(e->{
            if(cerruntId != null && day_txt.getText() != null){
                moneyPlanAriseStatement.updateType(cerruntId,null,id);
                ResultSet rss = moneyPlanAriseStatement.getById(id);
                try {
                    if(rss.next()) {
                        moneyFixeds = FXCollections.observableArrayList(new MoneyFixed(rss.getString("o_foodDrink"), rss.getString("o_clothes"), rss.getString("o_petroleum")));
                        m_total.setText("Total : " + moneyFixeds.get(0).getCerruntMoney() + "$");
                        m_today.setItems(moneyFixeds);
                    }
                }catch (SQLException ex){
                    throw  new RuntimeException(ex);
                }
                m_today.setItems(moneyFixeds);
            }
        });


    }
    public void setType(String _type,String id) {
        check = "NO";
        m_today.getColumns().forEach(v->{
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
            m_today.getColumns().add(col);
        }

    }


}
