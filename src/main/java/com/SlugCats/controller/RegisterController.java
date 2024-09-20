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
import com.SlugCats.auth.Authentication;
public class RegisterController {
    @FXML
    private BorderPane rootPane;

    @FXML
    private ImageView logoImage;
    @FXML
    private Button backButton;

    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordField;
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

    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);

        logoImage.setImage(logo);

        HBox headerBox = new HBox(20);
        headerBox.getChildren().addAll(
                logoImage,
                backButton
        );

        VBox formLabelBox = new VBox(20);
        passwordLabel.setPadding(new Insets(15,0,0,0));
        firstNameLabel.setPadding(new Insets(15,0,0,0));
        lastNameLabel.setPadding(new Insets(15,0,0,0));
        emailLabel.setPadding(new Insets(15,0,0,0));
        formLabelBox.getChildren().addAll(
                usernameLabel,
                passwordLabel,
                firstNameLabel,
                lastNameLabel,
                emailLabel,
                registerButton
        );

        VBox formFieldBox = new VBox(20);
        formFieldBox.getChildren().addAll(
                usernameField,
                passwordField,
                firstNameField,
                lastNameField,
                emailField
        );

        rootPane.setTop(headerBox);
        rootPane.setLeft(formLabelBox);
        rootPane.setRight(formFieldBox);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        // Transition to Login window
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        //ADD ALL YOUR REGISTER LOGIC HERE PLEASE AND THANK YOU
        //you can get the text from field like example below
        String username = usernameField.getText();
        String Password = passwordField.getText();
        String ConfirmPassword = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        Authentication register=new Authentication();
        register.register(username,Password,ConfirmPassword,firstName,lastName,email);

        // Transition to Login window
        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }
}
