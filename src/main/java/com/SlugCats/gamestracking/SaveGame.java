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

        Game newGame = new Game(gameName, gameProcess);

        try {
            gameDAO.AddGame(newGame);
            System.out.println("Game saved successfully.");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to save game: " + e.getMessage());
            return false;
        }
    }
}
