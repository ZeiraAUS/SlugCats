package com.SlugCats.controller;
import com.SlugCats.NewAuth.login_status;
import com.SlugCats.DAOs.*;
import com.SlugCats.Models.User;

import com.SlugCats.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    // Main Border Pane
    @FXML
    private BorderPane rootPane;
    // Primary Components of the FXML
    @FXML
    private ImageView logoImage;
    @FXML
    private Label loginLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    // Buttons in HBox
    @FXML
    private HBox buttonBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    public static  User user;
    /**
     * Initialise the Login window's components.
     */
    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);

        logoImage.setImage(logo);

        // Initialize and configure the VBox
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(
                loginLabel,
                usernameLabel,
                usernameField,
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

    /**
     * Login button click.
     * @throws IOException
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        //NOTE: Put your login logic here. - You can reorganise my placeholder logic if you need to.
       /* String emailInput = emailField.toString();
        System.out.println(emailInput);
        String passwordInput = passwordField.toString();
        login_status loginStatus = new login_status();*/
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        login_status loginStatus = new login_status();
        loginStatus.is_login(username,password);
        boolean is_login=loginStatus.gotLogin_status();
        user=loginStatus.getUser();
        boolean credentialValidity = is_login;

        if (credentialValidity) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        else {
            //idk some popup appears that says credentials are wrong WIP
        }

    }

    // please insert your authentication logic here
    private boolean AuthenticateUser(String emailInput, String passwordInput) {
        login_status loginStatus = new login_status();
        System.out.println(emailInput);
        boolean is_login=loginStatus.is_login(emailInput,passwordInput);
        System.out.println(is_login);
        return is_login;
    }

    /**
     * Register button click.
     * @throws IOException
     */
    @FXML
    protected void onRegisterButtonClick() throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }
    static public User gotUser(){
        return user;

    }

}
