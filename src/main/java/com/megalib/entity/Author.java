package com.megalib.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    private Long id;
    private String firstname;
    private String lastname;
    private String patronymic;
    private LocalDate birthday;
    private String about;
}
