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
    private Timeline timeline;
    private boolean Active = false;

    public CountDown()
    {
        windowsSound = (Runnable) Toolkit.getDefaultToolkit().getDesktopProperty("win.sound.exclamation");
        timeline = new Timeline();
    }

    /**
     * used to start the countdown timer
     * automatically updates the timer per second
     */
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

    /**
     * used to set time to a specified time in the format of HH:MM:SS
     * @param newHours
     * @param newMins
     * @param newSeconds
     */
    public void pauseTime(int newHours, int newMins, int newSeconds)
    {
        hours = newHours;
        mins = newMins;
        seconds = newSeconds;
    }

    /**
     * used to set the time to a new time and automatically converts the time into the proper format
     * @param newHours
     * @param newMins
     * @param newSeconds
     */
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

    /**
     * getter for the current time
     * @return
     */
    public int[] getTime()
    {
        return new int[]{hours,mins,seconds};
    }

    /**
     * resets the current time to the time that the countdown was set to
     */
    public void resetTime()
    {
        hours = originalHours;
        mins = originalMins;
        seconds = originalSeconds;
    }

    /**
     * intialises the timer label
     * @param timerLabel
     */
    public void setLabels(Label timerLabel)
    {
        this.timerLabel = timerLabel;
    }

    /**
     * formats a time correctly
     * @param remaining
     * @return
     */
    private String format(java.time.Duration remaining) {
        return String.format("%02d:%02d:%02d",
                remaining.toHoursPart(),
                remaining.toMinutesPart(),
                remaining.toSecondsPart()
        );
    }

    /**
     * getter for the timeline object
     * @return
     */
    public Timeline getTimeline()
    {
        return timeline;
    }

    /**
     * getter for the active bool
     * @return
     */
    public boolean getActive()
    {
        return Active;
    }

    /**
     * setter for the active bool
     * @param active
     */
    public void setActive(boolean active)
    {
        Active = active;
    }

}
