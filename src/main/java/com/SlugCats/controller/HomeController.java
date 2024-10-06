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
import com.SlugCats.DAOs.*;
import com.SlugCats.Models.User;

import java.io.IOException;

import static com.SlugCats.controller.LoginController.gotUser;

/**
 * HomeController provides the functionality for the Home window.
 */
public class HomeController {
    // Components of the Home window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label usernameLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button timerButton;
    @FXML
    private Button stopwatchButton;
    @FXML
    private Button statisticsButton;

    /**
     * The initialize method includes logic to add graphics and additional functionality to the window components.
     */
    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        logoImage.setImage(logo);
        SetUsername();
        HBox headerBox = new HBox(20);
        VBox headerButtonsBox = new VBox(20);
        Image settings = new Image(getClass().getResource("/images/cog.PNG").toString(),true);
        ImageView settingsView = new ImageView(settings);
        settingsView.setFitHeight(50.0);
        settingsView.setPreserveRatio(true);
        settingsButton.setGraphic(settingsView);
        headerButtonsBox.getChildren().addAll(
                logoutButton,
                settingsButton
        );
        headerBox.getChildren().addAll(
                logoImage,
                headerButtonsBox,
                usernameLabel
        );
        rootPane.setTop(headerBox);

        // Home Screen Buttons
        HBox buttonsBox = new HBox(20);
        // Timer Button
        Image timer = new Image(getClass().getResource("/images/hourglass.PNG").toString(),true);
        ImageView timerView = new ImageView(timer);
        timerView.setFitHeight(375.0);
        timerView.setPreserveRatio(true);
        timerButton.setGraphic(timerView);
        timerButton.setContentDisplay(ContentDisplay.TOP);

        // Stopwatch and Statistics Buttons
        Image stopwatch = new Image(getClass().getResource("/images/stopwatch.PNG").toString(), true);
        Image statistics = new Image(getClass().getResource("/images/statistics.PNG").toString(), true);
        ImageView stopwatchView = new ImageView(stopwatch);
        ImageView statisticsView = new ImageView(statistics);
        stopwatchView.setFitHeight(193.0);
        statisticsView.setFitHeight(192.0);
        stopwatchView.setPreserveRatio(true);
        statisticsView.setPreserveRatio(true);
        stopwatchButton.setGraphic(stopwatchView);
        statisticsButton.setGraphic(statisticsView);

        VBox subButtonsBox = new VBox(20);
        subButtonsBox.getChildren().addAll(
                stopwatchButton,
                statisticsButton
        );

        buttonsBox.getChildren().addAll(
                timerButton,
                subButtonsBox
        );
        rootPane.setCenter(buttonsBox);
    }

    /**
     * Gets the username of the logged-in user and sets it to the label.
     */
    private void SetUsername() {
        User user = gotUser();
        String userName = user.getUserName();
        usernameLabel.setText(userName);
    }

    /**
     * Transition user back to the login screen when the Logout button is clicked.
     * @throws IOException
     */
    @FXML
    protected void onLogoutButtonClick() throws IOException {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    /**
     * Transitions user to Timer window when they click the Timer button.
     * @throws IOException
     */
    @FXML
    protected void onTimerButtonClick() throws IOException {
        Stage stage = (Stage) timerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("timer-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onStopwatchButtonClick() throws IOException {
        Stage stage = (Stage) stopwatchButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("stopwatch-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onStatisticsButtonClick() throws IOException {
        Stage stage = (Stage) statisticsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("statistics-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onSettingsButtonClick() throws IOException {
        Stage stage = (Stage) settingsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("settings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }
}