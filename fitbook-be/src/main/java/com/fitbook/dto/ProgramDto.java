package com.fitbook.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProgramDto {

    private Long id;

    private String description;

    private List<ProgramPartDto> programParts;
}
