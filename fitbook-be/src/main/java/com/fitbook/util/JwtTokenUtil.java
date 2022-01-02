package com.fitbook.util;

import com.fitbook.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    String key = "asdasd";

    public String generateToken(User user, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("role", role);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 10000))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String requestTokenHeader = request.getHeader("Authorization");

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            return requestTokenHeader.substring(7);
        }

        return "";
    }

    public String getEmailFromToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();



        return body.getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return body.getExpiration();
    }

    public Boolean validateToken(String token, User user) {
        String email = getEmailFromToken(token);
        return email.equals(user.getEmail()) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
}
