package com.fitbook.service;

import com.fitbook.dto.ProgramDto;
import com.fitbook.entity.program.Program;
import com.fitbook.entity.program.ProgramPart;
import com.fitbook.exception.ResourceNotFoundException;
import com.fitbook.repository.ProgramRepository;
import com.fitbook.util.Mapper;
import com.fitbook.util.ProgramPartUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    private final Mapper mapper;

    private final ProgramPartUtil programPartUtil;

    @Autowired
    public ProgramService(ProgramRepository programRepository, Mapper mapper, ProgramPartUtil programPartUtil) {
        this.programRepository = programRepository;
        this.mapper = mapper;
        this.programPartUtil = programPartUtil;
    }

    public Program findById(Long id) {
        Optional<Program> programOpt = programRepository.findById(id);
        if (programOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Failed to find program with id %d", id));
        }

        return programOpt.get();
    }

    public ProgramDto create(ProgramDto programDto) {
        Program program = mapper.map(programDto);

        List<ProgramPart> programParts = program.getProgramParts();

        program.setProgramParts(programPartUtil.transformProgramParts(programParts));

        return mapper.map(programRepository.save(program));
    }

    public ProgramDto update(Long id, ProgramDto programDto) {
        Optional<Program> programOpt = programRepository.findById(id);
        if (programOpt.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Failed to find program with id %d", id));
        }

        Program program = programOpt.get();
        mapper.map(programDto, program);
        program.setId(id);

        program = programRepository.save(program);
        return mapper.map(program);
    }
}
