package com.SlugCats.NewAuth;

import com.SlugCats.Models.User;

public interface Authentication_function {
    User login(String username, String password);
    boolean register(User user);
}
