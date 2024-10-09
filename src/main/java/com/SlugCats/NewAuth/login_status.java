package com.SlugCats.NewAuth;
import com.SlugCats.DAOs.SaltDAO;
import com.SlugCats.Models.User;
import com.SlugCats.Models.salt;

public class login_status implements LoginInterface{
    private boolean login_status;
    private String username;
    private User User;

    @Override
    public String gotUsername(){
        return username;

    }
    @Override
    public boolean gotLogin_status(){
        return login_status;

    }
    @Override
    public boolean  is_login(String username, String password){
        Authentication userlogin=new Authentication();
        SaltDAO saltDAO=new SaltDAO();
        salt salt=saltDAO.GetSalt(username);
        String saltpassword= salt.getSalt();
        if(salt==null){
            System.out.println("User not found");
        }
        String hash_password =Hash_salt.generateHash(password, saltpassword);
        User user=userlogin.login(username,hash_password);
        if(user==null){
            System.out.println("User not found");
            return false;
        }
        this.User=user;
        this.login_status=true;
        this.username=username;
        System.out.println("login_status: "+login_status);
        return true;
    }
    @Override
    public User getUser(){
        return User;
    }

    public boolean change_password(String Email, String NewPassword , String confirmNewPassword){
        if(!User.getEmail().equals(Email)){
            System.out.println("Email does not match");
            return false;
        }
       if (NewPassword==null || confirmNewPassword==null ||confirmNewPassword.equals(NewPassword)){
            User.changePassword(NewPassword);
            System.out.println("Password changed successfully");
            return true;
        }
        System.out.println("Password and confirm password do not match.");

        return false;
    }
    @Override
    public void logout(){
        login_status=false;
        User=null;
        username=null;
        System.out.println("Logged out successfully");
    }
}
