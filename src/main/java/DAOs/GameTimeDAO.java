package DAOs;

import Models.Game;
import Models.GameTime;
import Models.User;
import com.SlugCats.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameTimeDAO implements IGameTimeDAO{
    private Connection connection;

    public GameTimeDAO()
    {
        connection = DatabaseConnection.getInstance();
    }

    @Override
    public void CreateGameTimeTable() {
        try
        {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS GameTimes (" +
                            "GameTimeId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "UserId INTEGER NOT NULL, " +
                            "GameId INTEGER NOT NULL, " +
                            "TotalPlaytime INTEGER NOT NULL, " +
                            "LastSessionPlaytime INTEGER NOT NULL, " +
                            "FOREIGN KEY (UserId) REFERENCES Users(UserId), " +
                            "FOREIGN KEY (GameId) REFERENCES Games(GameId)" +
                            ")"
            );
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void AddGameTime(GameTime gameTime) {
        try
        {
            PreparedStatement insertGameTime = connection.prepareStatement(
                    "INSERT INTO GameTimes (UserId, GameId, TotalPlaytime, LastSessionPlaytime) " +
                            "VALUES (?, ?, ?, ?)"
            );

            insertGameTime.setInt(1, gameTime.getUserId());
            insertGameTime.setInt(2, gameTime.getGameId());
            insertGameTime.setLong(3, gameTime.getTotalPlaytime());
            insertGameTime.setLong(4, gameTime.getLastSessionPlaytime());
            insertGameTime.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public GameTime GetGameTime(int id) {
        try
        {
            PreparedStatement getGameTime = connection.prepareStatement("SELECT * FROM GameTimes " +
                    "WHERE GameTimeId = ?");
            getGameTime.setInt(1, id);
            ResultSet rs = getGameTime.executeQuery();
            if (rs.next())
            {
                return new GameTime(
                        rs.getInt("GameTimeId"),
                        rs.getInt("UserId"),
                        rs.getInt("GameId"),
                        rs.getLong("TotalPlaytime"),
                        rs.getLong("LastSessionPlaytime")
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
    public List<GameTime> GetGameTimeList() {
        List<GameTime> gameTimeList = new ArrayList<>();
        try
        {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM GameTimes");
            while (rs.next())
            {
                gameTimeList.add(
                        new GameTime(
                                rs.getInt("GameTimeId"),
                                rs.getInt("UserId"),
                                rs.getInt("GameId"),
                                rs.getLong("TotalPlaytime"),
                                rs.getLong("LastSessionPlaytime")
                        )
                );
            }
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
        return gameTimeList;
    }

    @Override
    public List<GameTime> GetGameTimeListByUser(int id) {
        List<GameTime> gameTimeList = new ArrayList<>();
        try
        {
            PreparedStatement getAllByUser = connection.prepareStatement("SELECT * FROM GameTimes " +
                    "WHERE UserId = ?");
            getAllByUser.setInt(1, id);
            ResultSet rs = getAllByUser.executeQuery();
            while (rs.next())
            {
                gameTimeList.add(
                        new GameTime(
                                rs.getInt("GameTimeId"),
                                rs.getInt("UserId"),
                                rs.getInt("GameId"),
                                rs.getLong("TotalPlaytime"),
                                rs.getLong("LastSessionPlaytime")
                        )
                );
            }
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
        return gameTimeList;
    }

    @Override
    public void UpdateGameTime(GameTime gameTime) {
        try
        {
            PreparedStatement updateGameTime = connection.prepareStatement(
                    "UPDATE GameTimes SET UserId = ?, GameId = ?, TotalPlaytime = ?, " +
                            "LastSessionPlaytime = ? WHERE GameTimeId = ?"
            );
            updateGameTime.setInt(1, gameTime.getUserId());
            updateGameTime.setInt(2, gameTime.getGameId());
            updateGameTime.setLong(3, gameTime.getTotalPlaytime());
            updateGameTime.setLong(4, gameTime.getLastSessionPlaytime());
            updateGameTime.setInt(5, gameTime.getGameTimeId());
            updateGameTime.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void DeleteGameTime(int id) {
        try
        {
            PreparedStatement deleteGameTime = connection.prepareStatement("DELETE FROM GameTime " +
                    "WHERE GameTimeId = ?");
            deleteGameTime.setInt(1, id);
            deleteGameTime.execute();
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
