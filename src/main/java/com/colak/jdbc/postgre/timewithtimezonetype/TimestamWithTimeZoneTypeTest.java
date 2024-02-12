package com.colak.jdbc.postgre.timewithtimezonetype;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

@Slf4j
public class TimestamWithTimeZoneTypeTest {

    private static final String TABLE_NAME = "datetime_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            clearTable(connection);
            insertAsOffsetTime(connection);
            selectAsOffsetTime(connection);

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

    private static void insertAsOffsetTime(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (time_with_timezone_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            LocalTime localTime = LocalTime.of(13, 1, 5, 123_456_789);
            OffsetTime offsetTime = OffsetTime.of(localTime, ZoneOffset.ofHours(3));

            preparedStatement.setObject(1, offsetTime);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectAsOffsetTime(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            OffsetTime offsetTime = resultSet.getObject("time_with_timezone_column", OffsetTime.class);

            // time_with_timezone_column : 13:01:05.123456+03:00
            log.info("time_with_timezone_column : " + offsetTime);
        }
    }


}
