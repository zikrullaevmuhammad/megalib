package com.megalib.service;

import com.megalib.dao.PublishingHouseDao;
import com.megalib.dto.PublishingHouseDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PublishingHouseService {

    private static final PublishingHouseService INSTANCE = new PublishingHouseService();

    private final PublishingHouseDao publishingHouseDao = PublishingHouseDao.getInstance();

    public List<PublishingHouseDto> findAll() {
        return publishingHouseDao.findAll().stream()
                .map(publishingHouse -> PublishingHouseDto.builder()
                        .id(publishingHouse.getId())
                        .naming("""
                                %s 
                                Год основания: %s
                                """.formatted(publishingHouse.getTitle(),
                                publishingHouse.getYearOfFoundation().toString()))
                        .description(publishingHouse.getDescription())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static PublishingHouseService getInstance() {
        return INSTANCE;
    }
}
