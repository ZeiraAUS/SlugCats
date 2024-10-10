package com.SlugCats.NewAuth;

import com.SlugCats.DAOs.*;
import com.SlugCats.Models.User;

public class Change {
    static boolean resetpassword(String username,String password,String ConfirmPassword){// not complete
        if (!password.equals(ConfirmPassword)) {
            System.out.println("Password and confirm password do not match.");
            return false;

        }
        UserDAO userDAO=new UserDAO();
        User user= userDAO.getUserByUsername( username);
        if(user==null){
            System.out.println("User not found.");
            return false;
        }
        String salt = Hash_salt.getSalt();
        String hashedPassword=Hash_salt.generateHash(password, salt);

        user.changePassword(password);


        return true;
    }
}
