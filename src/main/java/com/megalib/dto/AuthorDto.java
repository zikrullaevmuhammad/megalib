package com.megalib.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthorDto {

    Long id;
//    naming = firstname + lastname + patronymic + birthday
    String naming;
    String about;
}
