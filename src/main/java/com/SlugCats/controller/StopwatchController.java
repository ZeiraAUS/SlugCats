package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.timetracking.Stopwatch;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StopwatchController {

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
    private Label stopwatchLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button resetButton;

    // Stopwatch object for tracking time
    private Stopwatch stopwatch = new Stopwatch();

    // AnimationTimer for updating UI
    private AnimationTimer timer;

    @FXML
    public void initialize() {
        // Initialize the logo and game title
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(), true);
        logoImage.setImage(logo);
        gameLabel.setText(getGameTitle());
        HBox headerBox = new HBox(20);
        VBox gameBox = new VBox(20);
        gameBox.getChildren().addAll(
                playingLabel,
                gameLabel
        );
        headerBox.getChildren().addAll(
                logoImage,
                gameBox,
                backButton
        );

        VBox stopwatchBox = new VBox(20);

        // Configure components for the Timer's buttons (Play, Pause and Reset).
        HBox stopwatchButtonBox = new HBox(20);
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
        startButton.setText("");
        stopButton.setText("");
        resetButton.setText("");
        startButton.setGraphic(playView);
        stopButton.setGraphic(pauseView);
        resetButton.setGraphic(resetView);
        stopwatchButtonBox.getChildren().addAll(
                startButton,
                stopButton,
                resetButton
        );
        stopwatchBox.getChildren().addAll(
                stopwatchLabel,
                stopwatchButtonBox
        );

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
        return "SnailCat™ Stopwatch";
    }

    /**
     * Handle back button click and navigate back to the home screen.
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
     * Start the stopwatch and timer when the start button is clicked.
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
}