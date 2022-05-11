package com.megalib.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDto {

    Long id;
    String image;
//    naming = title + authors_id + publishing_houses_id + genre + pages
    String naming;
    String description;
}
