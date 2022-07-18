package com.gmail.juliarusakevich.user.service.dto;

import com.gmail.juliarusakevich.user.repository.model.enums.UserRole;
import com.gmail.juliarusakevich.user.repository.model.enums.UserStatus;

import java.time.LocalDateTime;

public class UserRegistration {

    private String username;
    private String nick;
    private String password;
    private UserRole role;
    private UserStatus status;
    private LocalDateTime dtCreate;

    public UserRegistration() {
    }

    public UserRegistration(String mail, String nick, String password, UserRole role, UserStatus status, LocalDateTime dtCreate) {
        this.username = mail;
        this.nick = nick;
        this.password = password;
        this.role = role;
        this.status = status;
        this.dtCreate = dtCreate;
    }

    public UserRegistration(String mail, String nick, String password, UserRole role) {
        this.username = mail;
        this.nick = nick;
        this.password = password;
        this.role = role;
    }

    public UserRegistration(String mail, String nick, String password) {
        this.username = mail;
        this.nick = nick;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }
}
