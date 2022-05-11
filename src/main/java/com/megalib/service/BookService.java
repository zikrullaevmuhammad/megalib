package com.megalib.service;

import com.megalib.dao.BookDao;
import com.megalib.dto.BookDto;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class BookService {

    private static final BookService INSTANCE = new BookService();

    private final BookDao bookDao = BookDao.getInstance();

    public List<BookDto> findAll() {
        return bookDao.findAll().stream()
                .map(book -> BookDto.builder()
                        .id(book.getId())
                        .image(book.getImage())
                        .naming("""
                                Название: %s <br>
                                Автор: %s %s %s <br>
                                Издательство: %s, %s <br>
                                Жанр: %s <br>
                                Страница: %s 
                                """.formatted(book.getTitle(),
                                book.getAuthor().getFirstname(),
                                book.getAuthor().getLastname(),
                                book.getAuthor().getPatronymic(),
                                book.getPublishingHouse().getTitle(),
                                book.getPublishingHouse().getYearOfFoundation(),
                                book.getGenre(),
                                book.getPages()))
                        .description(book.getDescription())
                        .build()
                )
                .collect(Collectors.toList());
    }

    public static BookService getInstance() {
        return INSTANCE;
    }
}
