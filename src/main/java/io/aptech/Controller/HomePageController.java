package io.aptech.Controller;

import io.aptech.Entity.User;
import io.aptech.Model.AddBudgetStatement;
import io.aptech.Model.BarChartStatement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    @FXML
    private Pane main_barChart;
    @FXML
    private BarChart<?, ?> barChart;

    @FXML private FontIcon transaction;
    @FXML private FontIcon addNew;
    @FXML private FontIcon planning;
    @FXML private FontIcon accountUser;
    @FXML private FontIcon home;
    @FXML private FontIcon btnEye;
    @FXML private Label moneyBalance;
    @FXML private Label user_id;
    @FXML private Label user_name;
    @FXML private Label moneyThisMonth;
    @FXML private Label negativePercent;
    @FXML private Label numberBarchart;
    @FXML private Button btnWeek;
    @FXML private Button btnMonth;
    @FXML private Label totalSpending;
    private static int count = 0;
    private static int balance = 0;
    private static AddBudgetStatement addBudgetStatement = new AddBudgetStatement();
    public HomePageController() {
    }
    public void getUser(User user){
        user_id.setText(String.valueOf(user.getId()));
        user_name.setText(user.getFullName());
        balance = addBudgetStatement.getBalance(user.getId());
        String type = addBudgetStatement.getType(user.getId());
        moneyBalance.setText(balance+" "+type);
    }

    public void barChart() {
        BarChartStatement barChartStatement = new BarChartStatement();
        if(numberBarchart.getText().equals("1")){
            int total =  barChartStatement.getThisWeekDataToBarChart();
            int total2 = barChartStatement.getLastWeekDataToBarChart();
            String lastWeek = "Last week";
            String thisWeek = "This week";

            double negative = ((double) total/total2)*100;
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String formattedValue = df.format(negative);

            moneyThisMonth.setText(total + " VND");
            negativePercent.setText(formattedValue + "%");

            XYChart.Series chartData = new XYChart.Series();
            chartData.getData().add(new XYChart.Data(lastWeek, total2));
            chartData.getData().add(new XYChart.Data(thisWeek, total));
            barChart.getData().add(chartData);
        } else if (numberBarchart.getText().equals("2")) {
            int total =  barChartStatement.getDataToBarChart();
            int total2 = barChartStatement.getDataToBarChart2();
            String lastMonth = "Last month";
            String thisMonth = "This month";

            double negative = ((double) total/total2)*100;
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.CEILING);
            String formattedValue = df.format(negative);

            moneyThisMonth.setText(total + " VND");
            negativePercent.setText(formattedValue + "%");

            XYChart.Series chartData = new XYChart.Series();
            chartData.getData().add(new XYChart.Data(lastMonth, total2));
            chartData.getData().add(new XYChart.Data(thisMonth, total));
            barChart.getData().add(chartData);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        numberBarchart.setVisible(false);
        user_id.setVisible(false);
        barChart();
        btnMonth.setStyle("-fx-background-color: #EEEEEE");

        btnMonth.setOnAction(event -> {
            btnWeek.setStyle("-fx-background-color: #EEEEEE");
            btnMonth.setStyle("");
            totalSpending.setText("Total spending this month");
            numberBarchart.setText("2");
            barChart.getData().clear();
            barChart();
        });

        btnWeek.setOnAction(event -> {
            btnWeek.setStyle("");
            btnMonth.setStyle("-fx-background-color: #EEEEEE");
            totalSpending.setText("Total spending this week");
            numberBarchart.setText("1");
            barChart.getData().clear();
            barChart();
        });


        btnEye.setOnMouseClicked(event -> {
            count+=1;
            if(count%2!=0){
                moneyBalance.setText("******** VND");
                btnEye.setIconLiteral("fas-eye-slash");
            } else {
                String type = addBudgetStatement.getType(Integer.parseInt(user_id.getText()));
                moneyBalance.setText(balance+" "+type);
                btnEye.setIconLiteral("fas-eye");
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
        addBalance.setOnMouseClicked(event -> {
            try {
                Stage loginStage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Budget/budget.fxml"));
                Parent root = loader.load();
                AddBudgetController budgetController = loader.getController();
                budgetController.getUserId(Integer.parseInt(user_id.getText()));
                Scene loginScene = new Scene(root,505, 350);
                loginStage.setTitle("Budget");
                loginStage.setScene(loginScene);
                loginStage.show();
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }
    public void loadHomeWindow(){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainWindow/homePage.fxml"));
            Parent root = loader.load();
            Scene loginScene = new Scene(root,700, 690);
            HomePageController controller = loader.getController();
            User user = new User();
            user.setId(Integer.parseInt(user_id.getText()));
            controller.getUser(user);
            loginStage.setTitle("Home page");
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
            Scene loginScene = new Scene(root,730, 690);
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
            User user = new User();
            user.setId(Integer.parseInt(user_id.getText()));
            user.setFullName(user_name.getText());
            userManagementController.getUserById(user);
            Scene loginScene = new Scene(root,695, 770);
            loginStage.setTitle("Account User");
            loginStage.setScene(loginScene);
            loginStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
