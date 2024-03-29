package com.gmail.juliarusakevich.classifier.controller.utils;

import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtTokenUtil {

    private static final String jwtSecret = "NDQ1ZjAzNjQtMzViZi00MDRjLTljZjQtNjNjYWIyZTU5ZDYw";
    private static final String jwtIssuer = "ITAcademy";


    public static String generateAccessToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }


    public void setTokenClaims(Claims tTokenClaims) {
         /*
         this.tokenClaims = tTokenClaims;

         Collection<?> roles = tokenClaims.get(ROLES_CLAIM_NAME, Collection.class);

         if (null != roles) {
             ArrayList<GrantedAuthority> authsList = new ArrayList<>(roles.size());

             for (Object role : roles) {
                 authsList.add(new SimpleGrantedAuthority(role.toString()));
             }

             authorities = Collections.unmodifiableList(authsList);
         } else {
             authorities = Collections.emptyList();
         }

          */
    }

    //разбираем токен, достаем роли
    public static Collection<? extends GrantedAuthority> getAuthorities(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        Collection<?> roles = claims.get("role", Collection.class);

        if (roles == null) {
            return Collections.emptyList();
        } else {
            ArrayList<GrantedAuthority> authsList = new ArrayList<>(roles.size());

            for (Object role : roles) {
                authsList.add(new SimpleGrantedAuthority(role.toString()));
            }

            return Collections.unmodifiableList(authsList);
        }
    }

    public static boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }
}


