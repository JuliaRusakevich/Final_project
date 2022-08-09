package com.gmail.juliarusakevich.event.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public UserDetails getUser() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /*
    ClassCastException: когда пытаются привести объект к классу,
    экземпляром которого он не является.
    Один из способов избежать этого исключения, когда во время исполнения тип
    объекта не известен, это использовать проверку является ли объект экземпляром
    определенного класса: “instanceof”.
     */
    public boolean isAuthUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null && !(auth instanceof AnonymousAuthenticationToken);
    }

    public boolean isAdmin() {
        return isAuthUser()
               && getUser().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public boolean isUser() {
        return isAuthUser()
               && getUser().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    public boolean isEnableAccount() {
        return getUser().isEnabled();
    }

}


