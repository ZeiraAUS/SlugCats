package com.SlugCats.timetracking;

import java.awt.Toolkit;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.time.LocalTime;
public class CountDown
{
    public boolean Active = false;
    int originalHours,originalMins,originalSeconds;
    int hours, mins, seconds;
    final Runnable windowsSound = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
    @FXML
    private Label timerHourLabel;
    @FXML
    private Label timerMinuteLabel;
    @FXML
    private Label timerSecondLabel;
    public Timeline timeline;

    public void run() {
        resetTime();
        LocalTime end = LocalTime.now()
                .plusHours(hours)
                .plusMinutes(mins)
                .plusSeconds(seconds);
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e ->
        {
            java.time.Duration remaining = java.time.Duration.between(LocalTime.now().minusSeconds(1), end);
            if (remaining.isPositive()) {
                //timerLabel.setText(format(remaining));
                timerHourLabel.setText(Integer.toString(remaining.toHoursPart()));
                timerMinuteLabel.setText(Integer.toString(remaining.toMinutesPart()));
                timerSecondLabel.setText(Integer.toString(remaining.toSecondsPart()));
            } else {
                //timerLabel.setText(format(java.time.Duration.ZERO));
                timerHourLabel.setText(Integer.toString(java.time.Duration.ZERO.toHoursPart()));
                timerMinuteLabel.setText(Integer.toString(java.time.Duration.ZERO.toMinutesPart()));
                timerSecondLabel.setText(Integer.toString(java.time.Duration.ZERO.toSecondsPart()));
                windowsSound.run();
                timeline.stop();
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void setTime(int newHours, int newMins, int newSeconds)
    {
        originalHours = newHours;
        originalMins = newMins;
        originalSeconds = newSeconds;
    }
    public void resetTime()
    {
        hours = originalHours;
        mins = originalMins;
        seconds = originalSeconds;
    }
    public void GetLabels(Label hourLabel, Label minLabel, Label secondLabel)
    {
        timerHourLabel = hourLabel;
        timerMinuteLabel = minLabel;
        timerSecondLabel = secondLabel;
    }
    private String format(java.time.Duration remaining) {
        return String.format("%02d:%02d:%02d",
                remaining.toHoursPart(),
                remaining.toMinutesPart(),
                remaining.toSecondsPart()
        );
    }
}
