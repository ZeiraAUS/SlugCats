module com.example.addressbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;




    opens com.SlugCats to javafx.fxml;
    exports com.SlugCats;
    exports com.SlugCats.controller;
    opens com.SlugCats.controller to javafx.fxml;
    exports com.SlugCats.Models;
    exports com.SlugCats.auth;
    exports com.SlugCats.NewAuth;
    exports com.SlugCats.DAOs;

}