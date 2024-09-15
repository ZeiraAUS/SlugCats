package Models;

public class Game {
    private int GameId;
    private String GameName;
    private int GameProcess;

    public Game(int gameId, String gameName, int gameProcess) {
        GameId = gameId;
        GameName = gameName;
        GameProcess = gameProcess;
    }

    public Game(String gameName, int gameProcess) {
        GameName = gameName;
        GameProcess = gameProcess;
    }

    public int getGameId() {
        return GameId;
    }
    public void setGameId(int gameId) {
        GameId = gameId;
    }

    public String getGameName() {
        return GameName;
    }
    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public int getGameProcess() {
        return GameProcess;
    }
    public void setGameProcess(int gameProcess) {
        GameProcess = gameProcess;
    }

    @Override
    public String toString() {
        return "Game{" +
                "GameID=" + GameId +
                ", GameName='" + GameName + '\'' +
                ", GameProcess=" + GameProcess +
                '}';
    }
}
