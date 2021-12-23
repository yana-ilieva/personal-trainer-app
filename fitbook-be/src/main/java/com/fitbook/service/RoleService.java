package com.fitbook.service;

import com.fitbook.entity.user.Role;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Set<Role> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Role with name: " + name + " does not exist."));
    }
}
