package NewAuth;

import Models.User;

public interface LoginInterface {

    // Method to get the username of the current logged-in user
    String gotUsername();

    // Method to check the login status
    boolean gotLogin_status();

    // Method to log in a user by username and password
    boolean is_login(String username, String password);

    // Method to get the logged-in User object
    User getUser();

    // Method to log out the current user
    void logout();
}