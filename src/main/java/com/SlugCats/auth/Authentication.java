package com.SlugCats.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Authentication implements UserService{
    @Override
    public boolean register(String userName, String password, String confirmPassword,String FirstName, String LastName, String email){
        if (!password.equals(confirmPassword)) {
            System.out.println("Password and confirm password do not match.");
            return false;
        }

        String queryUserExist = "SELECT * FROM Users WHERE username = ?";
        String queryEmailExist = "SELECT * FROM Users WHERE email = ?";
        String insertQuery = "INSERT INTO Users (UserName, Password, FirstName, LastName, email) VALUES (?, ?, ?, ?, ?)";
        //need to improve like hash and salt the password
        try (Connection conn = DatabaseConnection.getInstance();
             PreparedStatement checkUserStmt = conn.prepareStatement(queryUserExist);
             PreparedStatement checkEmailStmt = conn.prepareStatement(queryEmailExist)) {

            // Check if the username exists
            checkUserStmt.setString(1, userName);
            ResultSet rsUser = checkUserStmt.executeQuery();
            if (rsUser.next()) {
                System.out.println("Username already exists.");
                return false;
            }

            // Check if the email exists
            checkEmailStmt.setString(1, email);
            ResultSet rsEmail = checkEmailStmt.executeQuery();
            if (rsEmail.next()) {
                System.out.println("Email already registered.");
                return false;
            }

            // If username and email are unique, proceed to register new user
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, userName);
                insertStmt.setString(2, password);
                insertStmt.setString(3, FirstName);
                insertStmt.setString(4, LastName);
                insertStmt.setString(5, email);

                int result = insertStmt.executeUpdate();
                if (result > 0) {
                    System.out.println("User registered successfully.");
                    return true;
                } else {
                    System.out.println("Failed to register user.");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

    }

