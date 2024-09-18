package com.SlugCats.NewAuth;

import com.SlugCats.Models.User;

//import org.mindrot.jbcrypt.BCrypt;
public class register extends Authentication{
    public boolean register_a_user(String userName, String password, String ConfirmPassword,String firstName, String lastName, String email) {
        if (!password.equals(ConfirmPassword)) {
            System.out.println("Password and confirm password do not match.");
            return false;
        }
        //String hashed = BCrypt.hashpw(password, BCrypt.gensalt()); //try hash I will do it later
        User useradd=new User( userName, password, firstName,lastName, email);
        boolean isRigster=register(useradd);
        return isRigster;

}
}
