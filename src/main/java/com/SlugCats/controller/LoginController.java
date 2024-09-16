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
    private Label emailLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    // Buttons in HBox
    @FXML
    private HBox buttonBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    /**
     * Initialise the Login window's components.
     */
    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        //WORKING FOR TEAM
        Image logo = new Image(getClass().getResource("/images/slugcat.PNG").toString(),true);
        //WORKING FOR AMY because one drive ruins my life
        //Image logo = new Image("C:\\Users\\amy_c\\OneDrive\\Desktop\\ED\\CAB302\\SlugCats\\src\\main\\resources\\images\\snailcat.PNG");
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

    /**
     * Login button click.
     * @throws IOException
     */
    @FXML
    protected void onLoginButtonClick() throws IOException {
        //NOTE: Put your login logic here. - You can reorganise my placeholder logic if you need to.
        String emailInput = emailField.toString();
        String passwordInput = passwordField.toString();
        Boolean credentialValidity = AuthenticateUser(emailInput,passwordInput);

        if (credentialValidity) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            ContinueHomeWindow(stage);
        }
        else {
            //idk some popup appears that says credentials are wrong WIP
        }

    }

    // please insert your authentication logic here
    private Boolean AuthenticateUser(String emailInput, String passwordInput) {
        return true;
    }

    /**
     * Register button click.
     * @throws IOException
     */
    @FXML
    protected void onRegisterButtonClick() throws IOException {
        //NOTE: Put your register logic here. - You can reorganise my placeholder logic if you need to.
        String emailInput = emailField.toString();
        String passwordInput = passwordField.toString();
        Boolean registerSuccess = RegisterUser(emailInput, passwordInput);

        if (registerSuccess) {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            ContinueHomeWindow(stage);
        }
        else {
            // some kind of pop up saying otherwise WIP
        }

    }

    //insert your register logic here plz and ty
    private Boolean RegisterUser(String emailInput, String passwordInput) {
        return true;
    }

    /**
     * Switch to Home Screen window.
     * @param stage The new window. Sourced from either login or register button.
     * @throws IOException
     */
    protected void ContinueHomeWindow(Stage stage) throws  IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }


}
