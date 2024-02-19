package com.colak.jdbc.postgre.uuidtype;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * This test writes and reads a Java UUID with Jdbc
 */
@Slf4j
public class UUIDTypeTest {

    private static final String TABLE_NAME = "uuid_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            clearTable(connection);

            insertAsObject(connection);
            selectAsObject(connection);

        } catch (Exception exception) {
            log.error("Exception ", exception);
        }
    }

    private static void clearTable(Connection connection) throws SQLException {
        String deleteQuery = "DELETE FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.executeUpdate();
        }
    }


    private static void insertAsObject(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (id) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            UUID uuid = UUID.randomUUID();
            log.info("UUID: " + uuid);

            preparedStatement.setObject(1, uuid);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectAsObject(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            UUID uuid = resultSet.getObject("id", UUID.class);
            // id: 23:59:59
            log.info("UUID: " + uuid);
        }
    }

}
