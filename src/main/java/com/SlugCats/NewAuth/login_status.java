package com.SlugCats.NewAuth;
import com.SlugCats.Models.User;

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
        User user=userlogin.login(username,password);
        if(user==null){
            return false;
        }
        this.User=user;
        this.login_status=true;
        this.username=username;
        return true;
    }
    @Override
    public User getUser(){
        return User;
    }
    @Override
    public void logout(){
        login_status=false;
        User=null;
        username=null;

    }
}
