package com.fitbook.config;

import com.fitbook.service.security.JwtRequestFilter;
import com.fitbook.service.security.SecurityAuthenticationProvider;
import com.fitbook.service.security.UnauthorizedAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final SecurityAuthenticationProvider securityAuthenticationProvider;
    private final UnauthorizedAuthenticationEntryPoint unauthorizedAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(SecurityAuthenticationProvider securityAuthenticationProvider,
                          UnauthorizedAuthenticationEntryPoint unauthorizedAuthenticationEntryPoint,
                          JwtRequestFilter jwtRequestFilter) {
        this.securityAuthenticationProvider = securityAuthenticationProvider;
        this.unauthorizedAuthenticationEntryPoint = unauthorizedAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(securityAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors()
                .and()
                .authorizeRequests().antMatchers( "/api/registration", "/api/login").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedAuthenticationEntryPoint)
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().disable()
                .logout().disable()
                .csrf().disable();
    }
}
