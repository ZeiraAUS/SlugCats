package com.SlugCats.Models;

import java.time.LocalDateTime;

public class GameTime {
    private int GameTimeId;
    private int UserId;
    private int GameId;
    private long TotalPlaytime;
    private long LastSessionPlaytime;
    private LocalDateTime CreatedDateTime;

    /**
     * Constructor for the GameTime object.
     * @param gameTimeId The ID of the GameTime object.
     * @param userId The ID of the related User object.
     * @param gameId The ID of the related Game object.
     * @param totalPlaytime The total playtime.
     * @param lastSessionPlaytime The playtime of the last session.
     */
    public GameTime(int gameTimeId, int userId, int gameId, long totalPlaytime, long lastSessionPlaytime) {
        GameTimeId = gameTimeId;
        UserId = userId;
        GameId = gameId;
        TotalPlaytime = totalPlaytime;
        LastSessionPlaytime = lastSessionPlaytime;
        CreatedDateTime = LocalDateTime.now();
    }

    /**
     * Constructor for the GameTime object.
     * @param gameTimeId The ID of the GameTime object.
     * @param userId The ID of the related User object.
     * @param gameId The ID of the related Game object.
     * @param totalPlaytime The total playtime.
     * @param lastSessionPlaytime The playtime of the last session.
     * @param createdDateTime The DateTime that the GameTime was created at.
     */
    public GameTime(int gameTimeId, int userId, int gameId, long totalPlaytime, long lastSessionPlaytime, LocalDateTime createdDateTime) {
        GameTimeId = gameTimeId;
        UserId = userId;
        GameId = gameId;
        TotalPlaytime = totalPlaytime;
        LastSessionPlaytime = lastSessionPlaytime;
        CreatedDateTime = createdDateTime;
    }

    /**
     * Constructor for the GameTime object.
     * @param userId The ID of the related User object.
     * @param gameId The ID of the related Game object.
     * @param totalPlaytime The total playtime.
     * @param lastSessionPlaytime The playtime of the last session.
     */
    public GameTime(int userId, int gameId, long totalPlaytime, long lastSessionPlaytime) {
        UserId = userId;
        GameId = gameId;
        TotalPlaytime = totalPlaytime;
        LastSessionPlaytime = lastSessionPlaytime;
        CreatedDateTime = LocalDateTime.now();
    }

    public int getGameTimeId() {
        return GameTimeId;
    }
    public void setGameTimeId(int gameTimeId) {
        GameTimeId = gameTimeId;
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

    public long getTotalPlaytime() {
        return TotalPlaytime;
    }
    public void setTotalPlaytime(long totalPlaytime) {
        TotalPlaytime = totalPlaytime;
    }

    public long getLastSessionPlaytime() {
        return LastSessionPlaytime;
    }
    public void setLastSessionPlaytime(long lastSessionPlaytime) {
        LastSessionPlaytime = lastSessionPlaytime;
    }

    public LocalDateTime getCreatedDateTime() {
        return CreatedDateTime;
    }
    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        CreatedDateTime = createdDateTime;
    }

    @Override
    public String toString() {
        return "GameTime{" +
                "GameTimeId=" + GameTimeId +
                ", UserId=" + UserId +
                ", GameId=" + GameId +
                ", TotalPlaytime=" + TotalPlaytime +
                ", LastSessionPlaytime=" + LastSessionPlaytime +
                ", CreatedDateTime=" + CreatedDateTime +
                '}';
    }
}
