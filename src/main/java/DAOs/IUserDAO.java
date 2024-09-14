package DAOs;

import Models.User;

import java.util.List;

/**
 * Data Access Object that handles CRUD operations
 * between the User class and the database.
 */
public interface IUserDAO {
    /**
     * Creates the User table in the database if it doesn't already exist.
     */
    public void CreateUserTable();

    /**
     * Adds a new User to the database.
     * @param user The User to be added.
     */
    public void AddUser(User user);

    /**
     * Gets the User with the matching ID.
     * @param id The ID of the desired User.
     * @return The User with a matching ID, or null if not found.
     */
    public User GetUser(int id);

    /**
     * Gets all the Users in the database.
     * @return A list of all the Users.
     */
    public List<User> GetUserList();

    /**
     * Updates an existing User in the database.
     * @param user The User to update.
     */
    public void UpdateUser(User user);

    /**
     * Deletes the User with the matching ID.
     * @param id The ID of the User to be deleted./
     */
    public void DeleteUser(int id);

    /**
     * Closes the connection to the database.
     */
    public void close();
}
