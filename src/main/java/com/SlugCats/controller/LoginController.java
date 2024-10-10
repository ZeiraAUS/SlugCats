package com.SlugCats.controller;
import com.SlugCats.NewAuth.login_status;
import com.SlugCats.Models.User;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Login Controller handles the logic for the Login window.
 */
public class LoginController {
    @FXML
    private BorderPane rootPane;
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
    @FXML
    private HBox buttonBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button resetButton;

    public static  User user;

    /**
     * Initialise the Login window's components.
     */
    @FXML
    public void initialize() {
        // Configure the logo image.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        logoImage.setImage(logo);
        // Add components of the login window to the root pane.
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(
                loginLabel,
                usernameLabel,
                usernameField,
                passwordLabel,
                passwordField,
                buttonBox,
                resetButton
        );
        rootPane.setCenter(vbox);
    }

    /**
     * When the login button is clicked, login the user if they are valid and transition them to the Home screen.
     * @throws IOException
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        login_status loginStatus = new login_status();
        loginStatus.is_login(username,password);
        boolean is_login=loginStatus.gotLogin_status();
        user=loginStatus.getUser();
        boolean credentialValidity = is_login;

        // If credentials are valid, log them in, otherwise display a prompt.
        if (credentialValidity) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        else {
            // Error pop-up when user credentials are invalid.
            loginButton.setText("Invalid Credentials");
            loginButton.setTextFill(Color.RED);
        }

    }

    /**
     * Authenticate the user with their input from the text fields.
     * @param usernameInput User input from username field.
     * @param passwordInput User input from password field.
     * @return
     */
    private boolean AuthenticateUser(String usernameInput, String passwordInput) {
        login_status loginStatus = new login_status();
        System.out.println(usernameInput);
        boolean is_login=loginStatus.is_login(usernameInput,passwordInput);
        System.out.println(is_login);
        return is_login;
    }

    /**
     * When the register button is clicked, transition the user to the register window.
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

    @FXML
    protected void onResetButtonClick() throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("reset-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }
}
