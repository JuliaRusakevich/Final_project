package com.gmail.juliarusakevich.users.mapper;

import com.gmail.juliarusakevich.users.entity.enums.UserRole;
import com.gmail.juliarusakevich.users.entity.enums.UserStatus;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class EnumMapper {

    @Named("role")
    public Set<UserRole> setUserRole(Set<UserRole> roles) {
        if (roles != null) {
            return new HashSet<>(roles);
        } else {
            return Set.of(UserRole.ROLE_USER);
        }
    }

    @Named("status")
    public UserStatus setUserStatus(UserStatus status) {
        return Objects.requireNonNullElse(status, UserStatus.WAITING_ACTIVATION);
    }
}
