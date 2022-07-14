package com.gmail.juliarusakevich.user.repository.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gmail.juliarusakevich.user.controller.json.LocalDateTimeDeserializer;
import com.gmail.juliarusakevich.user.controller.json.LocalDateTimeSerializer;
import com.gmail.juliarusakevich.user.repository.model.enums.UserRole;
import com.gmail.juliarusakevich.user.repository.model.enums.UserStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "user_info")
public class UserCreate implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String mail;
    private String nick;
    private UserRole role;
    private UserStatus status;
    private String password;

    public UserCreate() {
    }

    public UserCreate(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, String mail, String nick, UserRole role, UserStatus status, String password) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.nick = nick;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @ReadOnlyProperty
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Column(name = "dt_create", updatable = false)
    @ReadOnlyProperty
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    @Version
    @Column(name = "dt_update")
    @ReadOnlyProperty
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column(name = "nick")
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCreate)) return false;
        UserCreate user = (UserCreate) o;
        return Objects.equals(getUuid(), user.getUuid()) && Objects.equals(getDtCreate(), user.getDtCreate()) && Objects.equals(getDtUpdate(), user.getDtUpdate()) && Objects.equals(getMail(), user.getMail()) && Objects.equals(getNick(), user.getNick()) && getRole() == user.getRole() && getStatus() == user.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getDtCreate(), getDtUpdate(), getMail(), getNick(), getRole(), getStatus());
    }

    @Override
    public String toString() {
        return "UserCreate{" +
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
