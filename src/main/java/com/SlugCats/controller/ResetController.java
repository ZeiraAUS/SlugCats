package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.NewAuth.Change;
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

/**
 * Handles the reset view's logic.
 */
public class ResetController {
    // Components of the Reset window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Button backButton, resetButton;
    @FXML
    private Label resetLabel, usernameLabel, passwordLabel, confirmPasswordLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField, confirmPasswordField;

    // Class for configuring controller components.
    private Components components = new Components();

    /**
     * Initialize the Reset window components.
     */
    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        components.setLogoImage(logoImage);
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

    /**
     * Transitions user back to log in screen.
     * @throws IOException
     */
    @FXML
    protected void onBackButtonClick() throws IOException {
        components.changeView(backButton,"login-view.fxml");
    }

    /**
     * Reset the user's password with their new one.
     * @throws IOException
     */
    @FXML
    protected void onResetButtonClick() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (Objects.equals(password, confirmPassword)) {
            //Change resetPassword = new Change();
            boolean isChanged=Change.resetpassword(username, password,confirmPassword );
            if (isChanged) {
                components.changeView(resetButton,"login-view.fxml");
            }
            else{
                confirmPasswordLabel.setText("User Does Not Exist");
                confirmPasswordLabel.setTextFill(Color.RED);
            }
        }
        else {
            confirmPasswordLabel.setText("Passwords did not match.");
            confirmPasswordLabel.setTextFill(Color.RED);
        }
    }
}
