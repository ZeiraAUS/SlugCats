package com.SlugCats.NewAuth;

import java.io.*;

public class LoginStatus {
    private static final String LOGIN_STATUS_FILE = "login_status.txt";


    public static String getLoggedInUsername() {
        File file = new File(LOGIN_STATUS_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static void setLoginStatus(String username) {
        File file = new File(LOGIN_STATUS_FILE);
        if (username != null && !username.isEmpty()) {

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(username);
                System.out.println("Login status saved for user: " + username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            if (file.exists()) {
                file.delete();
                System.out.println("Login status file deleted.");
            }
        }
    }
    public static void logout() {
        File file = new File(LOGIN_STATUS_FILE);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Login status file successfully deleted.");
            } else {
                System.out.println("Failed to delete login status file.");
            }
        } else {
            System.out.println("No login status file to delete.");
        }
    }



}

