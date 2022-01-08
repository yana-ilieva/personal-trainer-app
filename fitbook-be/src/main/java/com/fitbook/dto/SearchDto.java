package com.fitbook.dto;

import com.fitbook.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {

    private Gender gender;
    private String city;
    private String name;
}
