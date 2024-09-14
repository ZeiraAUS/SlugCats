package com.SlugCats;

import DAOs.UserDAO;

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
            instance = DriverManager.getConnection(url);
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

        userDAO.CreateUserTable();

        userDAO.close();
    }
}
