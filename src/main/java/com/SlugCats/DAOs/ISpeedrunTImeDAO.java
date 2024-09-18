package com.SlugCats.DAOs;

import com.SlugCats.Models.SpeedrunTime;

import java.util.List;

public interface ISpeedrunTImeDAO {
    /**
     * Creates the SpeedrunTime table in the database if it doesn't already exist.
     */
    public void CreateSpeedrunTimeTable();

    /**
     * Adds a new SpeedrunTime to the database.
     * @param speedrunTime SpeedrunTime to be added.
     */
    public void AddSpeedrunTime(SpeedrunTime speedrunTime);

    /**
     * Gets the SpeedrunTime with the matching ID.
     * @param id The ID of the desired SpeedrunTime.
     * @return A SpeedrunTime with a matching ID.
     */
    public SpeedrunTime GetSpeedrunTime(int id);

    /**
     * Gets all SpeedrunTimes in the database.
     * @return A list of all SpeedrunTimes.
     */
    public List<SpeedrunTime> GetSpeedrunTimeList();

    /**
     * Gets all SpeedrunTimes in the database with the matching UserId.
     * @param id The ID of the User.
     * @return A list of all SpeedrunTimes with the matching UserId.
     */
    public List<SpeedrunTime> GetSpeedrunTimeListByUser(int id);

    /**
     * Gets all SpeedrunTimes in the database with the matching GameId.
     * @param id The ID of the Game.
     * @return A list of all SpeedrunTimes with the matching GameId.
     */
    public List<SpeedrunTime> GetSpeedrunTimeListByGame(int id);

    /**
     * Updates an existing SpeedrunTime in the database.
     * @param speedrunTime The SpeedrunTime to update.
     */
    public void UpdateSpeedrunTime(SpeedrunTime speedrunTime);

    /**
     * Deletes the SpeedrunTime with the matching ID.
     * @param id The ID of the SpeedrunTime to be deleted.
     */
    public void DeleteSpeedrunTime(int id);

    /**
     * Closes the connection to the database.
     */
    public void close();
}
