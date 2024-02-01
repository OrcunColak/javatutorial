package com.colak.jdbc.mysql.bittype;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class BitTypeTest {

    private static final String TABLE_NAME = "bit_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/userdb";
        String username = "root";
        String password = "12345678";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            clearTable(connection);

            insertOneBit(connection);
            selectOneBit(connection);

            clearTable(connection);

            insertBitString(connection);
            selectBitString(connection);

            // These did not work
            // clearTable(connection);
            //
            // insertBlob(connection);
            // selectBlob(connection);

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

    private static void insertOneBit(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (bit_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Setting the value for the bit_column
            preparedStatement.setBoolean(1, true);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectOneBit(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            resultSet.next();
            boolean bitColumnValue = resultSet.getBoolean("bit_column");

            // Do something with the retrieved values
            log.info("bit_column: " + bitColumnValue);
        }
    }

    private static void insertBitString(Connection connection) throws SQLException {
        // https://stackoverflow.com/questions/1601838/jdbc-mysql-getting-bits-into-a-bitm-1-column
        // Special (b?) syntax
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (bit_column) VALUES (b?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            // Setting the value for the bit_column
            preparedStatement.setString(1, "111000");

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectBitString(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            resultSet.next();
            String bitColumnValue = resultSet.getString("bit_column");

            // Do something with the retrieved values
            log.info("bit_column: " + bitColumnValue);
        }
    }

    private static void insertBlob(Connection connection) throws SQLException {
        // https://stackoverflow.com/questions/48207525/mysql-jdbc-inserting-true-value-to-a-bit-column-becomes-false

        String insertQuery = "INSERT INTO " + TABLE_NAME + " (bit_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            byte[] bytes = "111000".getBytes(StandardCharsets.UTF_8);
            Blob blob = connection.createBlob();
            blob.setBytes(1, bytes);
            // Setting the value for the bit_column
            preparedStatement.setBlob(1, blob);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectBlob(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            resultSet.next();
            byte[] bytes = resultSet.getBytes("bit_column");

            String string = new String(bytes, StandardCharsets.UTF_8);
            // Do something with the retrieved values
            log.info("bit_column: " + string);
        }
    }
}
