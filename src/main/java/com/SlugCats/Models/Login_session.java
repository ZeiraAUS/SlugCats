package com.SlugCats.Models;

public class Login_session {
    private int sessionId;
    private String UserName;
    private boolean login;
    public Login_session(int ID, String UserName, boolean login) {
        sessionId=ID;
        this.UserName = UserName;
        this.login = login;
    }
    public int getSessionId() {
        return sessionId;
    }
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }
    public String getUserName() {
        return UserName;
    }
    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    public boolean isLogin() {
        return login;
    }
    public void setLogin(boolean login) {
        this.login = login;
    }

}
