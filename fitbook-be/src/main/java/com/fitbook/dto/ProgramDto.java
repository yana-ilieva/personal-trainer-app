package com.fitbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgramDto {

    private Long id;

    private String description;

    private List<ProgramPartDto> programParts;
}
