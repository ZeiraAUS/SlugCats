package com.SlugCats.timetracking;

public class playtimemonitoring {
    private static playtimetracker tracker;
    private static Thread trackerThread;

    private playtimemonitoring() {}

    public static void startTracking(String gameExecutable) {
        if (tracker == null) {
            tracker = new playtimetracker(gameExecutable);
            trackerThread = new Thread(() -> {
                tracker.trackPlayTime();
            });
            trackerThread.setDaemon(true);
            trackerThread.start();
        }
    }

    public static void stopTracking() {
        if (trackerThread != null && trackerThread.isAlive()) {
            trackerThread.interrupt();
            tracker = null;
        }
    }

    public static boolean isTracking() {
        return trackerThread != null && trackerThread.isAlive();
    }
}
