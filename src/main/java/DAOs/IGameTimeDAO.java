package DAOs;

import Models.GameTime;

import java.util.List;

public interface IGameTimeDAO {
    /**
     * Creates the GameTime table in the database if it doesn't already exist.
     */
    public void CreateGameTimeTable();

    /**
     * Adds a new GameTime to the database.
     * @param gameTime GameTime to be added.
     */
    public void AddGameTime(GameTime gameTime);

    /**
     * Gets the GameTime with the matching ID.
     *
     * @param id The ID of the desired GameTime.
     * @return A GameTime with a matching ID.
     */
    public GameTime GetGameTime(int id);

    /**
     * Gets all GameTImes in the database.
     * @return A list of all GameTimes.
     */
    public List<GameTime> GetGameTimeList();

    /**
     * Updates an existing GameTime in the database.
     * @param gameTime The GameTime to update.
     */
    public void UpdateGameTime(GameTime gameTime);

    /**
     * Deletes the GameTime with the matching ID.
     * @param id The ID of the GameTime to be deleted.
     */
    public void DeleteGameTime(int id);

    /**
     * Closes the connection to the database.
     */
    public void close();
}
