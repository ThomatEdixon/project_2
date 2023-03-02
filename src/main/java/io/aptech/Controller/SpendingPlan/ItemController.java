package io.aptech.Controller.SpendingPlan;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemController implements Initializable {
    private static   String title;
    private static  String titleOption;
    private static MoneyFixedController moneyFixedController ;
    private static MoneyAriseController moneyAriseController;
    public  ItemController(){}
    public  ItemController(String title, MoneyAriseController moneyAriseController){
        this.title = title;
        this.moneyAriseController = moneyAriseController;
    }
    public  ItemController(String title,MoneyFixedController moneyFixedControllerA ){
        this.title = title;
        this.moneyFixedController = moneyFixedControllerA;
    }


    public static MoneyAriseController getMoneyAriceController() {
        return moneyAriseController;
    }

    public static void setMoneyAriceController(MoneyAriseController moneyAriseController) {
        ItemController.moneyAriseController = moneyAriseController;
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

        if(title.equals("DAY")){
            day();
            System.out.println("Item Day");
        }
        if(title.equals("MONTH")){
            month();
            System.out.println("Item month");

        }


    }
    public  void  day(){
        o_foodDrink.setOnAction(e ->{
            this.moneyAriseController.setType(o_foodDrink.getText(),o_foodDrink.getId());
        });
        o_clothes.setOnAction(e ->{
            this.moneyAriseController.setType(o_clothes.getText(),o_clothes.getId());
        });
        o_petroleum.setOnAction(e ->{
            this.moneyAriseController.setType(o_petroleum.getText(),o_petroleum.getId());
        });
    }
    public void month(){
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
