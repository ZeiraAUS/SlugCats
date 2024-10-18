package com.SlugCats.timetracking;

import com.SlugCats.DAOs.GameTimeDAO;
import com.SlugCats.Models.GameTime;
import java.time.LocalDateTime;
import java.util.List;

public class GameTimeManager {
    private GameTimeDAO gameTimeDAO;

    public GameTimeManager() {
        this.gameTimeDAO = new GameTimeDAO();
    }

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
