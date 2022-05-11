package com.megalib.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublishingHouse {

    private Long id;
    private String title;
    private Integer yearOfFoundation;
    private String description;
}
