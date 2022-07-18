package com.gmail.juliarusakevich.user.service.dto;

public class UserLogin {

    private String username;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String mail, String password) {
        this.username = mail;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


}
