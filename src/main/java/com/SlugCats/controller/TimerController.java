package com.SlugCats.controller;
import com.SlugCats.Main;
import com.SlugCats.timetracking.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;

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
    private CountDown countdown = new CountDown();
    @FXML
    public void initialize() {


        // Initialise and set the logo image into the image view.
        Image logo = new Image(getClass().getResource("/images/snailcat.PNG").toString(),true);

        logoImage.setImage(logo);

        gameLabel.setText(GetGameTitle());

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

        HBox timerButtonBox = new HBox(20);
        // Timer Buttons Button
        //TEAM
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

        rootPane.setTop(headerBox);
        rootPane.setCenter(timerBox);

    }

    //NOTE: Include logic for getting the title of the tracked application and return it here.
    private String GetGameTitle() {
        //Placeholder
        return "SnailCat TM";
    }

    @FXML
    protected void onBackButtonClick() throws IOException {
        // Transition to Timer window
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), Main.WIDTH, Main.HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
    }

    @FXML
    protected void onSetButtonClick() throws IOException {
        if(countdown.timeline.getStatus() != Animation.Status.RUNNING)
        {
            timerLabel.setText(
                    setHourField.getText() + ":" + setMinuteField.getText() + ":" + setSecondField.getText()
            );
            SetupCountDown();
        }

    }

    //NOTE: Whoever's handling the timer display logic, put it in here VVV
    @FXML
    protected void onPlayButtonClick() throws IOException {
        countdown.run();

    }

    @FXML
    protected void onPauseButtonClick() throws IOException {
        countdown.timeline.pause();
    }

    @FXML
    protected void onResetButtonClick() throws IOException {
        countdown.timeline.stop();
        countdown.resetTime();
        countdown.run();
    }
    protected void SetupCountDown()
    {
        countdown.SetLabels(timerLabel);
        int hour = Integer.parseInt(setHourField.getText());
        int min = Integer.parseInt(setMinuteField.getText());
        int seconds = Integer.parseInt(setSecondField.getText());
        countdown.setTime(hour,min,seconds);
    }

}
