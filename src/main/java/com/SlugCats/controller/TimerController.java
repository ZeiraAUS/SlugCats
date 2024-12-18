package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.timetracking.CountDown;
import com.SlugCats.timetracking.playtimemonitoring;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.SlugCats.gamestracking.GameDetector;
import com.SlugCats.gamestracking.SaveGame;
import com.SlugCats.Models.Game;
import com.SlugCats.Models.User;
import com.SlugCats.Models.GameTime;

import com.SlugCats.timetracking.GameTimeManager;

import java.io.File;
import java.io.IOException;

import static com.SlugCats.controller.LoginController.gotUser;

/**
 * The Timer Controller handles logic for the Timer Window.
 */
public class TimerController {
    // Components of the timer window.
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label gameLabel, playingLabel, timerLabel, setHourLabel, setMinuteLabel, setSecondLabel, selectgameLabel;
    @FXML
    private Button backButton, setButton, playButton, pauseButton, resetButton, gameDetectButton;
    @FXML
    private TextField setHourField, setMinuteField, setSecondField;

    // The countdown object for the timer.
    private final CountDown countdown = new CountDown();
    private String selectedGameTitle = "Select .EXE";

    // Class for configuring controller components.
    private final Components components = new Components();

    /**
     * Initialize the Timer window.
     */
    @FXML
    public void initialize() {
        // Initialize and configure components for the header of the window.
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

        // Configure labels and fields for setting timer display.
        VBox timerBox = new VBox(20);
        HBox timerSetBox = new HBox(20);
        timerSetBox.getChildren().addAll(
                setHourLabel,
                setHourField,
                setMinuteLabel,
                setMinuteField,
                setSecondLabel,
                setSecondField,
                setButton
        );

        // Configure components for the Timer's buttons (Play, Pause and Reset).
        HBox timerButtonBox = new HBox(20);
        components.setButtonImage(playButton,"/images/play.PNG", 150);
        components.setButtonImage(pauseButton,"/images/pause.PNG", 150);
        components.setButtonImage(resetButton,"/images/reset.PNG",150);
        timerButtonBox.getChildren().addAll(
                playButton,
                pauseButton,
                resetButton
        );
        timerBox.getChildren().addAll(
                timerLabel,
                timerSetBox,
                timerButtonBox
        );

        timerLabel.setPadding(new Insets(0.0,0.0,0.0,200));
        timerSetBox.setPadding(new Insets(0.0,0.0,0.0,140));
        timerButtonBox.setPadding(new Insets(0.0,0.0,0.0,197));

        rootPane.setTop(headerBox);
        rootPane.setCenter(timerBox);
    }

    /**
     * Fetches the detected executable and return the name for the label.
     * @return
     */
    private String getGameTitle() {
        return selectedGameTitle;
    }

    /**
     * On back button click, transition back to the home screen.
     * @throws IOException
     */
    @FXML
    protected void onBackButtonClick() throws IOException {
        components.changeView(backButton,"home-view.fxml");
    }

    /**
     * Set the timer time when the set button is clicked.
     * @throws IOException
     */
    @FXML
    protected void onSetButtonClick() throws IOException {
        if(countdown.getTimeline().getStatus() != Animation.Status.RUNNING)
        {
            setupCountDown();
            setTimeLabel();
        }
    }

    /**
     * Play/Unpause the timer countdown when the play button is clicked.
     * @throws IOException
     */
    @FXML
    protected void onPlayButtonClick() throws IOException {
        if(countdown.getActive())
        {
            countdown.run();
        }
    }

    /**
     * Pause the timer's countdown when the pause button is clicked.
     * @throws IOException
     */
    @FXML
    protected void onPauseButtonClick() throws IOException {
        if(countdown.getActive())
        {
            countdown.getTimeline().pause();
            String[] timeLabel = timerLabel.getText().split(":");
            countdown.pauseTime(Integer.parseInt(timeLabel[0]),Integer.parseInt(timeLabel[1]),Integer.parseInt(timeLabel[2]));
        }
    }

