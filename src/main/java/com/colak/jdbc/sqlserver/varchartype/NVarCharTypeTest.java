package com.colak.jdbc.sqlserver.varchartype;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class NVarCharTypeTest {

    private static final String TABLE_NAME = "all_unicode_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "Pass@word";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            clearTable(connection);

            insertAsVarChar(connection);
            selectAsVarChar(connection);


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

    private static void insertAsVarChar(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (nvarcharColumn) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            String unicodeString = "Hello, 你好, مرحبا";
            preparedStatement.setNString(1, unicodeString);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectAsVarChar(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            resultSet.next();
            String value = resultSet.getNString("nvarcharColumn");

            // Hello, 你好, مرحبا
            log.info("varcharColumn: " + value);
        }
    }

}
