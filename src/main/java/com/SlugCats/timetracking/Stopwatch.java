package com.SlugCats.timetracking;

public class Stopwatch {
    private long startTime;
    private long stopTime;
    private long totalTime;
    private boolean isRunning;

    public Stopwatch() {
        this.isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - totalTime;;
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            stopTime = System.currentTimeMillis();
            totalTime = stopTime - startTime;
            isRunning = false;
        }
    }

    public void reset() {
        startTime = 0;
        stopTime = 0;
        isRunning = false;
    }

    public String getelapsedTime() {
        long elaspedTime = isRunning ? System.currentTimeMillis() - startTime : totalTime;
        long hours = (elaspedTime / 3600000) % 24;
        long minutes = (elaspedTime / 60000) % 60;
        long seconds = (elaspedTime / 1000) % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}
