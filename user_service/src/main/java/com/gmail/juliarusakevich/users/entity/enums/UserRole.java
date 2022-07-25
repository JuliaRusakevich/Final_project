package com.gmail.juliarusakevich.users.entity.enums;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;

public enum UserRole implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
