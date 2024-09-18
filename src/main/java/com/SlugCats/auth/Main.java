package com.SlugCats.auth;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getInstance();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(id INTEGER PRIMARY KEY, username VARCHAR," +
                    "email VARCHAR," +
                    "password VARCHAR)");
            connection.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}