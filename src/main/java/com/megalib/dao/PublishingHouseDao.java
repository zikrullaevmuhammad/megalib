package com.megalib.dao;

import com.megalib.entity.PublishingHouse;
import com.megalib.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PublishingHouseDao implements Dao<Long, PublishingHouse> {

    private static final PublishingHouseDao INSTANCE = new PublishingHouseDao();

    private static final String FIND_ALL = """
            SELECT id, title, year_of_foundation, description
            FROM publishing_houses
            """;

    private static final String FIND_BY_ID = FIND_ALL + "WHERE id = ?";

    @Override
    @SneakyThrows
    public List<PublishingHouse> findAll() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL)) {
            var resultSet = preparedStatement.executeQuery();
            List<PublishingHouse> publishingHouses = new ArrayList<>();

            while (resultSet.next()) {
                publishingHouses.add(buildPublishingHouse(resultSet));
            }

            return publishingHouses;
        }
    }

    @Override
    @SneakyThrows
    public Optional<PublishingHouse> findById(Long id) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            var resultSet = preparedStatement.executeQuery();
            PublishingHouse publishingHouse = null;

            if (resultSet.next()) {
                publishingHouse = buildPublishingHouse(resultSet);
            }

            return Optional.ofNullable(publishingHouse);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(PublishingHouse entity) {

    }

    @Override
    public PublishingHouse save(PublishingHouse entity) {
        return null;
    }

    public static PublishingHouseDao getInstance() {
        return INSTANCE;
    }

    private PublishingHouse buildPublishingHouse(ResultSet resultSet) throws SQLException {
        return PublishingHouse.builder()
                .id(resultSet.getObject("id", Long.class))
                .title(resultSet.getObject("title", String.class))
                .yearOfFoundation(resultSet.getObject("year_of_foundation", Integer.class))
                .description(resultSet.getObject("description", String.class))
                .build();
    }
}
