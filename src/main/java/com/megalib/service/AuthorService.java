package com.megalib.service;

import com.megalib.dao.AuthorDao;
import com.megalib.dto.AuthorDto;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AuthorService {

    private static final AuthorService INSTANCE = new AuthorService();

    private final AuthorDao authorDao = AuthorDao.getInstance();

    public List<AuthorDto> findAll() {
        return authorDao.findAll().stream()
                .map(author -> AuthorDto.builder()
                        .id(author.getId())
                        .naming("""
                                %s %s %s
                                Дата рождения: %s
                                """.formatted(author.getFirstname(),
                                author.getLastname(),
                                author.getPatronymic(),
                                author.getBirthday().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))))
                        .about(author.getAbout())
                        .build()
                )
                .collect(Collectors.toList());

    }

    public static AuthorService getInstance() {
        return INSTANCE;
    }
}
