package Models;

public class GameTime {
    private int TimerID;
    private int UserID;
    private int GameID;
    private long TotalPlaytime;
    private long LastSessionPlaytime;

    public GameTime(int userID, int gameID, long totalPlaytime, long lastSessionPlaytime) {
        UserID = userID;
        GameID = gameID;
        TotalPlaytime = totalPlaytime;
        LastSessionPlaytime = lastSessionPlaytime;
    }

    public int getTimerID() {
        return TimerID;
    }
    public void setTimerID(int timerID) {
        TimerID = timerID;
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

    @Override
    public String toString() {
        return "GameTime{" +
                "TimerID=" + TimerID +
                ", UserID=" + UserID +
                ", GameID=" + GameID +
                ", TotalPlaytime=" + TotalPlaytime +
                ", LastSessionPlaytime=" + LastSessionPlaytime +
                '}';
    }
}
