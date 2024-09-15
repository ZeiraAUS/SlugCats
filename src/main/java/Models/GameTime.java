package Models;

public class GameTime {
    private int GameTimeId;
    private int UserId;
    private int GameId;
    private long TotalPlaytime;
    private long LastSessionPlaytime;

    public GameTime(int gameTimeId, int userId, int gameId, long totalPlaytime, long lastSessionPlaytime) {
        GameTimeId = gameTimeId;
        UserId = userId;
        GameId = gameId;
        TotalPlaytime = totalPlaytime;
        LastSessionPlaytime = lastSessionPlaytime;
    }

    public GameTime(int userId, int gameId, long totalPlaytime, long lastSessionPlaytime) {
        UserId = userId;
        GameId = gameId;
        TotalPlaytime = totalPlaytime;
        LastSessionPlaytime = lastSessionPlaytime;
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

    @Override
    public String toString() {
        return "GameTime{" +
                "TimerID=" + GameTimeId +
                ", UserID=" + UserId +
                ", GameID=" + GameId +
                ", TotalPlaytime=" + TotalPlaytime +
                ", LastSessionPlaytime=" + LastSessionPlaytime +
                '}';
    }
}
