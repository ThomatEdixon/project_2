package io.aptech.Controller;

import io.aptech.Entity.Events;
import io.aptech.Entity.User;
import io.aptech.Model.EventsStatement;
import io.aptech.Model.UserStatement;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class EventsController implements Initializable {
    @FXML private Button btn_add_event;
    @FXML private ImageView events_back_page;
    @FXML private Label event_all;
    @FXML private Label events_running;
    @FXML private Label events_finished;
    @FXML private Label user_id;
    @FXML private TableView<Events> tbl_events = new TableView<Events>();
    @FXML private TableColumn<Events,String> c_name;
    @FXML private TableColumn<Events,Integer> c_spent;
    @FXML private TableColumn<Events,Date> c_start;
    @FXML private TableColumn<Events,Date> c_end;
    @FXML private TableColumn<Events,Integer> c_id;
    @FXML private FontIcon transaction;
    @FXML private FontIcon addNew;
    @FXML private FontIcon planning;
    @FXML private FontIcon accountUser;
    @FXML private FontIcon home;
    private ObservableList<Events> events;
    private EventsStatement eventsStatement = new EventsStatement();

    public EventsController() {
    }
    public void getUserId(int id){
        user_id.setText(String.valueOf(id));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user_id.setVisible(false);
        eventsStatement.create();
        events = eventsStatement.getAll();
        tbl_events.getColumns().get(4).setVisible(false);
        c_name.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
        c_spent.setCellValueFactory(new PropertyValueFactory<Events,Integer>("spent"));
        c_start.setCellValueFactory(new PropertyValueFactory<Events, Date>("startDate"));
        c_end.setCellValueFactory(new PropertyValueFactory<Events,Date>("endDate"));
        c_id.setCellValueFactory(new PropertyValueFactory<Events,Integer>("id"));
        tbl_events.setItems(events);
        event_all.setOnMouseClicked(e->{
            events = eventsStatement.getAll();
            event_all.setStyle("-fx-text-fill: #000000");
            events_running.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #f0f0f0");
            tbl_events.getColumns().get(4).setVisible(false);
            c_name.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Events,Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Events, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Events,Date>("endDate"));
            c_id.setCellValueFactory(new PropertyValueFactory<Events,Integer>("id"));
            tbl_events.setItems(events);
        });
        events_running.setOnMouseClicked(e->{
            events = eventsStatement.getRunning();
            event_all.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #f0f0f0");
            events_running.setStyle("-fx-text-fill: #000000");
            tbl_events.getColumns().get(4).setVisible(false);
            c_name.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Events,Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Events, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Events,Date>("endDate"));
            c_id.setCellValueFactory(new PropertyValueFactory<Events,Integer>("id"));
            tbl_events.setItems(events);
        });
        events_finished.setOnMouseClicked(e->{
            events = eventsStatement.getFinished();
            events_running.setStyle("-fx-text-fill: #f0f0f0");
            event_all.setStyle("-fx-text-fill: #f0f0f0");
            events_finished.setStyle("-fx-text-fill: #000000");
            tbl_events.getColumns().get(4).setVisible(false);
            c_name.setCellValueFactory(new PropertyValueFactory<Events,String>("name"));
            c_spent.setCellValueFactory(new PropertyValueFactory<Events,Integer>("spent"));
            c_start.setCellValueFactory(new PropertyValueFactory<Events, Date>("startDate"));
            c_end.setCellValueFactory(new PropertyValueFactory<Events,Date>("endDate"));
            c_id.setCellValueFactory(new PropertyValueFactory<Events,Integer>("id"));
            tbl_events.setItems(events);
        });

        events_back_page.setOnMouseClicked(e->{
            //close window
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadPlanningWindow();
        });
        btn_add_event.setOnAction(e->{
            Node node = (Node) e.getSource();
            Stage thisStage = (Stage) node.getScene().getWindow();
            thisStage.close();
            //load login window
            loadAddEventWindow();
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
        tbl_events.setOnMouseClicked(e ->{
            try{
                Events editEvent = tbl_events.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Events/editEvent.fxml"));
                Pane viewEdit = loader.load();
                Scene scene = new Scene(viewEdit);
                EditEventController editEventController = loader.getController();
                editEventController.getUserId(user_id.getText());
                editEventController.getEvents(editEvent);
                stage.setTitle("Edit Event");
                stage.setScene(scene);
                stage.show();
                // close window
                Node node = (Node) e.getSource();
                Stage thisStage = (Stage) node.getScene().getWindow();
                thisStage.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        });
    }
    public void loadAddEventWindow(){
        try {
            Stage dddEventStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Events/addEvent.fxml"));
            Parent root = loader.load();
            AddEventController controller = loader.getController();
            controller.getUserId(Integer.parseInt(user_id.getText()));
            Scene loginScene = new Scene(root,600, 400);
            dddEventStage.setTitle("Add Event");
            dddEventStage.setScene(loginScene);
            dddEventStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
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
            loginStage.setTitle("Home Page");
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
            UserStatement userStatement = new UserStatement();
            User user = userStatement.getUserById(Integer.parseInt(user_id.getText()));
            user.setId(user.getId());
            user.setFullName(user.getFullName());
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
