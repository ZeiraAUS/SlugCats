package Models;

public class SpeedrunTime {
    private int SpeedrunTimeId;
    private int UserId;
    private int GameId;
    private long BestTime;
    private long LastRunTime;

    public SpeedrunTime(int speedrunTimeId, int userId, int gameId, long bestTime, long lastRunTime) {
        SpeedrunTimeId = speedrunTimeId;
        UserId = userId;
        GameId = gameId;
        BestTime = bestTime;
        LastRunTime = lastRunTime;
    }

    public SpeedrunTime(int userId, int gameId, long bestTime, long lastRunTime) {
        UserId = userId;
        GameId = gameId;
        BestTime = bestTime;
        LastRunTime = lastRunTime;
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

    @Override
    public String toString() {
        return "SpeedrunTime{" +
                "SpeedrunTimeID=" + SpeedrunTimeId +
                ", UserID=" + UserId +
                ", GameID=" + GameId +
                ", BestTime=" + BestTime +
                ", LastRunTime=" + LastRunTime +
                '}';
    }
}
