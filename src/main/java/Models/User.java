package Models;

public class User {
    private int UserId;
    private String UserName;
    private String Password;
    private String FirstName;
    private String LastName;
    private String Email;

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



}
