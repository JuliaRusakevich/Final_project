package com.gmail.juliarusakevich.user.service.dto;

import com.gmail.juliarusakevich.user.repository.model.enums.UserRole;
import com.gmail.juliarusakevich.user.repository.model.enums.UserStatus;

import java.time.LocalDateTime;


public class UserCreateUpdateDTO {

    private LocalDateTime dtCreate;
    private String username;
    private String nick;
    private UserRole role;
    private UserStatus status;
    /*
    Только во время создания, для обновления будет другая дто,
    так как пароль обновляется отдельно от всей сущности
     */
    //@NotBlank(groups = LruCache.CreateAction.class)
    private String password;

    public UserCreateUpdateDTO() {
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
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
        return "UserCreateUpdateDTO{" +
               "mail='" + username + '\'' +
               ", nick='" + nick + '\'' +
               ", role=" + role +
               ", status=" + status +
               ", password='" + password + '\'' +
               '}';
    }
}
