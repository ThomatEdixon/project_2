package io.aptech.Controller;

import io.aptech.Controller.SpendingPlan.MoneyAriseController;
import io.aptech.Controller.SpendingPlan.MoneyFixedController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class LoadMainWindows {
    public LoadMainWindows(){}
    public void open(String path,String title,String sId,int W, int H,String id){
        try {
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(path));
            if(sId.equals("MONTH")){
                System.out.println("okkkkkk");
                MoneyFixedController controller = loader.getController();
                controller.setId(id);
            }else if(sId.equals("DAY")){
                MoneyAriseController controller1 = loader.getController();
                controller1.setIds(id);
            }
            Parent root = loader.load();
            Scene scene = new Scene(root,W, H);
            scene.getStylesheets().add(getClass().getResource("/Style/style.css").toExternalForm());
            loginStage.setTitle(title);
            loginStage.setScene(scene);
            loginStage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
