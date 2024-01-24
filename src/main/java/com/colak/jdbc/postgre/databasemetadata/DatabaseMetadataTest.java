package com.colak.jdbc.postgre.databasemetadata;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class DatabaseMetadataTest {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/db";
        String username = "postgres";
        String password = "postgres";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            DatabaseMetaData metaData = connection.getMetaData();

            // Specify the table name for which you want to retrieve metadata

            String tableName = "all_char_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "all_numeric_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "datetime_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "binary_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "boolean_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "person_mood";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "bit_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "uuid_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "inet_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "cidr_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "json_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "xml_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "geometric_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "array_types_table";
            log.info("---------- {} ----------", tableName);
            printTable(metaData, tableName);

            tableName = "ranges_table";
            log.info("---------- {} ----------", tableName);
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
