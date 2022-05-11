package com.megalib.dao;

import com.megalib.entity.Author;
import com.megalib.entity.Book;
import com.megalib.entity.PublishingHouse;
import com.megalib.enums.Genre;
import com.megalib.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookDao implements Dao<Long, Book> {

    private static final BookDao INSTANCE = new BookDao();

    private static final String FIND_ALL = """
            SELECT b.id, 
            b.title, 
            authors_id, 
            publishing_houses_id, 
            genre, 
            pages, 
            image, 
            b.description,
            a.firstname, 
            a.lastname, 
            a.patronymic,
            p.title,
            p.year_of_foundation
            FROM books b 
            join authors a on b.authors_id = a.id
            join publishing_houses p on b.publishing_houses_id = p.id
            """;

    private static final String FIND_BY_ID = FIND_ALL + "WHERE books.id = ?";

    @Override
    @SneakyThrows
    public List<Book> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                books.add(buildBook(resultSet));
            }

            return books;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Book> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            var resultSet = preparedStatement.executeQuery();
            Book book = null;

            if (resultSet.next()) {
                book = buildBook(resultSet);
            }

            return Optional.ofNullable(book);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public Book save(Book entity) {
        return null;
    }

    public static BookDao getInstance() {
        return INSTANCE;
    }

    private Book buildBook(ResultSet resultSet) throws SQLException {
        var publishing_house = new PublishingHouse(
                resultSet.getObject("publishing_houses_id", Long.class),
                resultSet.getObject("title", String.class),
                resultSet.getObject("year_of_foundation", Integer.class),
                null
        );
        var author = new Author(
                resultSet.getObject("authors_id", Long.class),
                resultSet.getObject("firstname", String.class),
                resultSet.getObject("lastname", String.class),
                resultSet.getObject("patronymic", String.class),
                null,
                null
        );

        return Book.builder()
                .id(resultSet.getObject("id", Long.class))
                .title(resultSet.getObject("title", String.class))
                .author(author)
                .publishingHouse(publishing_house)
                .genre(Genre.valueOf(resultSet.getObject("genre", String.class)))
                .pages(resultSet.getObject("pages", Integer.class))
                .image(resultSet.getObject("image", String.class))
                .description(resultSet.getObject("description", String.class))
                .build();
    }
}
