package com.SlugCats.timetracking;

import com.SlugCats.DAOs.GameTimeDAO;
import com.SlugCats.Models.GameTime;
import java.time.LocalDateTime;
import java.util.List;

public class GameTimeManager {
    private GameTimeDAO gameTimeDAO;

    /**
     * Constructs a new GameTimeManager and initializes the GameTimeDAO for database interactions.
     */
    public GameTimeManager() {
        this.gameTimeDAO = new GameTimeDAO();
    }

    /**
     * Saves a new game time entry for a specific user and game if it does not already exist.
     *
     * @param userId The ID of the user.
     * @param gameId The ID of the game.
     */
    public void saveGameTime(int userId, int gameId) {
        List<GameTime> userGameTimes = gameTimeDAO.GetGameTimeListByUser(userId);

        boolean exists = userGameTimes.stream()
                .anyMatch(gameTime -> gameTime.getGameId() == gameId);

        if (!exists) {
            GameTime newGameTime = new GameTime(userId, gameId, 0, 0);
            gameTimeDAO.AddGameTime(newGameTime);
        } else {
            System.out.println("Game Time exist");
        }
    }

    /**
     * Updates the game time entry for a specific user and game with the total and session playtime.
     *
     * @param userId The ID of the user.
     * @param gameId The ID of the game.
     * @param totalPlaytime The total playtime for the game.
     * @param sessionPlaytime The playtime for the current session.
     */
    public void updateGameTime(int userId, int gameId, long totalPlaytime, long sessionPlaytime) {
        List<GameTime> userGameTimes = gameTimeDAO.GetGameTimeListByUser(userId);

        for (GameTime gameTime : userGameTimes) {
            if (gameTime.getGameId() == gameId) {

                gameTime.setTotalPlaytime(totalPlaytime);
                gameTime.setLastSessionPlaytime(sessionPlaytime);

                gameTimeDAO.UpdateGameTime(gameTime);

                System.out.println("Game time updated successfully.");
                return;
            }
        }
    }

    /**
     * Retrieves the game time entry for a specific user and game.
     *
     * @param userId The ID of the user.
     * @param gameId The ID of the game.
     * @return The GameTime object containing the playtime information, or null if not found.
     */
    public GameTime getGameTime(int userId, int gameId) {
        List<GameTime> gameTimes = gameTimeDAO.GetGameTimeListByUser(userId);

        for (GameTime gameTime : gameTimes) {
            if (gameTime.getGameId() == gameId) {
                return gameTime;
            }
        }
        return null;
    }
}