    /**
     * Reset the timer countdown when the reset button is clicked.
     * @throws IOException
     */
    @FXML
    protected void onResetButtonClick() throws IOException {
        if(countdown.getActive())
        {
            countdown.getTimeline().stop();
            countdown.resetTime();
            setTimeLabel();
            countdown.run();
        }
    }

    /**
     * Set the user's input for the timer.
     */
    protected void setupCountDown() {
        countdown.setLabels(timerLabel);
        String hour = setHourField.getText();
        String min = setMinuteField.getText();
        String seconds = setSecondField.getText();

        // Check if all inputs are valid integers
        if (isValidInteger(hour) && isValidInteger(min) && isValidInteger(seconds)) {
            int hourTime = Integer.parseInt(hour);
            int minTime = Integer.parseInt(min);
            int secTime = Integer.parseInt(seconds);

            countdown.setTime(hourTime, minTime, secTime);
            countdown.setActive(true);
        } else {
            setButton.setTextFill(Color.RED);
        }
    }

    /**
     * Check time validity.
     * @param time String to check.
     * @return Validity of time.
     */
    private boolean isValidInteger(String time) {
        try {
            Integer.parseInt(time);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * Set the timer's display label as the countdown functions.
     */
    protected void setTimeLabel()
    {
        int[] time = countdown.getTime();
        timerLabel.setText(
                time[0] + ":"  + time[1] + ":" + time[2]
        );
    }

    /**
     * Open explorer for user to select a game to track.
     * @throws IOException
     */
    @FXML
    protected void onGameDetectButtonClick() throws IOException {
        playtimemonitoring.stopTracking();
        playtimemonitoring.trackedPlayTime = 0;

        GameDetector gameDetector = new GameDetector();
        File selectedFile = gameDetector.choosefile();

        if (selectedFile != null && selectedFile.exists()) {
            String gameName = selectedFile.getName();
            String displayName = extractGameTitle(gameName);

            selectedGameTitle = displayName;
            String processName = selectedFile.getName();

            saveGameDetails(selectedGameTitle, processName);

            Game existingGame = new SaveGame().gotGame(selectedGameTitle);
            int gameId = (existingGame != null) ? existingGame.getGameId() : -1;

            User user = gotUser();
            int userId = user.getUserId();

            GameTimeManager newGameTime = new GameTimeManager();
            newGameTime.saveGameTime(userId, gameId);

            playtimemonitoring.startTracking(processName);

            Thread trackerThread = new Thread(() -> {
                try {
                    while (playtimemonitoring.isTracking()) {
                        Thread.sleep(1000);
                    }
                    long lastSessionPlayTime = playtimemonitoring.getTrackedPlayTime();

                    if (lastSessionPlayTime != -1) {
                        GameTimeManager gameTimeManager = new GameTimeManager();

                        GameTime existingGameTime = gameTimeManager.getGameTime(userId, gameId);
                        long totalPlayTime = existingGameTime != null ? existingGameTime.getTotalPlaytime() : 0;

                        totalPlayTime += lastSessionPlayTime;

                        gameTimeManager.updateGameTime(userId, gameId, totalPlayTime, lastSessionPlayTime);

                    } else {
                        System.out.println("Error retrieving tracked playtime.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            trackerThread.setDaemon(true);
            trackerThread.start();
        } else {
            selectedGameTitle = "No Game Detected";
        }
        gameLabel.setText(selectedGameTitle);
    }

    /**
     * Extract game name from the selected executable file.
     * @param gameName Name of the EXE
     * @return The name of the game without file type.
     */
    private String extractGameTitle(String gameName) {
        int lastDotIndex = gameName.lastIndexOf('.');
        if (lastDotIndex != -1) {
            return gameName.substring(0, lastDotIndex);
        } else {
            return gameName;
        }
    }

    /**
     * Save the selected game.
     * @param gameTitle Name of the Game
     * @param processName Name of the Detected Process
     */
    private void saveGameDetails(String gameTitle, String processName) {
        SaveGame newGame = new SaveGame();
        newGame.saveGame(gameTitle, processName);
    }
}
