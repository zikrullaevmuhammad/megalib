package com.megalib.dao;

import com.megalib.entity.Author;
import com.megalib.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthorDao implements Dao<Long, Author> {

    private static final AuthorDao INSTANCE = new AuthorDao();

    private static final String FIND_ALL = """
            SELECT id, firstname, lastname, patronymic, birthday, about
            FROM authors
            """;

    private static final String FIND_BY_ID = FIND_ALL + "WHERE id = ?";

    @Override
    @SneakyThrows
    public List<Author> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<Author> authors = new ArrayList<>();

            while (resultSet.next()) {
                authors.add(buildAuthor(resultSet));
            }

            return authors;
        }
    }

    @Override
    @SneakyThrows
    public Optional<Author> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setObject(1, id);
            var resultSet = preparedStatement.executeQuery();

            Author author = null;
            if (resultSet.next()) {
                author = buildAuthor(resultSet);
            }

            return Optional.ofNullable(author);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Author entity) {

    }

    @Override
    public Author save(Author entity) {
        return null;
    }

    public static AuthorDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    private Author buildAuthor(ResultSet resultSet) {
        return Author.builder()
                .id(resultSet.getObject("id", Long.class))
                .firstname(resultSet.getObject("firstname", String.class))
                .lastname(resultSet.getObject("lastname", String.class))
                .patronymic(resultSet.getObject("patronymic", String.class))
                .birthday(resultSet.getObject("birthday", Date.class).toLocalDate())
                .about(resultSet.getObject("about", String.class))
                .build();
    }
}
