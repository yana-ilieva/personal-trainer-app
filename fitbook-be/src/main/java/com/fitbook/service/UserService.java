package com.fitbook.service;

import com.fitbook.dto.UserDto;
import com.fitbook.entity.user.Role;
import com.fitbook.entity.user.User;
import com.fitbook.exception.DuplicateErrorException;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleService roleService,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Set<User> findAll() {
        return new HashSet<>(userRepository.findAll());
    }

    public User findById(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with id %d not found", id));
        }
        return userOpt.get();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email: " + email + " does not exist."));
    }

    public User save(UserDto userDto) {
        try {
            Role role = roleService.findByName(userDto.getRole());

            User user = User.builder()
                    .email(userDto.getEmail())
                    .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .role(role)
                    .deleted(false)
                    .build();

            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateErrorException("User with email: " + userDto.getEmail() + " already exists.");
        }
    }
}
