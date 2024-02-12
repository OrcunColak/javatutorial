package com.colak.jdbc.postgre.timestamptype;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
public class TimeStampTypeTest {

    private static final String TABLE_NAME = "datetime_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            clearTable(connection);
            insertAsTimestamp(connection);
            selectAsTimestamp(connection);

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

    private static void insertAsTimestamp(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (timestamp_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            LocalDateTime localDateTime = LocalDateTime .of(2024,2,12,23,23,23,999999999);
            Timestamp sqlTimestamp = Timestamp.valueOf(localDateTime);
            preparedStatement.setTimestamp(1, sqlTimestamp);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectAsTimestamp(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            Timestamp value = resultSet.getTimestamp("timestamp_column");
            LocalDateTime localDateTime = value.toLocalDateTime();
            // timestamp_column: 2024-02-12T23:23:24
            log.info("timestamp_column: " + localDateTime);
        }
    }

    private static void insertAsObject(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (timestamp_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            LocalDateTime localDateTime = LocalDateTime .of(2024,2,12,23,23,23,999999999);
            preparedStatement.setObject(1, localDateTime);

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
            LocalDateTime value = resultSet.getObject("timestamp_column", LocalDateTime.class);
            // timestamp_column: 2024-02-12T23:23:24
            log.info("timestamp_column: " + value);
        }
    }

}
