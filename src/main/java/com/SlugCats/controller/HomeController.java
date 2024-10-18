package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.NewAuth.LoginStatus;
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
    private Button logoutButton, settingsButton, timerButton, stopwatchButton, statisticsButton;

    // Class for configuring controller components.
    private Components components = new Components();

    /**
     * The initialize method includes logic to add graphics and additional functionality to the window components.
     */
    @FXML
    public void initialize() {
        // Initialise and set the logo image into the image view.
        components.setLogoImage(logoImage);
        SetUsername();
        HBox headerBox = new HBox(20);
        VBox headerButtonsBox = new VBox(20);
        components.setButtonImage(settingsButton,"/images/cog.PNG",50);
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
        components.setButtonImage(timerButton,"/images/hourglass.PNG",375);
        timerButton.setContentDisplay(ContentDisplay.TOP);

        // Stopwatch and Statistics Buttons
        components.setButtonImage(stopwatchButton,"/images/stopwatch.PNG",193);
        components.setButtonImage(statisticsButton,"/images/statistics.PNG",192);

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
        components.changeView(logoutButton,"login-view.fxml");
    }

    /**
     * Transitions user to Timer window when they click the Timer button.
     * @throws IOException
     */
    @FXML
    protected void onTimerButtonClick() throws IOException {
        components.changeView(timerButton,"timer-view.fxml");
    }

    /**
     * Transitions user to Stopwatch window.
     * @throws IOException
     */
    @FXML
    protected void onStopwatchButtonClick() throws IOException {
        components.changeView(stopwatchButton,"stopwatch-view.fxml");
    }

    /**
     * Transitions user to Statistics window.
     * @throws IOException
     */
    @FXML
    protected void onStatisticsButtonClick() throws IOException {
        components.changeView(statisticsButton,"statistics-view.fxml");
    }

    /**
     * Transitions user to Settings window.
     * @throws IOException
     */
    @FXML
    protected void onSettingsButtonClick() throws IOException {
        components.changeView(settingsButton,"settings-view.fxml");
    }
}