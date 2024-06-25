package com.example.projet_ict308.dao;

import com.example.projet_ict308.entities.User;

public interface IUser {

    public User seConnecter(String email, String password);

    boolean userExists(String email);
}
