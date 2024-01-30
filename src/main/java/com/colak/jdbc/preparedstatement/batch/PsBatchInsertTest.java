package com.colak.jdbc.preparedstatement.batch;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

@Slf4j
public class PsBatchInsertTest {

    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db";
        String username = "postgres";
        String password = "postgres";
        String insertEmployeeSQL = "INSERT INTO PSEMPLOYEE(ID, NAME) VALUES (?,?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeeSQL)) {

            createTable(connection);

            for (int index = 0; index < 100; index++) {
                preparedStatement.setInt(1, index);
                preparedStatement.setString(2, String.valueOf(index));
                preparedStatement.addBatch();
                preparedStatement.clearParameters();
            }
            int[] ints = preparedStatement.executeBatch();
            boolean allMatch = Arrays.stream(ints).allMatch(i -> i == 1);
            log.info("executeBatch result : {}", allMatch);

        }
    }

    private static void dropTable(Connection connection) throws SQLException {
        String sql = "DROP TABLE PSEMPLOYEE";
        try (Statement statement = connection.createStatement()) {
            boolean result = statement.execute(sql);
            log.info("createTable result : {}", result);
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS PSEMPLOYEE (
                    ID SERIAL PRIMARY KEY,
                    NAME VARCHAR(255)
                );
                """;
        try (Statement statement = connection.createStatement()) {
            boolean result = statement.execute(sql);
            log.info("createTable result : {}", result);
        }
    }
}