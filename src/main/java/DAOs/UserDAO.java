package DAOs;

import Models.User;
import com.SlugCats.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    private Connection connection;

    public UserDAO()
    {
        connection = DatabaseConnection.getInstance();
    }

    @Override
    public void CreateUserTable() {
        try
        {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS Users (" +
                            "UserId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "UserName VARCHAR NOT NULL, " +
                            "Password VARCHAR NOT NULL, " +
                            "FirstName VARCHAR NOT NULL, " +
                            "LastName VARCHAR NOT NULL, " +
                            "Email VARCHAR NOT NULL" +
                            ")"
            );
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void AddUser(User user) {
        try
        {
            PreparedStatement insertUser = connection.prepareStatement(
                    "INSERT INTO Users (UserName, Password, FirstName, LastName, Email) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );

            insertUser.setString(1, user.getUserName());
            insertUser.setString(2, user.getPassword());
            insertUser.setString(3, user.getFirstName());
            insertUser.setString(4, user.getLastName());
            insertUser.setString(5, user.getEmail());
            insertUser.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public User GetUser(int id) {
        try
        {
            PreparedStatement getUser = connection.prepareStatement("SELECT * FROM Users WHERE UserId = ?");
            getUser.setInt(1, id);
            ResultSet rs = getUser.executeQuery();
            if (rs.next())
            {
                return new User(
                        rs.getInt("UserId"),
                        rs.getString("UserName"),
                        rs.getString("Password"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email")
                );
            }
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
        return null;
    }

    @Override
    public List<User> GetUserList() {
        List<User> userList = new ArrayList<>();
        try
        {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM Users");
            while (rs.next())
            {
                userList.add(
                        new User(
                                rs.getInt("UserId"),
                                rs.getString("UserName"),
                                rs.getString("Password"),
                                rs.getString("FirstName"),
                                rs.getString("LastName"),
                                rs.getString("Email")
                        )
                );
            }
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
        return userList;
    }

    @Override
    public void UpdateUser(User user) {
        try
        {
            PreparedStatement updateUser = connection.prepareStatement(
                    "UPDATE Users SET UserName = ?, Password = ?, FirstName = ?, " +
                            "LastName = ?, Email = ? WHERE UserId = ?"
            );
            updateUser.setString(1, user.getUserName());
            updateUser.setString(2, user.getPassword());
            updateUser.setString(3, user.getFirstName());
            updateUser.setString(4, user.getLastName());
            updateUser.setString(5, user.getEmail());
            updateUser.setInt(6, user.getUserId());
            updateUser.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void DeleteUser(int id) {
        try
        {
            PreparedStatement deleteUser = connection.prepareStatement("DELETE FROM Users WHERE UserId = ?");
            deleteUser.setInt(1, id);
            deleteUser.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void close() {
        try
        {
            connection.close();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }
}
