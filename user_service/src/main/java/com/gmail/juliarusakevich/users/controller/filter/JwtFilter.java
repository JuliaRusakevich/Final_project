package com.gmail.juliarusakevich.users.controller.filter;

import com.gmail.juliarusakevich.users.controller.utils.JwtTokenUtil;
import com.gmail.juliarusakevich.users.service.UsersService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JwtFilter extends OncePerRequestFilter {


    private final JwtTokenUtil jwtTokenUtil;
    private final UsersService usersService;
    public static final String AUTHORIZATION = "Authorization";

    public JwtFilter(JwtTokenUtil jwtTokenUtil, UsersService usersService) {
        this.jwtTokenUtil = jwtTokenUtil;

        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        var token = getTokenFromRequest(httpServletRequest);
        if (token != null && JwtTokenUtil.validate(token)) {
            var userLogin = JwtTokenUtil.getUsername(token);
            var customUser = usersService.loadUserByUsername(userLogin);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    customUser, null, customUser.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        var bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}