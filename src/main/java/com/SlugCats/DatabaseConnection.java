package com.SlugCats;

import com.SlugCats.DAOs.GameDAO;
import com.SlugCats.DAOs.GameTimeDAO;
import com.SlugCats.DAOs.SpeedrunTimeDAO;
import com.SlugCats.DAOs.UserDAO;
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection
{
    private static Connection instance = null;

    private DatabaseConnection()
    {
        String url ="jdbc:sqlite:SlugCatsDatabase.db";
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
        SpeedrunTimeDAO speedrunTimeDAO = new SpeedrunTimeDAO();

        userDAO.CreateUserTable();
        gameDAO.CreateGameTable();
        gameTimeDAO.CreateGameTimeTable();
        speedrunTimeDAO.CreateSpeedrunTimeTable();
    }

    public static void DropTables()
    {
        Connection connection = getInstance();

        try {
            Statement dropSpeedrun = connection.createStatement();
            dropSpeedrun.execute("DROP TABLE IF EXISTS SpeedrunTimes");

            Statement dropTimes = connection.createStatement();
            dropTimes.execute("DROP TABLE IF EXISTS GameTimes");

            Statement dropGames = connection.createStatement();
            dropGames.execute("DROP TABLE IF EXISTS Games");

            Statement dropUsers = connection.createStatement();
            dropUsers.execute("DROP TABLE IF EXISTS Users");
        }
        catch (SQLException sqlEx)
        {
            System.err.println(sqlEx);
        }
    }

    public static void StartupMessage()
    {
        System.out.println("            (\\. -- ./)");
        System.out.println("        O-0)))--|     \\");
        System.out.println("          |____________|");
        System.out.println("           -|--|--|--|-");
        System.out.println("           _T~_T~_T~_T_");
        System.out.println("          |____________|");
        System.out.println("          |_o_|____|_o_|");
        System.out.println("       .-~/  :  |   %  \\");
        System.out.println(".-..-~   /  :   |  %:   \\");
        System.out.println("`-'     /   :   | %  :   \\");
        System.out.println("       /   :    |#   :    \\");
        System.out.println("      /    :    |     :    \\");
        System.out.println("     /    :     |     :     \\");
        System.out.println(" . -/     :     |      :     \\- .");
        System.out.println("|\\  ~-.  :      |      :   .-~  /|");
        System.out.println("\\ ~-.   ~ - .  _|_  . - ~   .-~ /");
        System.out.println("  ~-.  ~ -  . _ _ _ .  - ~  .-~");
        System.out.println("       ~ -  . _ _ _ .  - ~");
    }
}
