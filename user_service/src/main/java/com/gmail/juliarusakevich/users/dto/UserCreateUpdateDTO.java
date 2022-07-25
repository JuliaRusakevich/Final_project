package com.gmail.juliarusakevich.users.dto;

import com.gmail.juliarusakevich.users.entity.enums.UserRole;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;

import java.util.HashSet;
import java.util.Set;

/*
uuid*	string($uuid)
readOnly: true
Уникальный идентификатор сущности

dt_create*	number($int64)
readOnly: true
Дата создания сущности (linux time)

dt_update*	integer($int64)
readOnly: true
Дата последнего обновления сущности (linux time)
}
 */
public class UserCreateUpdateDTO {
    /*
    {
  "mail": "string",
  "nick": "string",
  "role": "ADMIN",
  "status": "WAITING_ACTIVATION",
  "password": "string"
}
     */

    private String mail;
    private String nick;
    private Set<UserRole> role = new HashSet<>();
    private UserStatus status;//[ WAITING_ACTIVATION, ACTIVATED, DEACTIVATED ]
    private String password;

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

    public Set<UserRole> getRole() {
        return role;
    }

    public void setRole(Set<UserRole> role) {
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
}
