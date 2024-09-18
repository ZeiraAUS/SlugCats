package com.SlugCats.DAOs;

import com.SlugCats.Models.Game;
import com.SlugCats.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO implements IGameDAO {
    private Connection connection;

    public GameDAO()
    {
        connection = DatabaseConnection.getInstance();
    }

    @Override
    public void CreateGameTable() {
        try
        {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS Games (" +
                            "GameId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "GameName VARCHAR NOT NULL, " +
                            "GameProcess INTEGER NOT NULL" +
                            ")"
            );
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void AddGame(Game game) {
        try
        {
            PreparedStatement insertGame = connection.prepareStatement(
                    "INSERT INTO Games (GameName, GameProcess) VALUES (?, ?)"
            );

            insertGame.setString(1, game.getGameName());
            insertGame.setInt(2, game.getGameProcess());
            insertGame.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public Game GetGame(int id) {
        try
        {
            PreparedStatement getGame = connection.prepareStatement("SELECT * FROM Games WHERE GameId = ?");
            getGame.setInt(1, id);
            ResultSet rs = getGame.executeQuery();
            if (rs.next())
            {
                return new Game(
                        rs.getInt("GameId"),
                        rs.getString("GameName"),
                        rs.getInt("GameProcess")
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
    public List<Game> GetGameList() {
        List<Game> gameList = new ArrayList<>();
        try
        {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM Games");
            while (rs.next())
            {
                gameList.add(
                        new Game(
                                rs.getInt("GameId"),
                                rs.getString("GameName"),
                                rs.getInt("GameProcess")
                        )
                );
            }
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
        return gameList;
    }

    @Override
    public void UpdateGame(Game game) {
        try
        {
            PreparedStatement updateGame = connection.prepareStatement(
                    "UPDATE Games SET GameName = ?, GameProcess = ? WHERE GameId = ?"
            );
            updateGame.setString(1, game.getGameName());
            updateGame.setInt(2, game.getGameProcess());
            updateGame.setInt(3, game.getGameId());
            updateGame.execute();
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void DeleteGame(int id) {
        try
        {
            PreparedStatement deleteGame = connection.prepareStatement("DELETE FROM Games WHERE GameId = ?");
            deleteGame.setInt(1, id);
            deleteGame.execute();
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
