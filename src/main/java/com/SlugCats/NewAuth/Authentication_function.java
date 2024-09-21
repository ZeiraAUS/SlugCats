package com.SlugCats.NewAuth;

import com.SlugCats.Models.User;

public interface Authentication_function {
    // In this class I use the DAO to refactor the login and register method
    User login(String username, String password);
    boolean register(User user);
}
