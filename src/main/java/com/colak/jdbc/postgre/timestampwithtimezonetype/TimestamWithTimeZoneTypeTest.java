package com.colak.jdbc.postgre.timestampwithtimezonetype;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Slf4j
public class TimestamWithTimeZoneTypeTest {

    private static final String TABLE_NAME = "datetime_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            clearTable(connection);
            insertAsOffsetDateTime(connection);
            selectAsOffsetDateTime(connection);

            // clearTable(connection);
            // insertAsZonedDateTime(connection);
            // selectAsZonedDateTime(connection);


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

    private static void insertAsOffsetDateTime(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (timestamptz_column1) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Use Zone offset (UTC+3)
            OffsetDateTime offsetDateTime = OffsetDateTime.of(2022, 2, 6, 13, 1, 5, 123_456_789,
                    ZoneOffset.ofHours(3));


            preparedStatement.setObject(1, offsetDateTime);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectAsOffsetDateTime(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            OffsetDateTime offsetDateTime = resultSet.getObject("timestamptz_column1", OffsetDateTime.class);

            // See https://stackoverflow.com/questions/54189839/how-to-store-offsetdatetime-to-postgresql-timestamp-with-time-zone-column
            // Postgres stores everything in UTC and does not keep original time zone. So you always get the value in UTC.
            // offsetDateTime : 2022-02-06T10:01:05.123457Z
            log.info("offsetDateTime : " + offsetDateTime);
        }
    }

    // This does not work
    private static void insertAsZonedDateTime(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (timestamptz_column1) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Use Zone offset (UTC+3)
            ZonedDateTime zonedDateTime = ZonedDateTime.of(2022, 2, 6, 13, 1, 5, 123_456_789,
                    ZoneOffset.ofHours(3));


            // The conversion from UNKNOWN to UNKNOWN is unsupported.
            preparedStatement.setObject(1, zonedDateTime);

            // The conversion from UNKNOWN to TIMESTAMP_WITH_TIMEZONE is unsupported.
            preparedStatement.setObject(1, zonedDateTime, JDBCType.TIMESTAMP_WITH_TIMEZONE);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    // And this does not work
    private static void selectAsZonedDateTime(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            // The conversion to class java.time.ZonedDateTime is unsupported.
            ZonedDateTime zonedDateTime = resultSet.getObject("timestamptz_column1", ZonedDateTime.class);

            // zonedDateTime : 2022-02-06T13:01:05.123+03:00
            log.info("zonedDateTime : " + zonedDateTime);
        }
    }

}
