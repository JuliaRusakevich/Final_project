package com.gmail.juliarusakevich.users.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gmail.juliarusakevich.users.entity.User;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;

import static com.gmail.juliarusakevich.users.entity.enums.UserStatus.*;

public class CustomUserDetails implements UserDetails {

    private UUID uuid;
    private String mail;
    private UserStatus status;
    private Collection<? extends GrantedAuthority> authorities;


    @JsonIgnore
    private String password;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(User object) {
        CustomUserDetails c = new CustomUserDetails();
        c.uuid = object.getUuid();
        c.mail = object.getMail();
        c.password = object.getPassword();
        c.status = object.getStatus();
        c.authorities = object.getRole();
        return c;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {

        if (CustomUserDetails.this.status.equals(WAITING_ACTIVATION) ||
            CustomUserDetails.this.status.equals(DEACTIVATED)) {
            return false;
        }
        return CustomUserDetails.this.status.equals(ACTIVATED);

    }
}