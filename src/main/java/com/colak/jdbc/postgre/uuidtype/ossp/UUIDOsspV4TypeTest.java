package com.colak.jdbc.postgre.uuidtype.ossp;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * This test reads an uuid-ossp v4 with Jdbc
 */
@Slf4j
public class UUIDOsspV4TypeTest {

    private static final String TABLE_NAME = "uuid_osspv4_table";

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
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (varchar_column) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            String value = "value1";

            preparedStatement.setString(1, value);

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
            // UUID: 5b25017a-e370-4a6b-a9a5-e1051733fafd
            // Java can read uuid_generate_v4()
            log.info("UUID: " + uuid);
        }
    }

}
