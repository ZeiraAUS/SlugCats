package com.SlugCats.NewAuth;
import com.SlugCats.DAOs.*;
import com.SlugCats.Models.User;

public class Authentication implements Authentication_function {


    @Override
    public User login(String username, String password) {
        // login by user DAO
        UserDAO userDAO=new UserDAO();
        User user = userDAO.GetUserByUsernamePassword(username, password);

        return user;
    }
    @Override
    public boolean register(User user) {
        UserDAO userDAO=new UserDAO();
        if (userDAO.GetUserByUsernamePassword(user.getUserName(), user.getPassword()) == null) {
            userDAO.AddUser(user);
            return true;
        }
        return false;}

}
