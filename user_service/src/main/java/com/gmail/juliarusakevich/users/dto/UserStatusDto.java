package com.gmail.juliarusakevich.users.dto;

import com.gmail.juliarusakevich.users.entity.enums.UserStatus;

public class UserStatusDto {

    private UserStatus status;

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}
