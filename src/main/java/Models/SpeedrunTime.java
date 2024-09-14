package Models;

public class SpeedrunTime {
    private int SpeedrunTimeID;
    private int UserID;
    private int GameID;
    private long BestTime;
    private long LastRunTime;

    public SpeedrunTime(int userID, int gameID, long bestTime, long lastRunTime) {
        UserID = userID;
        GameID = gameID;
        BestTime = bestTime;
        LastRunTime = lastRunTime;
    }

    public int getSpeedrunTimeID() {
        return SpeedrunTimeID;
    }
    public void setSpeedrunTimeID(int speedrunTimeID) {
        SpeedrunTimeID = speedrunTimeID;
    }

    public int getUserID() {
        return UserID;
    }
    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getGameID() {
        return GameID;
    }
    public void setGameID(int gameID) {
        GameID = gameID;
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
                "SpeedrunTimeID=" + SpeedrunTimeID +
                ", UserID=" + UserID +
                ", GameID=" + GameID +
                ", BestTime=" + BestTime +
                ", LastRunTime=" + LastRunTime +
                '}';
    }
}
