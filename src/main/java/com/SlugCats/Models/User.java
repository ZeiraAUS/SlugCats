package com.SlugCats.Models;

public class User {
    private int UserId;
    private String UserName;
    private String Password;
    private String FirstName;
    private String LastName;
    private String Email;

    /**
     * The constructor for the User object.
     * @param userId The ID of the User object.
     * @param userName The username of the User.
     * @param password The password of the User.
     * @param firstName The first name of the User.
     * @param lastName The last name of the User.
     * @param email The email of the User.
     */
    public User(int userId, String userName, String password, String firstName, String lastName, String email) {
        UserId = userId;
        UserName = userName;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
    }

    /**
     * The constructor for the User object.
     * @param userName The username of the User.
     * @param password The password of the User.
     * @param firstName The first name of the User.
     * @param lastName The last name of the User.
     * @param email The email of the User.
     */
    public User(String userName, String password, String firstName, String lastName, String email) {
        UserName = userName;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        Email = email;
    }

    public int getUserId() {
        return UserId;
    }
    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }

    public void  changePassword(String newPassword) {
        Password = newPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", Password='" + Password + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
