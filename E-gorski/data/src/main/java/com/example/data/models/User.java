package com.example.data.models;

public class User {
    private String username;
    private String password;
    private boolean IsAdmin;

    public User(){

    }

    public User(String username, String password, boolean IsAdmin){
        this.username = username;
        this.password = password;
        this.IsAdmin = IsAdmin;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return IsAdmin;
    }

    public void setAdmin(boolean admin) {
        IsAdmin = admin;
    }
}
