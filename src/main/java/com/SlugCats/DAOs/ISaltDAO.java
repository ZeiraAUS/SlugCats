package com.SlugCats.DAOs;


import com.SlugCats.Models.salt;

import java.util.List;

public interface ISaltDAO {
    void CreateSaltTable();
    void AddSalt(salt salt);
    salt GetSalt(String username);
    List<salt> GetSaltList();
    void UpdateSalt(salt salt);
    void DeleteSalt(int id);
    void close();
}
