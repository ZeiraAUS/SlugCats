package com.SlugCats.Models;

public class Game {
    private int GameId;
    private String GameName;
    private String GameProcess;

    /**
     * Constructor for the Game object.
     * @param gameId The ID of the Game object.
     * @param gameName The name of game.
     * @param gameProcess The process name of the game.
     */
    public Game(int gameId, String gameName, String gameProcess) {
        GameId = gameId;
        GameName = gameName;
        GameProcess = gameProcess;
    }

    /**
     * Constructor for the Game object.
     * @param gameName The name of game.
     * @param gameProcess The process name of the game.
     */
    public Game(String gameName, String gameProcess) {
        GameName = gameName;
        GameProcess = gameProcess;
    }

    public int getGameId() {
        return GameId;
    }

    public String getGameName() {
        return GameName;
    }
    public void setGameName(String gameName) {
        GameName = gameName;
    }

    public String getGameProcess() {
        return GameProcess;
    }
    public void setGameProcess(String gameProcess) {
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
