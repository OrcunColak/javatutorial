package com.colak.jdbc.statement;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

@Slf4j
public class StBatchInsertTest {

    public static void main(String[] args) throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            dropTable(connection);
            createTable(connection);

            for (int index = 0; index < 100; index++) {
                String sql = String.format("INSERT INTO STEMPLOYEE(ID, NAME) VALUES(%d, '%s')", index, index);
                statement.addBatch(sql);

            }
            int[] ints = statement.executeBatch();
            boolean allMatch = Arrays.stream(ints).allMatch(i -> i == 1);
            log.info("executeBatch result : {}", allMatch);

        }
    }

    private static void dropTable(Connection connection) throws SQLException {
        String sql = "DROP TABLE STEMPLOYEE";
        try (Statement statement = connection.createStatement()) {
            boolean result = statement.execute(sql);
            log.info("createTable result : {}", result);
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS STEMPLOYEE (
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