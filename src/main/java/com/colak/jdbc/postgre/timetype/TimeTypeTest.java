package com.colak.jdbc.postgre.timetype;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

@Slf4j
public class TimeTypeTest {

    private static final String TABLE_NAME = "datetime_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            clearTable(connection);
            insertAsTime(connection);
            selectAsTime(connection);

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

    private static void insertAsTime(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (time_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            LocalTime localTime = LocalTime.of(23, 59, 59, 999999999);
            Time sqlTime = Time.valueOf(localTime);
            preparedStatement.setTime(1, sqlTime);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectAsTime(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            Time value = resultSet.getTime("time_column");
            LocalTime localTime = value.toLocalTime();
            // time_column: 23:59:59
            log.info("time_column: " + localTime);
        }
    }

    private static void insertAsObject(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (time_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            LocalTime localTime = LocalTime.of(23, 59, 59);
            preparedStatement.setObject(1, localTime);

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
            LocalTime value = resultSet.getObject("time_column", LocalTime.class);
            // time_column: 23:59:59
            log.info("time_column: " + value);
        }
    }

}
