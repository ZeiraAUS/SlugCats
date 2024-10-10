package com.SlugCats.DAOs;

import com.SlugCats.Models.Login_session;
import java.util.List;

public interface ILoginSessionDAO {
    void CreateLoginSessionTable();
    void AddLoginSession(Login_session session);
    Login_session GetLoginSession(int id);
    List<Login_session> GetLoginSessionList();
    void UpdateLoginSession(Login_session session);
    void DeleteLoginSession(int id);
    void close();
}
