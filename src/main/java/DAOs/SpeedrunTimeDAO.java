package DAOs;

import Models.GameTime;
import Models.SpeedrunTime;
import com.SlugCats.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpeedrunTimeDAO implements ISpeedrunTImeDAO{
    private Connection connection;

    public SpeedrunTimeDAO()
    {
        connection = DatabaseConnection.getInstance();
    }

    @Override
    public void CreateSpeedrunTimeTable() {
        try
        {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS SpeedrunTimes (" +
                            "SpeedrunTimeId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "UserId INTEGER NOT NULL, " +
                            "GameId INTEGER NOT NULL, " +
                            "BestTime INTEGER NOT NULL, " +
                            "LastRunTime INTEGER NOT NULL, " +
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
    public void AddSpeedrunTime(SpeedrunTime speedrunTime) {
        try
        {
            PreparedStatement insertSpeedrunTime = connection.prepareStatement(
                    "INSERT INTO SpeedrunTimes (UserId, GameId, BestTime, LastRunTime) " +
                            "VALUES (?, ?, ?, ?)"
            );

            insertSpeedrunTime.setInt(1, speedrunTime.getUserId());
            insertSpeedrunTime.setInt(2, speedrunTime.getGameId());
            insertSpeedrunTime.setLong(3, speedrunTime.getBestTime());
            insertSpeedrunTime.setLong(4, speedrunTime.getLastRunTime());
            insertSpeedrunTime.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public SpeedrunTime GetSpeedrunTime(int id) {
        try
        {
            PreparedStatement getSpeedrunTime = connection.prepareStatement("SELECT * FROM SpeedrunTime " +
                    "WHERE SpeedrunTimeId = ?");
            getSpeedrunTime.setInt(1, id);
            ResultSet rs = getSpeedrunTime.executeQuery();
            if (rs.next())
            {
                return new SpeedrunTime(
                        rs.getInt("SpeedrunTimeId"),
                        rs.getInt("UserId"),
                        rs.getInt("GameId"),
                        rs.getLong("BestTime"),
                        rs.getLong("LastRunTime")
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
    public List<SpeedrunTime> GetSpeedrunTimeList() {
        List<SpeedrunTime> speedrunTimeList = new ArrayList<>();
        try
        {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM SpeedrunTimes");
            while (rs.next())
            {
                speedrunTimeList.add(
                        new SpeedrunTime(
                                rs.getInt("SpeedrunTimeId"),
                                rs.getInt("UserId"),
                                rs.getInt("GameId"),
                                rs.getLong("BestTime"),
                                rs.getLong("LastRunTime")
                        )
                );
            }
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
        return speedrunTimeList;
    }

    @Override
    public void UpdateSpeedrunTime(SpeedrunTime speedrunTime) {
        try
        {
            PreparedStatement updateSpeedrunTime = connection.prepareStatement(
                    "UPDATE SpeedrunTime SET UserId = ?, GameId = ?, BestTime = ?, " +
                            "LastRunTime = ? WHERE SpeedrunTimeId = ?"
            );
            updateSpeedrunTime.setInt(1, speedrunTime.getUserId());
            updateSpeedrunTime.setInt(2, speedrunTime.getGameId());
            updateSpeedrunTime.setLong(3, speedrunTime.getBestTime());
            updateSpeedrunTime.setLong(4, speedrunTime.getLastRunTime());
            updateSpeedrunTime.setInt(5, speedrunTime.getSpeedrunTimeId());
            updateSpeedrunTime.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void DeleteSpeedrunTime(int id) {
        try
        {
            PreparedStatement deleteSpeedrunTime = connection.prepareStatement("DELETE FROM SpeedrunTimes " +
                    "WHERE SpeedrunTimeId = ?");
            deleteSpeedrunTime.setInt(1, id);
            deleteSpeedrunTime.execute();
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
