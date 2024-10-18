package com.SlugCats.controller;
import com.SlugCats.DAOs.UserDAO;
import com.SlugCats.NewAuth.LoginStatus;
import com.SlugCats.NewAuth.login_status;
import com.SlugCats.Models.User;
import com.SlugCats.DAOs.*;

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
    private Label loginLabel, usernameLabel, passwordLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private HBox buttonBox;
    @FXML
    private Button loginButton, registerButton,resetButton;

    public static User user;

    // Class for configuring controller components.
    private Components components = new Components();

    /**
     * Initialise the Login window's components.
     */
    @FXML
    public void initialize() {
        components.setLogoImage(logoImage);

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
        boolean credentialValidity;
        if(if_already_login()!=null){
            user=if_already_login();
            credentialValidity=true;
            System.out.println("keep logged in working");
        }else{

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        login_status loginStatus = new login_status();
        loginStatus.is_login(username,password);
        boolean is_login=loginStatus.gotLogin_status();
        user=loginStatus.getUser();
        credentialValidity = is_login;}

        // If credentials are valid, log them in, otherwise display a prompt.
        if (credentialValidity) {
            components.changeView(loginButton,"home-view.fxml");
        }
        else {
            // Error pop-up when user credentials are invalid.
            loginButton.setText("Invalid Credentials");
            loginButton.setTextFill(Color.RED);
        }

    }

    /**
     * If user is already logged in, get their username.
     * @return
     */
    private User if_already_login(){
        String  username=LoginStatus.getLoggedInUsername();
        if(username==null){

            return null;
        }
        UserDAO userDAO=new UserDAO();
        User loginUser= userDAO.getUserByUsername(username);
        return loginUser;
    }
   
    /**
     * When the register button is clicked, transition the user to the register window.
     * @throws IOException
     */
    @FXML
    protected void onRegisterButtonClick() throws IOException {
        components.changeView(registerButton,"register-view.fxml");
    }

    /**
     * Get the user.
     * @return
     */
    static public User gotUser(){
        return user;
    }

    /**
     * Transitions user to reset password window.
     * @throws IOException
     */
    @FXML
    protected void onResetButtonClick() throws IOException {
        components.changeView(resetButton,"reset-view.fxml");
    }
}
