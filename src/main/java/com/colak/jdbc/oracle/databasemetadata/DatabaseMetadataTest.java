package com.colak.jdbc.oracle.databasemetadata;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class DatabaseMetadataTest {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:FREE";
        String username = "system";
        String password = "12345678";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            selectFromTable(connection, "SELECT 'Hello World!' FROM dual");

        } catch (Exception exception) {
            log.error("Exception ", exception);
        }
    }


    private static void selectFromTable(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                for (int index = 1; index <= columnCount; index++) {
                    Object object = resultSet.getObject(index);
                    log.info(object.toString());
                }
            }
        }
    }
}
