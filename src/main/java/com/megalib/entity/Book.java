package com.megalib.entity;

import com.megalib.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private Long id;
    private String title;
    private Author author;
    private PublishingHouse publishingHouse;
    private Genre genre;
    private Integer pages;
    private String image;
    private String description;
}
