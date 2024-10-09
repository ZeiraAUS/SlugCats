package com.SlugCats.Models;

public class salt {
    private int saltID;
    private String UserName;
    private String salt;

    public salt(int saltID, String UserName, String salt) {
        this.saltID = saltID;
        this.UserName = UserName;
        this.salt = salt;
    }
    public salt( String UserName, String salt) {
        this.UserName = UserName;
        this.salt = salt;
    }
    public int getSaltID() {
        return saltID;
    }
    //public void setSaltID(int saltID) {this.saltID = saltID;}
    public String getUserName() {
    return UserName;}
    //public void setUserName(String UserName) {UserName = UserName;}
    public String getSalt() {
        return salt;
    }
   // public void setSalt(String salt) {salt = salt;}

}
