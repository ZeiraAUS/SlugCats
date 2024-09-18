module com.example.SlugCats {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.SlugCats to javafx.fxml;
    exports com.SlugCats;
    exports com.SlugCats.controller;
    opens com.SlugCats.controller to javafx.fxml;
    exports com.SlugCats.DAOs;
    exports com.SlugCats.Models;
}