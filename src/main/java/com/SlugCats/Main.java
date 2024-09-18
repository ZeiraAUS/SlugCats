package com.SlugCats;

import com.SlugCats.DAOs.UserDAO;
import com.SlugCats.Models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "SnailCat";
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 650;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)
    {
        Connection connection = DatabaseConnection.getInstance();

        DatabaseConnection.StartupMessage();
        DatabaseConnection.CreateTables();

        launch();
    }
}