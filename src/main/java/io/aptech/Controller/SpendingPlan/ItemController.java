package io.aptech.Controller.SpendingPlan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    private static   String title;
    private static  String titleOption;
    private static MoneyFixedController moneyFixedController ;
    public  ItemController(){}
    public  ItemController(String title,MoneyFixedController moneyFixedControllerA ){
        this.title = title;
        this.moneyFixedController = moneyFixedControllerA;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public  String getTitleOption() {
        return titleOption;
    }

    public  void setTitleOption(String titleOption) {
        ItemController.titleOption = titleOption;
    }

    @FXML
    private Label o_title;
    @FXML
    private Button o_foodDrink;
    @FXML
    private Button o_clothes;
    @FXML
    private Button o_petroleum;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        o_foodDrink.setOnAction(e ->{
         this.moneyFixedController.setType(o_foodDrink.getText(),o_foodDrink.getId());
        });
        o_clothes.setOnAction(e ->{
            this.moneyFixedController.setType(o_clothes.getText(),o_clothes.getId());
        });
        o_petroleum.setOnAction(e ->{
            this.moneyFixedController.setType(o_petroleum.getText(),o_petroleum.getId());
        });
    }
}
