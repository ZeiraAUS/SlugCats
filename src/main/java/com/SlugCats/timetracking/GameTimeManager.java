package com.SlugCats.timetracking;

import com.SlugCats.DAOs.GameTimeDAO;
import com.SlugCats.Models.GameTime;
import java.util.List;

public class GameTimeManager {
    private GameTimeDAO gameTimeDAO;

    public GameTimeManager() {
        gameTimeDAO = new GameTimeDAO();
    }

    public void updateGameTimeByUserAndGame(int userId, int gameId, long sessionPlaytime) {
        List<GameTime> userGameTimes = gameTimeDAO.GetGameTimeListByUser(userId);

        GameTime gameTime = userGameTimes.stream()
                .filter(gt -> gt.getGameId() == gameId)
                .findFirst()
                .orElse(null);

        if (gameTime != null) {
            long newTotalPlaytime = gameTime.getTotalPlaytime() + sessionPlaytime;
            gameTime.setTotalPlaytime(newTotalPlaytime);
            gameTime.setLastSessionPlaytime(sessionPlaytime);
            gameTimeDAO.UpdateGameTime(gameTime);
            System.out.println("Game time updated for game ID: " + gameId);
        } else {
            System.out.println("No game time found for game ID: " + gameId);
        }
    }
}
