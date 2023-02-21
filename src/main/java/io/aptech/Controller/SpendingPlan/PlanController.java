package io.aptech.Controller.SpendingPlan;

import io.aptech.Controller.LoadMainWindows;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PlanController implements Initializable {

    @FXML
    private Button m_fixed;
    @FXML
    private Button m_days;
    private LoadMainWindows window = new LoadMainWindows();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            m_fixed.setOnAction(e -> {
                window.open("/spendingPlan/moneyFixed.fxml","Money Fixed",679,577);
            });

    }
}
