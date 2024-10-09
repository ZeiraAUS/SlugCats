package com.SlugCats.DAOs;

import com.SlugCats.Models.salt;
import com.SlugCats.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaltDAO implements ISaltDAO {
    private Connection connection;

    public SaltDAO() {
        connection = DatabaseConnection.getInstance();
    }

    @Override
    public void CreateSaltTable() {
        try {
            Statement createTable = connection.createStatement();
            createTable.execute(
                    "CREATE TABLE IF NOT EXISTS Salts (" +
                            "SaltId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "UserName VARCHAR NOT NULL, " +
                            "Salt VARCHAR NOT NULL" +
                            ")"
            );
            createTable.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void AddSalt(salt salt) {
        try {
            PreparedStatement insertSalt = connection.prepareStatement(
                    "INSERT INTO Salts (UserName, Salt) VALUES (?, ?)"
            );
            insertSalt.setString(1, salt.getUserName());
            insertSalt.setString(2, salt.getSalt());
            insertSalt.execute();
            insertSalt.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override

    public salt GetSalt(String username) {
        try {
            // 根据 UserName 查询 salt
            PreparedStatement getSalt = connection.prepareStatement("SELECT * FROM Salts WHERE UserName = ?");
            getSalt.setString(1, username); // 将用户名设置为查询条件
            ResultSet rs = getSalt.executeQuery();
            if (rs.next()) {
                return new salt(
                        rs.getInt("SaltId"),
                        rs.getString("UserName"),
                        rs.getString("Salt")
                );
            }
            // 关闭 ResultSet 和 PreparedStatement
            getSalt.close();
            rs.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
        return null;
    }

    @Override
    public List<salt> GetSaltList() {
        List<salt> saltList = new ArrayList<>();
        try {
            Statement getAll = connection.createStatement();
            ResultSet rs = getAll.executeQuery("SELECT * FROM Salts");
            while (rs.next()) {
                saltList.add(
                        new salt(
                                rs.getInt("SaltId"),
                                rs.getString("UserName"),
                                rs.getString("Salt")
                        )
                );
            }
            getAll.close();
            rs.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
        return saltList;
    }

    @Override
    public void UpdateSalt(salt salt) {
        try {
            PreparedStatement updateSalt = connection.prepareStatement(
                    "UPDATE Salts SET UserName = ?, Salt = ? WHERE SaltId = ?"
            );
            updateSalt.setString(1, salt.getUserName());
            updateSalt.setString(2, salt.getSalt());
            updateSalt.setInt(3, salt.getSaltID());
            updateSalt.execute();
            updateSalt.close();
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    @Override
    public void DeleteSalt(int id) {
        try {
            PreparedStatement deleteSalt = connection.prepareStatement("DELETE FROM Salts WHERE SaltId = ?");
            deleteSalt.setInt(1, id);
            deleteSalt.execute();
            deleteSalt.close();
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
