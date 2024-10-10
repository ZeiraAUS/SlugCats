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
import javafx.stage.Stage;
import com.SlugCats.gamestracking.GameDetector;
import com.SlugCats.gamestracking.SaveGame;

import java.io.File;
import java.io.IOException;

/**
 * The Timer Controller handles logic for the Timer Window.
 */
public class TimerController {
    @FXML
    private BorderPane rootPane;
    @FXML
    private ImageView logoImage;
    @FXML
    private Label gameLabel;
    @FXML
    private Label playingLabel;
    @FXML
    private Button backButton;
    @FXML
    private Label timerLabel;
    @FXML
    private Label setHourLabel;
    @FXML
    private TextField setHourField;
    @FXML
    private Label setMinuteLabel;
    @FXML
    private TextField setMinuteField;
    @FXML
    private Label setSecondLabel;
    @FXML
    private TextField setSecondField;
    @FXML
    private Button setButton;
    @FXML
    private Button playButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button gameDetectButton;
    @FXML
    private Label selectgameLabel;

    // The countdown object for the timer.
    private CountDown countdown = new CountDown();
    private String selectedGameTitle = "No Game Detected";

    /**
     * Initialize the Timer window.
     */
    @FXML
    public void initialize() {
        // Initialize and configure components for the header of the window.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);
        logoImage.setImage(logo);
        gameLabel.setText(getGameTitle());
        HBox headerBox = new HBox(20);
        VBox gameBox = new VBox(20);
        gameBox.getChildren().addAll(
                playingLabel,
                gameLabel
        );

        Image selectGame = new Image(getClass().getResource("/images/folder.PNG").toString(),true);
        ImageView selectView = new ImageView(selectGame);
        selectView.setFitHeight(25);
        selectView.setPreserveRatio(true);
        gameDetectButton.setGraphic(selectView);
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
        Image play = new Image(getClass().getResource("/images/play.PNG").toString(),true);
        Image pause = new Image(getClass().getResource("/images/pause.PNG").toString(),true);
        Image reset = new Image(getClass().getResource("/images/reset.PNG").toString(),true);
        ImageView playView = new ImageView(play);
        ImageView pauseView = new ImageView(pause);
        ImageView resetView = new ImageView(reset);
        playView.setFitHeight(150.0);
        pauseView.setFitHeight(150.0);
        resetView.setFitHeight(150.0);
        playView.setPreserveRatio(true);
        pauseView.setPreserveRatio(true);
        resetView.setPreserveRatio(true);
        playButton.setGraphic(playView);
        pauseButton.setGraphic(pauseView);
        resetButton.setGraphic(resetView);
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
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
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
    protected void setupCountDown()
    {
        countdown.setLabels(timerLabel);
        int hour = Integer.parseInt(setHourField.getText());
        int min = Integer.parseInt(setMinuteField.getText());
        int seconds = Integer.parseInt(setSecondField.getText());
        countdown.setTime(hour,min,seconds);
        countdown.setActive(true);
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

    @FXML
    protected void onGameDetectButtonClick() throws IOException {
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

            if (isSaved) {
                System.out.println("Game successfully saved.");
            } else {
                System.out.println("Failed to save game.");
            }

            playtimemonitoring.startTracking(selectedFile.getName());

        } else {
            selectedGameTitle = "No Game Detected";
        }

        gameLabel.setText(selectedGameTitle);
    }

}
