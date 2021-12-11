package com.fitbook.controller;

import com.fitbook.dto.LoginDto;
import com.fitbook.entity.user.User;
import com.fitbook.service.security.SecurityAuthenticationProvider;
import com.fitbook.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final SecurityAuthenticationProvider securityAuthenticationProvider;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LoginController(SecurityAuthenticationProvider securityAuthenticationProvider, JwtTokenUtil jwtTokenUtil) {
        this.securityAuthenticationProvider = securityAuthenticationProvider;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Authentication authenticate = securityAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        User user = (User) authenticate.getPrincipal();
        String token = jwtTokenUtil.generateToken(user, user.getRole().getName());
        return ResponseEntity.ok(token);
    }
}
