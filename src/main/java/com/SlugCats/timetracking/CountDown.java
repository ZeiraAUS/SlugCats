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
    int originalHours,originalMins,originalSeconds;
    int hours, mins, seconds;
    final Runnable windowsSound;
    @FXML
    private Label timerLabel;
    public Timeline timeline;
    public boolean Active = false;

    public CountDown()
    {
        windowsSound = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        timeline = new Timeline();
    }
    public void run() {
        LocalTime end = LocalTime.now()
                .plusHours(hours)
                .plusMinutes(mins)
                .plusSeconds(seconds);
        if(timeline.getStatus() == Animation.Status.RUNNING)
        {
            timeline.stop();
        }
        timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e ->
        {

            java.time.Duration remaining = java.time.Duration.between(LocalTime.now().minusSeconds(1), end);

            if (!remaining.isNegative() && !remaining.isZero()) {
                timerLabel.setText(format(remaining));
            } else {
                timerLabel.setText(format(java.time.Duration.ZERO));
                windowsSound.run();
                timeline.stop();
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


    }
    public void pauseTime(int newHours, int newMins, int newSeconds)
    {
        hours = newHours;
        mins = newMins;
        seconds = newSeconds;
    }
    public void setTime(int newHours, int newMins, int newSeconds)
    {

        if(newSeconds > 60)
        {
            newMins += (newSeconds - 60) / 60;
            newSeconds = 60;
        }
        if(newMins > 60)
        {
            newHours += (newMins - 60) / 60;
            newMins = 60;
        }
        if(newHours > 24)
        {
            newHours = 24;
        }
        originalHours = newHours;
        originalMins = newMins;
        originalSeconds = newSeconds;
        hours = originalHours;
        mins = originalMins;
        seconds = originalSeconds;
    }
    public int[] getTime()
    {
        return new int[]{hours,mins,seconds};
    }
    public void resetTime()
    {
        hours = originalHours;
        mins = originalMins;
        seconds = originalSeconds;
    }
    public void setLabels(Label timerLabel)
    {
        this.timerLabel = timerLabel;
    }
    private String format(java.time.Duration remaining) {
        return String.format("%02d:%02d:%02d",
                remaining.toHoursPart(),
                remaining.toMinutesPart(),
                remaining.toSecondsPart()
        );
    }
}