package com.SlugCats.timetracking;

public class playtimemonitoring {
    private static playtimetracker tracker;
    private static Thread trackerThread;
    public static long trackedPlayTime;

    /**
     * Private constructor to prevent instantiation.
     */
    private playtimemonitoring() {}

    /**
     * Starts the playtime tracking process for the specified game executable.
     * This method initializes a playtimetracker instance and runs it in a separate thread.
     *
     * @param gameExecutable The name of the game executable to track playtime for.
     */
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

    /**
     * Stops the playtime tracking process if it is currently running.
     * This method interrupts the tracker thread and sets the tracker instance to null.
     */
    public static void stopTracking() {
        if (trackerThread != null && trackerThread.isAlive()) {
            trackerThread.interrupt();
        }
        tracker = null;
    }

    /**
     * Checks if playtime tracking is currently in progress.
     *
     * @return true if the tracker thread is alive and tracking is active, false otherwise.
     */
    public static boolean isTracking() {
        return trackerThread != null && trackerThread.isAlive();
    }

    /**
     * Retrieves the total tracked playtime in seconds.
     *
     * @return The total playtime tracked by the playtimetracker.
     */
    public static long getTrackedPlayTime() {
        return trackedPlayTime;
    }

}
