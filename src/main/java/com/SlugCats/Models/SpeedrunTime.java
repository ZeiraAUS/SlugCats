package com.SlugCats.Models;

import java.time.LocalDateTime;

public class SpeedrunTime {
    private int SpeedrunTimeId;
    private int UserId;
    private int GameId;
    private long BestTime;
    private long LastRunTime;
    private LocalDateTime CreatedDateTime;

    /**
     * Constructor for the SpeedrunTime object.
     * @param speedrunTimeId The ID of the SpeedrunTime object.
     * @param userId The ID of the related User object.
     * @param gameId The ID of the related Game object.
     * @param bestTime The best speedrun time.
     * @param lastRunTime the speedrun time of the last run.
     */
    public SpeedrunTime(int speedrunTimeId, int userId, int gameId, long bestTime, long lastRunTime) {
        SpeedrunTimeId = speedrunTimeId;
        UserId = userId;
        GameId = gameId;
        BestTime = bestTime;
        LastRunTime = lastRunTime;
        CreatedDateTime = LocalDateTime.now();
    }

    /**
     * Constructor for the SpeedrunTime object.
     * @param speedrunTimeId The ID of the SpeedrunTime object.
     * @param userId The ID of the related User object.
     * @param gameId The ID of the related Game object.
     * @param bestTime The best speedrun time.
     * @param lastRunTime the speedrun time of the last run.
     * @param createdDateTime The DateTime that the SpeedrunTime was created at.
     */
    public SpeedrunTime(int speedrunTimeId, int userId, int gameId, long bestTime, long lastRunTime, LocalDateTime createdDateTime) {
        SpeedrunTimeId = speedrunTimeId;
        UserId = userId;
        GameId = gameId;
        BestTime = bestTime;
        LastRunTime = lastRunTime;
        CreatedDateTime = createdDateTime;
    }

    /**
     * Constructor for the SpeedrunTime object.
     * @param userId The ID of the related User object.
     * @param gameId The ID of the related Game object.
     * @param bestTime The best speedrun time.
     * @param lastRunTime the speedrun time of the last run.
     */
    public SpeedrunTime(int userId, int gameId, long bestTime, long lastRunTime) {
        UserId = userId;
        GameId = gameId;
        BestTime = bestTime;
        LastRunTime = lastRunTime;
        CreatedDateTime = LocalDateTime.now();
    }

    public int getSpeedrunTimeId() {
        return SpeedrunTimeId;
    }
    public void setSpeedrunTimeId(int speedrunTimeId) {
        SpeedrunTimeId = speedrunTimeId;
    }

    public int getUserId() {
        return UserId;
    }
    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getGameId() {
        return GameId;
    }
    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public long getBestTime() {
        return BestTime;
    }
    public void setBestTime(long bestTime) {
        BestTime = bestTime;
    }

    public long getLastRunTime() {
        return LastRunTime;
    }
    public void setLastRunTime(long lastRunTime) {
        LastRunTime = lastRunTime;
    }

    public LocalDateTime getCreatedDateTime() {
        return CreatedDateTime;
    }
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        CreatedDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "SpeedrunTime{" +
                "SpeedrunTimeId=" + SpeedrunTimeId +
                ", UserId=" + UserId +
                ", GameId=" + GameId +
                ", BestTime=" + BestTime +
                ", LastRunTime=" + LastRunTime +
                ", CreatedDateTime=" + CreatedDateTime +
                '}';
    }
}
