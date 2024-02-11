package com.colak.jdbc.mysql.chartypetest;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MySQl can write and read unicode char with preparedStatement.setString() and resultSet.getString()
 */
@Slf4j
public class CharTypeUnicodeTest {

    private static final String TABLE_NAME = "all_char_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/userdb";
        String username = "root";
        String password = "12345678";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            clearTable(connection);
            insertAsString(connection);
            selectAsString(connection);

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

    private static void insertAsString(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (char_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            String unicodeString = "م";
            preparedStatement.setString(1, unicodeString);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectAsString(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            String value = resultSet.getString("char_column");

            // "م"
            log.info("char_column: " + value);
        }
    }

    private static void insertAsObject(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (char_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            String unicodeString = "م";
            preparedStatement.setObject(1, unicodeString);

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
            Object value = resultSet.getObject("char_column");

            // "م"
            log.info("char_column: " + value);
        }
    }
}
