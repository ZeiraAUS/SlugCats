package com.SlugCats.timetracking;

public class Stopwatch {
    private long startTime;
    private long stopTime;
    private long totalTime;
    private boolean isRunning;

    /**
     * Constructs a Stopwatch with an initial state of not running.
     */
    public Stopwatch() {
        this.isRunning = false;
    }

    /**
     * Starts the stopwatch. If it is already running, this method has no effect.
     * It adjusts the startTime based on the total time already recorded.
     */
    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - totalTime;;
            isRunning = true;
        }
    }

    /**
     * Stops the stopwatch and records the total elapsed time since it was started.
     * If the stopwatch is not running, this method has no effect.
     */
    public void stop() {
        if (isRunning) {
            stopTime = System.currentTimeMillis();
            totalTime = stopTime - startTime;
            isRunning = false;
        }
    }

    /**
     * Resets the stopwatch to its initial state. It stops the stopwatch if it is running
     * and resets the recorded time to zero.
     */
    public void reset() {
        startTime = 0;
        stopTime = 0;
        isRunning = false;
    }

    /**
     * Returns the formatted elapsed time as a string in the format of HH:MM:SS.
     * If the stopwatch is running, it calculates the time from the startTime to the current time.
     * If it is not running, it returns the total elapsed time before it was last stopped.
     *
     * @return The formatted elapsed time as a string.
     */
    public String getelapsedTime() {
        long elaspedTime = isRunning ? System.currentTimeMillis() - startTime : totalTime;
        long hours = (elaspedTime / 3600000) % 24;
        long minutes = (elaspedTime / 60000) % 60;
        long seconds = (elaspedTime / 1000) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
