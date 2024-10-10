package com.SlugCats.NewAuth;

import com.SlugCats.DAOs.*;
import com.SlugCats.Models.*;
//import org.mindrot.jbcrypt.BCrypt;
public class register extends Authentication{
    public boolean register_a_user(String userName, String password, String ConfirmPassword,String firstName, String lastName, String email) {
        if (!password.equals(ConfirmPassword)) {
            System.out.println("Password and confirm password do not match.");
            return false;
        }
        String salt = Hash_salt.getSalt();
        String hashedPassword=Hash_salt.generateHash(password, salt);

        User useradd=new User( userName, hashedPassword, firstName,lastName, email);
        boolean isRigster=super.register(useradd);
        if(isRigster){
            String userna=userName;
            salt saltadd=new salt(userna,salt);

            if (saltadd.getUserName() == null || saltadd.getUserName().isEmpty()) {
                System.out.println("Username is empty.");
                System.out.println("Error: UserName is null or empty");

            }
            System.out.println("getUserName() returns: " + saltadd.getUserName());

            SaltDAO saltDAO=new SaltDAO();
            saltDAO.AddSalt(saltadd);
            System.out.println("salt added successfully.");

        }
        return isRigster;
    }
}