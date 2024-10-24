package com.SlugCats.gamestracking;

import com.SlugCats.DAOs.GameDAO;
import com.SlugCats.Models.Game;
import com.SlugCats.Models.User;

import java.util.List;

public class SaveGame {
    private GameDAO gameDAO;

    /**
     * Constructs a new SaveGame instance and initializes the GameDAO for database interactions.
     */
    public SaveGame() {
        this.gameDAO = new GameDAO();
    }

    /**
     * Saves a new game with the provided game name and game process to the database if it does not already exist.
     *
     * @param gameName The name of the game.
     * @param gameProcess The process name of the game.
     */
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

    /**
     * Retrieves an existing game from the database by its name.
     *
     * @param gameName The name of the game.
     * @return The Game object if found, or null if the game does not exist.
     */
    public Game getExistingGame(String gameName) {
        for (Game game : gameDAO.GetGameList()) {
            if (game.getGameName().equalsIgnoreCase(gameName)) {
                return game;
            }
        }

        return null;
    }

    /**
     * An alternative method for retrieving an existing game by its title.
     *
     * @param gameTitle The title of the game.
     * @return The Game object if found, or null if the game does not exist.
     */
    public Game gotGame(String gameTitle) {
        return getExistingGame(gameTitle);
    }
}

