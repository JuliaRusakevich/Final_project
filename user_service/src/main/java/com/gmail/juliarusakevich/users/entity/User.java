package com.gmail.juliarusakevich.users.entity;

import com.gmail.juliarusakevich.users.entity.enums.UserRole;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users", schema = "security")
public class User extends AuditingEntity<UUID> implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator")
    @ReadOnlyProperty
    private UUID uuid;

    @Column(name = "mail")
    private String mail;

    @Column(name = "nick")
    private String nick;

    @Column(name = "password")
    private String password;
//@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "user_uuid"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authority")
    private Set<UserRole> role = new HashSet<>();

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public User() {
    }

    public User(String login, String password) {
        this.mail = login;
        this.password = password;
    }

    public User(String login,
                String password,
                Set<UserRole> roles) {
        this.mail = login;
        this.password = password;
        this.role = roles;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRole() {
        return role;
    }

    public void setRole(Set<UserRole> roles) {
        this.role = roles;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public List<String> getStringRoles() {

        List<String> rolesList = new ArrayList<>();

        for (UserRole role : getRole()) {
            rolesList.add(role.name());
        }
        return rolesList;
    }
}
