package com.gmail.juliarusakevich.user.service.dto;

import com.gmail.juliarusakevich.user.repository.model.enums.UserRole;
import com.gmail.juliarusakevich.user.repository.model.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public final class UserReadDTO {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String mail;
    private String nick;
    private UserRole role;
    private UserStatus status;
    private String password;

    public UserReadDTO() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserReadDTO{" +
               "uuid=" + uuid +
               ", dtCreate=" + dtCreate +
               ", dtUpdate=" + dtUpdate +
               ", mail='" + mail + '\'' +
               ", nick='" + nick + '\'' +
               ", role=" + role +
               ", status=" + status +
               ", password='" + password + '\'' +
               '}';
    }
}
