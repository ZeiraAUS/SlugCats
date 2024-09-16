package com.SlugCats.controller;

import com.SlugCats.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private BorderPane rootPane; // Reference to the BorderPane from FXML
    @FXML
    private Label loginLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private HBox buttonBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private ImageView logoImage;

    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        Image logo = new Image(getClass().getResource("/src/main/resources/images/snailcat.PNG").toString(),true);
        //should be working with Image("file url"); as well but doesn't
        logoImage.setImage(logo);

        // Initialize and configure the VBox
        VBox vbox = new VBox(20);

        vbox.getChildren().addAll(
                loginLabel,
                emailLabel,
                emailField,
                passwordLabel,
                passwordField,
                buttonBox
        );

        // Set the VBox to the center of the BorderPane
        rootPane.setCenter(vbox);
    }

    //NOTE TO TEAM:
    /*
    So the plan with the text fields is that their input can be used for both logging in and registering.
    Regardless of which is pressed, as long as it's valid, they'll be moved to the next window (Home).
     */

    @FXML
    protected void onLoginButtonClick() throws IOException {
        //NOTE: Put your login logic here.

        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        //NOTE: Put your register logic here.

        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }


}
