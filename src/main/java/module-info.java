module io.aptech.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
//    requires validatorfx;
//    requires eu.hansolo.tilesfx;

    opens io.aptech.project to javafx.fxml;
    exports io.aptech.project;
}