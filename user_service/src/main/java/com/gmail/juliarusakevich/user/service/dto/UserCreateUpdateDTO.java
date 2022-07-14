package com.gmail.juliarusakevich.user.service.dto;

import com.gmail.juliarusakevich.user.repository.model.enums.UserRole;
import com.gmail.juliarusakevich.user.repository.model.enums.UserStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;


public class UserCreateUpdateDTO {

    private LocalDateTime dtCreate;
    // @Pattern(regexp = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$")
    @Pattern(regexp = "^[а-яА-Яa-zA-Z\\d]{3,15}$")
    private String mail;
    @Pattern(regexp = "^[а-яА-Яa-zA-Z\\d]{3,15}$")
    private String nick;
    private UserRole role;
    private UserStatus status;
    @NotBlank
    private String password;

    public UserCreateUpdateDTO() {
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
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
        return "UserCreateUpdateDTO{" +
               "mail='" + mail + '\'' +
               ", nick='" + nick + '\'' +
               ", role=" + role +
               ", status=" + status +
               ", password='" + password + '\'' +
               '}';
    }
}
