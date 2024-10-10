package com.SlugCats.controller;

import com.SlugCats.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
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
import javafx.stage.Stage;
import java.io.IOException;

import com.SlugCats.NewAuth.register;

public class RegisterController {
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button backButton;
    @FXML
    private Label registrationLabel;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label confirmPasswordLabel;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label firstNameLabel;
    @FXML
    private TextField firstNameField;
    @FXML
    private Label lastNameLabel;
    @FXML
    private TextField lastNameField;
    @FXML
    private Label emailLabel;
    @FXML
    private TextField emailField;
    @FXML
    private Button registerButton;

    /**
     * Initialize the Register window.
     */
    @FXML
    public void initialize() {
        // Initialize the header components.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        logoImage.setImage(logo);
        HBox headerBox = new HBox(20);
        headerBox.getChildren().addAll(
                logoImage,
                backButton,
                registrationLabel
        );

        // Configure the components of the register form.
        VBox formLabelBox = new VBox(20);
        passwordLabel.setPadding(new Insets(15,0,0,0));
        confirmPasswordLabel.setPadding(new Insets(15,0,0,0));
        firstNameLabel.setPadding(new Insets(15,0,0,0));
        lastNameLabel.setPadding(new Insets(15,0,0,0));
        emailLabel.setPadding(new Insets(15,0,0,0));
        formLabelBox.getChildren().addAll(
                usernameLabel,
                passwordLabel,
                confirmPasswordLabel,
                firstNameLabel,
                lastNameLabel,
                emailLabel,
                registerButton
        );
        VBox formFieldBox = new VBox(20);
        formFieldBox.getChildren().addAll(
                usernameField,
                passwordField,
                confirmPasswordField,
                firstNameField,
                lastNameField,
                emailField
        );

        rootPane.setTop(headerBox);
        rootPane.setLeft(formLabelBox);
        rootPane.setRight(formFieldBox);
    }

    /**
     * Transitions user back to the log-in screen when they click the back button.
     * @throws IOException
     */
    @FXML
    protected void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    /**
     * When the register button is clicked, use the text fields' input to create a new user then transition
     * the user back to the Log-in window.
     * @throws IOException
     */
    @FXML
    protected void onRegisterButtonClick() throws IOException {
        String username = usernameField.getText();
        String Password = passwordField.getText();
        String ConfirmPassword = confirmPasswordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        register register=new register();
        register.register_a_user(username,Password,ConfirmPassword,firstName,lastName,email);

        // Transition back to Log-in Window.
        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }
}
