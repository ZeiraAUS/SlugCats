package com.SlugCats;

import DAOs.GameDAO;
import DAOs.GameTimeDAO;
import DAOs.UserDAO;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection
{
    private static Connection instance = null;

    private DatabaseConnection()
    {
        String url = "jdbc:sqlite:SlugCatsDatabase.db";
        try
        {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            instance = DriverManager.getConnection(url, config.toProperties());
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    public static Connection getInstance() {
        if (instance == null)
        {
            new DatabaseConnection();
        }
        return instance;
    }

    public static void CreateTables()
    {
        UserDAO userDAO = new UserDAO();
        GameDAO gameDAO = new GameDAO();
        GameTimeDAO gameTimeDAO = new GameTimeDAO();

        userDAO.CreateUserTable();
        gameDAO.CreateGameTable();
        gameTimeDAO.CreateGameTimeTable();

        userDAO.close();
        gameDAO.close();
        gameTimeDAO.close();
    }
}
