package com.colak.jdbc.sqlserver.smalldatetimetype;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
public class SmallDateTimeTypeTest {

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
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (smalldatetime_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Create a LocalDateTime for the specified time
            // the smalldatetime_column colum is going to lose 5 seconds part.
            // Because its accuracy is 1 minute
            // 500 milliseconds
            LocalDateTime localDateTime = LocalDateTime.of(2022, 2, 6, 13, 1, 5, 500_000_000);

            // Convert LocalDateTime to Timestamp
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
            Timestamp value = resultSet.getTimestamp("smalldatetime_column");

            // 2022-02-06 13:01:00.0
            log.info("smalldatetime_column: " + value);
        }
    }
}
