package com.example.finalproject_videotutorialapp;

public class Credentials {
    private String userName;
    private String password;

    public Credentials(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) { this.userName = userName; }

    public void setPassword(String password) { this.password = password; }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
