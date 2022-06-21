package com.fitbook.service.security;

import com.fitbook.entity.user.User;
import com.fitbook.service.UserService;
import com.fitbook.util.JwtTokenUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @Autowired
    public JwtRequestFilter(JwtTokenUtil jwtTokenUtil,
                            UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenUtil.getTokenFromRequest(httpServletRequest);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        if (Strings.isNotBlank(token)) {
            String emailFromToken = jwtTokenUtil.getEmailFromToken(token);
            if (Strings.isNotBlank(emailFromToken)) {
                User user = userService.findByEmail(emailFromToken);
                usernamePasswordAuthenticationToken = validateToken(token, user);
            }
        }

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken validateToken(String token, User user) {
        if (jwtTokenUtil.validateToken(token, user)) {
            return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), Collections.singletonList(user.getRole()));
        }

        return null;
    }
}
