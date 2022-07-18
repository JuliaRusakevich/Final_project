package com.gmail.juliarusakevich.user.controller.filter;

import com.gmail.juliarusakevich.user.controller.utils.JwtTokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final UserDetailsManager userManager;

    public JwtFilter(UserDetailsManager userManager) {
        this.userManager = userManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        /*
        получает Header AUTHORIZATION
        https://learning.postman.com/docs/sending-requests/variables/ - ИЗУЧИТЬ
         */
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        // Не пустой и начинается с "Bearer "
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        // Get jwt token and validate
        /*
        получаем токен, убраем Bearer
        token = сам токен при логине
        JwtTokenUtil.validate(token) - создается парсер, передается секретный ключ, разбирает токен
       , то есть токен валидный?
       Невалидный, если:
       - подпись недействительна
       - не по стандарту
       - вышел из обращения
       - алгоритм шифрования не поддерживается нашей библиотекой
       - если строка пустая
         */
        final String token = header.split(" ")[1].trim();
        if (!JwtTokenUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context
        /*
        Фильтро отвалидировал токен ->
        идет к userManager и загружает пользователя loadUserByUsername(), то есть
        идет в базу, загружает пользователя и из токена достаем логин getUsername(token)
         */

        UserDetails userDetails = userManager
                .loadUserByUsername(JwtTokenUtil.getUsername(token));

        /*
        Полученное помещаем в специализированный объект UsernamePasswordAuthenticationToken,
        который хранит getAuthorities(), то есть получаем Роли

         */
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()

        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        /*
        Помещает инфу о пользователе в контекст spring
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }
}