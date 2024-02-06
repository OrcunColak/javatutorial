package com.colak.jdbc.sqlserver.datetimetype;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
public class DateTimeTypeTest {

    private static final String TABLE_NAME = "datetime_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "Pass@word";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            clearTable(connection);

            insertAsTimeStamp(connection);
            selectAsTimeStamp(connection);


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

    private static void insertAsTimeStamp(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (datetime_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // The datetime2_column has accuracy of 3.33 milliseconds
            // So it will lose 456_789 part
            LocalDateTime localDateTime = LocalDateTime.of(2022, 2, 6, 13, 1, 5, 333_123_456);

            // Convert Instant to Timestamp
            Timestamp timestamp = Timestamp.valueOf(localDateTime);

            preparedStatement.setTimestamp(1, timestamp);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectAsTimeStamp(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            Timestamp value = resultSet.getTimestamp("datetime_column");

            // 2022-02-06 13:01:05.333
            log.info("datetime_column: " + value);
        }
    }
}
