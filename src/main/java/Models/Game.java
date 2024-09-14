package Models;

public class Game {
    private int GameID;
    private String GameName;
    private int GameProcess;

    public Game(String gameName, int gameProcess) {
        GameName = gameName;
        GameProcess = gameProcess;
    }

    public int getGameID() {
        return GameID;
    }
    public void setGameID(int gameID) {
        GameID = gameID;
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
                "GameID=" + GameID +
                ", GameName='" + GameName + '\'' +
                ", GameProcess=" + GameProcess +
                '}';
    }
}
