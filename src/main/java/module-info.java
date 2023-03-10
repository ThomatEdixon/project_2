module io.aptech.project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    //requires validatorfx;
    //requires eu.hansolo.tilesfx;
    // Sql
    requires java.sql;
    requires mysql.connector.java;
    //Bcrypt
    requires jbcrypt;

    requires org.kordamp.ikonli.fontawesome5;
    requires org.kordamp.ikonli.core;
    requires org.kordamp.ikonli.javafx;

//    Send Mail
    requires javax.mail;

    exports io.aptech.Entity;
    opens io.aptech.Entity to javafx.fxml;
    opens io.aptech.project to javafx.fxml;
    exports io.aptech.project;
    exports io.aptech.Controller;
    opens io.aptech.Controller to javafx.fxml;
    opens User to javafx.fxml;
    opens MainWindow to javafx.fxml;
    opens io.aptech.Controller.SpendingPlan to javafx.fxml;
    opens Planning to javafx.fxml;
    opens Events to javafx.fxml;

    opens spendingPlan to javafx.fxml;


}