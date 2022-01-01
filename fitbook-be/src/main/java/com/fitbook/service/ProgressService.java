package com.fitbook.service;

import com.fitbook.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgressService {

    private final ProgressRepository progressRepository;

    @Autowired
    public ProgressService(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }
}
