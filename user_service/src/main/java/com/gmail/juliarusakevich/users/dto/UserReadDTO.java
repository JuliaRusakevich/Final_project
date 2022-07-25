package com.gmail.juliarusakevich.users.dto;

import com.gmail.juliarusakevich.users.entity.enums.UserRole;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public final class UserReadDTO {

    private final UUID uuid;
    private final String mail;
    private final String nick;
    private final Set<UserRole> role;
    private final UserStatus status;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String createdBy;
    private final String modifiedBy;


    public UserReadDTO(UUID uuid, String mail, String nick, Set<UserRole> role, UserStatus status, LocalDateTime createdAt, LocalDateTime modifiedAt, String createdBy, String modifiedBy) {
        this.uuid = uuid;
        this.mail = mail;
        this.nick = nick;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getMail() {
        return mail;
    }

    public String getNick() {
        return nick;
    }

    public Set<UserRole> getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }
}
