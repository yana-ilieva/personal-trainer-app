package com.fitbook.controller;

import com.fitbook.dto.LoginDto;
import com.fitbook.dto.LoginResponseDto;
import com.fitbook.entity.user.User;
import com.fitbook.service.security.SecurityAuthenticationProvider;
import com.fitbook.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {

    private final SecurityAuthenticationProvider securityAuthenticationProvider;

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public LoginController(SecurityAuthenticationProvider securityAuthenticationProvider, JwtTokenUtil jwtTokenUtil) {
        this.securityAuthenticationProvider = securityAuthenticationProvider;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping
    public LoginResponseDto login(@RequestBody LoginDto loginDto) {
        Authentication authenticate = securityAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        User user = (User) authenticate.getPrincipal();
        LoginResponseDto dto = new LoginResponseDto();
        dto.setJwt(jwtTokenUtil.generateToken(user, user.getRole().getName()));
        dto.setRole(user.getRole().getName());
        dto.setUserId(user.getId());
        return dto;
    }
}
