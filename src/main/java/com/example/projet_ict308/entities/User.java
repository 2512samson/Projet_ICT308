package com.example.projet_ict308.entities;

public class User {
    private int idU;
    private  String email;
    private String password;

    public User(int idU, String email, String password) {
        this.idU = idU;
        this.email = email;
        this.password = password;
    }

    public User() {

    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
