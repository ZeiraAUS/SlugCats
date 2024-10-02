package com.SlugCats.controller;

import com.SlugCats.Main;
import com.SlugCats.timetracking.Stopwatch;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
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

/**
 * The Stopwatch Controller handles logic for the Stopwatch Window.
 */
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
    private Label timerLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button resetButton;

    // The stopwatch object for the timer.
    private Stopwatch stopwatch = new Stopwatch();

    // AnimationTimer to continuously update the UI
    private AnimationTimer timer;

    /**
     * Initialize the Stopwatch window.
     */
    @FXML
    public void initialize() {
        // Initialize and configure components for the header of the window.
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

        // Configure buttons (Start, Stop, Reset) and their actions
        startButton.setOnAction(event -> onStartButtonClick());
        stopButton.setOnAction(event -> onStopButtonClick());
        resetButton.setOnAction(event -> onResetButtonClick());

        rootPane.setTop(headerBox);
        rootPane.setCenter(timerLabel);
        rootPane.setBottom(createButtonBox());

        // Create an AnimationTimer to update the timer label every frame
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateTimerLabel();
            }
        };
    }

    /**
     * Fetches the detected executable and return the name for the label.
     * @return
     */
    private String getGameTitle() {
        // Placeholder for game title
        return "SnailCat™ Stopwatch";
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
     * Start the stopwatch when the start button is clicked.
     */
    @FXML
    protected void onStartButtonClick() {
        stopwatch.start();
        timer.start();
    }

    /**
     * Stop the stopwatch when the stop button is clicked.
     */
    @FXML
    protected void onStopButtonClick() {
        stopwatch.stop();
        timer.stop();
    }

    /**
     * Reset the stopwatch when the reset button is clicked.
     */
    @FXML
    protected void onResetButtonClick() {
        stopwatch.reset();
        updateTimerLabel();
    }

    /**
     * Update the label displaying the stopwatch's elapsed time.
     */
    private void updateTimerLabel() {
        timerLabel.setText(stopwatch.getelapsedTime());
    }

    /**
     * Create a box for the buttons (Start, Stop, Reset)
     */
    private HBox createButtonBox() {
        HBox buttonBox = new HBox(20);
        buttonBox.getChildren().addAll(startButton, stopButton, resetButton);
        return buttonBox;
    }

    public void onSetButtonClick(ActionEvent actionEvent) {
    }
}
