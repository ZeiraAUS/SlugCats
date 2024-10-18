package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.gamestracking.GameDetector;
import com.SlugCats.gamestracking.SaveGame;
import com.SlugCats.timetracking.Stopwatch;
import com.SlugCats.timetracking.playtimemonitoring;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Handles the stopwatch view logic.
 */
public class StopwatchController {
    // Components for the Stopwatch window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label gameLabel, playingLabel, selectgameLabel, stopwatchLabel;
    @FXML
    private Button gameDetectButton, backButton, startButton, stopButton, resetButton;

    // Stopwatch object for tracking time
    private Stopwatch stopwatch = new Stopwatch();
    private String selectedGameTitle = "No Game Detected";

    // AnimationTimer for updating UI
    private AnimationTimer timer;

    // Class for configuring controller components.
    private Components components = new Components();

    /**
     * Initialize components for the stopwatch window.
     */
    @FXML
    public void initialize() {
        // Initialize the logo and game title
        components.setLogoImage(logoImage);
        gameLabel.setText(getGameTitle());
        HBox headerBox = new HBox(20);
        VBox gameBox = new VBox(20);
        gameBox.getChildren().addAll(
                playingLabel,
                gameLabel
        );

        components.setButtonImage(gameDetectButton,"/images/folder.PNG",25);
        VBox detectGameBox = new VBox(20);
        detectGameBox.getChildren().addAll(
                selectgameLabel,
                gameDetectButton
        );
        headerBox.getChildren().addAll(
                logoImage,
                backButton,
                gameBox,
                detectGameBox
        );

        VBox stopwatchBox = new VBox(20);

        // Configure components for the Timer's buttons (Play, Pause and Reset).
        HBox stopwatchButtonBox = new HBox(20);
        components.setButtonImage(startButton,"/images/play.PNG", 150);
        components.setButtonImage(stopButton,"/images/pause.PNG", 150);
        components.setButtonImage(resetButton,"/images/reset.PNG",150);
        stopwatchButtonBox.getChildren().addAll(
                startButton,
                stopButton,
                resetButton
        );
        stopwatchBox.getChildren().addAll(
                stopwatchLabel,
                stopwatchButtonBox
        );

        stopwatchLabel.setPadding(new Insets(0.0,0.0,0.0,200));
        stopwatchButtonBox.setPadding(new Insets(0.0,0.0,0.0,185));

        rootPane.setTop(headerBox);
        rootPane.setCenter(stopwatchBox);

        // Create an AnimationTimer to update the stopwatch label every frame
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateStopwatchLabel();
            }
        };
    }

    /**
     * Get the detected game title for the label.
     */
    private String getGameTitle() {
        return selectedGameTitle;
    }

    /**
     * Handle back button click and navigate back to the home screen.
     */
    @FXML
    protected void onBackButtonClick() throws IOException {
        components.changeView(backButton,"home-view.fxml");
    }

    /**
     * Start the stopwatch when the start button is clicked.
     */
    @FXML
    protected void onStartButtonClick() {
        stopwatch.start();
        timer.start();
    }

    /**
     * Stop the stopwatch and timer when the stop button is clicked.
     */
    @FXML
    protected void onStopButtonClick() {
        stopwatch.stop();
        timer.stop();
    }

    /**
     * Reset the stopwatch and update the label when the reset button is clicked.
     */
    @FXML
    protected void onResetButtonClick() {
        stopwatch.reset();
        updateStopwatchLabel();
    }

    /**
     * Update the label displaying the stopwatch's elapsed time.
     */
    private void updateStopwatchLabel() {
        stopwatchLabel.setText(stopwatch.getelapsedTime());
    }

    @FXML
    protected void onGameDetectButtonClick() {
        GameDetector gameDetector = new GameDetector();
        File selectedFile = gameDetector.choosefile();

        if (selectedFile != null && selectedFile.exists()) {
            String gameName = selectedFile.getName();
            String displayName;

            int lastDotIndex = gameName.lastIndexOf('.');
            if (lastDotIndex != -1) {
                displayName = gameName.substring(0, lastDotIndex);
            } else {
                displayName = gameName;
            }

            selectedGameTitle = displayName;
            String processName = selectedFile.getName();

            SaveGame newGame = new SaveGame();

            boolean isSaved = newGame.saveGame(selectedGameTitle, processName);

            //Can remove in the future - it was for debugging
            if (isSaved) {
                System.out.println("Game saved.");
            }
            else {
                System.out.println("Gamesave failed");
            }

            playtimemonitoring.startTracking(selectedFile.getName());

        } else {
            selectedGameTitle = "No Game Detected";
        }

        // Update the label to show just the game name
        gameLabel.setText(selectedGameTitle);
    }
}
