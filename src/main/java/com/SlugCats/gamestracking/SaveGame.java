package com.SlugCats.gamestracking;

import com.SlugCats.DAOs.GameDAO;
import com.SlugCats.Models.Game;

public class SaveGame {
    private GameDAO gameDAO;

    public SaveGame() {
        this.gameDAO = new GameDAO();
    }

    public boolean saveGame(String gameName, String gameProcess) {
        if (gameName == null || gameName.isEmpty()) {
            System.out.println("Game name cannot be empty.");
            return false;
        }

        if (gameProcess == null || gameProcess.isEmpty()) {
            System.out.println("Game process cannot be empty.");
            return false;
        }

        Game existingGame = getExistingGame(gameName);

        if (existingGame != null) {
            System.out.println("Game already exists: " + existingGame.getGameName());
            return false;
        }

        Game newGame = new Game(gameName, gameProcess);

        try {
            gameDAO.AddGame(newGame);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Game getExistingGame(String gameName) {
        for (Game game : gameDAO.GetGameList()) {
            if (game.getGameName().equalsIgnoreCase(gameName)) {
                return game; // Return the existing game
            }
        }
        return null; // Game does not exist
    }
}

