package com.fitbook.service.security;

import com.fitbook.entity.user.User;
import com.fitbook.exception.RequestProcessingException;
import com.fitbook.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
public class SecurityAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityAuthenticationProvider(UserService userService,
                                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (Strings.isBlank(authentication.getName()) || Objects.isNull(authentication.getCredentials())) {
            throw new IllegalArgumentException("Email and password should not be null.");
        }

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userService.findByEmail(email);

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new RequestProcessingException("Email or password are invalid.");
        }

        return new UsernamePasswordAuthenticationToken(user.getId(), password, Collections.singletonList(user.getRole()));
    }

    public boolean supports(Class<?> authenticationToken) {
        return authenticationToken.equals(UsernamePasswordAuthenticationToken.class);
    }

}
