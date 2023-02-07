package io.aptech.project;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FxmlLoader {
    private static Pane view;
    public static Pane getViewPane(String viewPath){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FxmlLoader.class.getResource(viewPath));
            view = (Pane) loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        return view;
    }
}
