package com.SlugCats.DAOs;

import com.SlugCats.Models.Login_session;
import com.SlugCats.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginSessionDAO implements ILoginSessionDAO {
    private Connection connection;

    public LoginSessionDAO() {
        connection = DatabaseConnection.getInstance();
    }

    @Override
    public void CreateLoginSessionTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS Login_sessions (" +
                            "SessionId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "UserName VARCHAR NOT NULL, " +
                            "Login BOOLEAN NOT NULL" +
                            ")"
            );
            createTable.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void AddLoginSession(Login_session session) {
        try {
            PreparedStatement insertSession = connection.prepareStatement(
                    "INSERT INTO Login_sessions (UserName, Login) VALUES (?, ?)"
            );
            insertSession.setString(1, session.getUserName());
            insertSession.setBoolean(2, session.isLogin());
            insertSession.execute();
            insertSession.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override
    public Login_session GetLoginSession(int id) {
        try {
            PreparedStatement getSession = connection.prepareStatement("SELECT * FROM Login_sessions WHERE SessionId = ?");
            getSession.setInt(1, id);
            ResultSet rs = getSession.executeQuery();
            if (rs.next()) {
                return new Login_session(
                        rs.getInt("SessionId"),
                        rs.getString("UserName"),
                        rs.getBoolean("Login")
                );
            }
            getSession.close();
            rs.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
        return null;
    }

    @Override
    public List<Login_session> GetLoginSessionList() {
        List<Login_session> sessionList = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM Login_sessions");
            while (rs.next()) {
                sessionList.add(
                        new Login_session(
                                rs.getInt("SessionId"),
                                rs.getString("UserName"),
                                rs.getBoolean("Login")
                        )
                );
            }
            getAll.close();
            rs.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
        return sessionList;
    }

    @Override
    public void UpdateLoginSession(Login_session session) {
        try {
            PreparedStatement updateSession = connection.prepareStatement(
                    "UPDATE Login_sessions SET UserName = ?, Login = ? WHERE SessionId = ?"
            );
            updateSession.setString(1, session.getUserName());
            updateSession.setBoolean(2, session.isLogin());
            updateSession.setInt(3, session.getSessionId());
            updateSession.execute();
            updateSession.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void DeleteLoginSession(int id) {
        try {
            PreparedStatement deleteSession = connection.prepareStatement("DELETE FROM Login_sessions WHERE SessionId = ?");
            deleteSession.setInt(1, id);
            deleteSession.execute();
            deleteSession.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }
}
