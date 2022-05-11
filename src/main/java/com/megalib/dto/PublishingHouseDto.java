package com.megalib.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PublishingHouseDto {

    Long id;
//    naming = title + year_of_foundation
    String naming;
    String description;
}
