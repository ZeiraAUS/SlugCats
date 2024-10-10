package com.SlugCats.controller;

import com.SlugCats.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.SlugCats.DAOs.*;
import com.SlugCats.Models.User;

import java.io.IOException;
import java.util.Objects;

public class ResetController {
    // Components of the Reset window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button backButton;
    @FXML
    private Label resetLabel;
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
    private Button resetButton;

    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        logoImage.setImage(logo);
        HBox headerBox = new HBox(20);
        headerBox.getChildren().addAll(
                logoImage,
                backButton,
                resetLabel
        );
        rootPane.setTop(headerBox);

        // Configure the components of the register form.
        VBox formLabelBox = new VBox(20);
        passwordLabel.setPadding(new Insets(15,0,0,0));
        confirmPasswordLabel.setPadding(new Insets(15,0,0,0));
        formLabelBox.getChildren().addAll(
                usernameLabel,
                passwordLabel,
                confirmPasswordLabel,
                resetButton
        );
        VBox formFieldBox = new VBox(20);
        formFieldBox.getChildren().addAll(
                usernameField,
                passwordField,
                confirmPasswordField
        );

        rootPane.setLeft(formLabelBox);
        rootPane.setRight(formFieldBox);
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onResetButtonClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (Objects.equals(password, confirmPassword)) {
            // Reset Password Logic uijvwhbcnwijcbewiubceiw :)

            Stage stage = (Stage) resetButton.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        else {
            confirmPasswordLabel.setText("Passwords did not match.");
            confirmPasswordLabel.setTextFill(Color.RED);
        }
    }
}
