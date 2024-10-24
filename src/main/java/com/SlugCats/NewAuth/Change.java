package com.SlugCats.NewAuth;

import com.SlugCats.DAOs.*;
import com.SlugCats.Models.User;
import com.SlugCats.Models.salt;

public class Change {
    public static boolean resetpassword(String username,String password,String ConfirmPassword){// not complete
        if (!password.equals(ConfirmPassword)) {// Check if passwords match
            System.out.println("Password and confirm password do not match.");
            return false;

        }
        UserDAO userDAO=new UserDAO();
        User user= userDAO.getUserByUsername( username);
        if(user==null){
            System.out.println("User not found.");
            return false;
        }

        SaltDAO saltDAO=new SaltDAO();
        salt salt=saltDAO.GetSalt(username);
        //use exist salt
        if(salt==null){
            System.out.println("User not found");
            return false;
        }
        String saltpassword= salt.getSalt();
        String hash_password =Hash_salt.generateHash(password, saltpassword);

        user.changePassword(hash_password);
        userDAO.UpdateUser(user);

        return true;
    }
}
