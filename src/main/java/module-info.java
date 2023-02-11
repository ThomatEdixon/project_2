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

    // Send Email
    requires javax.mail;

    opens io.aptech.project to javafx.fxml;
    exports io.aptech.project;
    exports io.aptech.Controller;
    opens io.aptech.Controller to javafx.fxml;
    opens User to javafx.fxml;

}