package DAOs;

import Models.SpeedrunTime;

import java.util.List;

public interface ISpeedrunTImeDAO {
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
     * Updates an existing SpeedrunTime in the database.
     * @param speedrunTime The SpeedrunTime to update.
     */
    public void UpdateSpeedrunTime(SpeedrunTime speedrunTime);

    /**
     * Deletes the SpeedrunTime with the matching ID.
     * @param id The ID of the SpeedrunTime to be deleted.
     */
    public void DeleteSpeedrunTime(int id);
}
