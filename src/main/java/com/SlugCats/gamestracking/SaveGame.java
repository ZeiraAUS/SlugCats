package com.SlugCats.gamestracking;

import com.SlugCats.DAOs.GameDAO;
import com.SlugCats.Models.Game;
import java.util.List;

public class SaveGame {
    private GameDAO gameDAO;

    public SaveGame() {
        this.gameDAO = new GameDAO();
    }

    public void saveGame(String gameName, String gameProcess) {
        List<Game> games = gameDAO.GetGameList();
        boolean gameExists = games.stream().anyMatch(game -> game.getGameName().equalsIgnoreCase(gameName));

        if (!gameExists) {
            Game newGame = new Game(gameName, gameProcess);
            gameDAO.AddGame(newGame);
        } else {
            System.out.println("Game already exists: " + gameName);
        }
    }

    private Game getExistingGame(String gameName) {
        for (Game game : gameDAO.GetGameList()) {
            if (game.getGameName().equalsIgnoreCase(gameName)) {
                return game;
            }
        }
        return null;
    }
}

