package com.SlugCats.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

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
    public void initialize() {
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
}
