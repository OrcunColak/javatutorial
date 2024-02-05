package com.colak.jdbc.sqlserver.databasemetadata;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DatabaseMetadataTest {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "Pass@word";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            DatabaseMetaData metaData = connection.getMetaData();

            // Specify the table name for which you want to retrieve metadata

            String tableName = "all_char_types_table";
            String tableSeparator = "---------- {} ----------";
            log.info(tableSeparator, tableName);
            printTable(metaData, tableName);


        } catch (Exception exception) {
            log.error("Exception ", exception);
        }
    }

    private static void printTable(DatabaseMetaData metaData, String tableName) throws SQLException {
        // Get columns metadata
        try (ResultSet resultSet = metaData.getColumns(null, null, tableName, null)) {
            while (resultSet.next()) {
                String columnName = resultSet.getString("COLUMN_NAME");
                String dataType = resultSet.getString("TYPE_NAME");
                int columnSize = resultSet.getInt("COLUMN_SIZE");
                int scale = resultSet.getInt("DECIMAL_DIGITS");

                log.info("Column Name {} ", columnName);
                log.info("Data Type: {}", dataType);
                log.info("Column Size: {}", columnSize);
                log.info("Decimal Digits: {}", scale);
                log.info("----------");
            }
        }
    }

}
