package DAOs;

import Models.Game;

import java.util.List;

public interface IGameDAO {
    /**
     * Adds a new Game to the database.
     * @param game Game to be added.
     */
    public void AddGame(Game game);

    /**
     * Gets the Game with the matching ID.
     * @param id The ID of the desired Game.
     * @return A Game with a matching ID.
     */
    public Game GetGame(int id);

    /**
     * Gets all Games in the database.
     * @return A list of all Games.
     */
    public List<Game> GetGameList();

    /**
     * Updates an existing Game in the database.
     * @param game The Game to update.
     */
    public void UpdateGame(Game game);

    /**
     * Deletes the Game with the matching ID.
     * @param id The ID of the Game to be deleted.
     */
    public void DeleteGame(int id);
}
