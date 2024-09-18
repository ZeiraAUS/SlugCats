package com.SlugCats.controller;

import com.SlugCats.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private BorderPane rootPane;

    @FXML
    private ImageView logoImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button logoutButton;

    @FXML
    private Button timerButton;
    @FXML
    private Label timerLabel;

    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        //WORKING FOR TEAM
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        //WORKING FOR AMY because one drive ruins my life
        //Image logo = new Image("C:\\Users\\amy_c\\OneDrive\\Desktop\\ED\\CAB302\\SlugCats\\src\\main\\resources\\images\\snailcat.PNG");
        logoImage.setImage(logo);

        SetUsername();

        HBox headerBox = new HBox(20);
        headerBox.getChildren().addAll(
            logoImage,
            logoutButton,
            usernameLabel

        );
        rootPane.setTop(headerBox);

        // Timer Button
        //TEAM
        Image timer = new Image(getClass().getResource("/images/hourglass.PNG").toString(),true);
        //AMY
        //Image timer = new Image("C:\\Users\\amy_c\\OneDrive\\Desktop\\ED\\CAB302\\SlugCats\\src\\main\\resources\\images\\hourglass.PNG");
        ImageView timerView = new ImageView(timer);
        timerView.setFitHeight(350.0);
        timerView.setPreserveRatio(true);
        timerButton.setGraphic(timerView);

        VBox timerBox = new VBox(20);
        timerBox.getChildren().addAll(
            timerButton,
            timerLabel
        );
        rootPane.setLeft(timerBox);
    }

    //NOTE: Whoever is handling the login stuff, please use this function to change the usernameLabel to the currently logged-in user.
    private void SetUsername() {
        //Example
        //get the email of user
        //...

        //set the email into the label
        usernameLabel.setText("Mackenzie@hotmail.com");
    }

    //NOTE: Include logic of logging the user out.
    @FXML
    protected void onLogoutButtonClick() throws IOException {
        //Logout user logic here VVV
        //...

        // Transition back to Log in Window
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onTimerButtonClick() throws IOException {
        // Transition to Timer window
        Stage stage = (Stage) timerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("timer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }
}