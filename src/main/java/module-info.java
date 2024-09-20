module com.SlugCats {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires java.desktop;



    opens com.SlugCats to javafx.fxml;
    exports com.SlugCats;
    exports com.SlugCats.controller;
    opens com.SlugCats.controller to javafx.fxml;
    exports com.SlugCats.Models;
    exports com.SlugCats.auth;
    exports com.SlugCats.NewAuth;
    exports com.SlugCats.DAOs;
    exports com.SlugCats.timetracking;
}