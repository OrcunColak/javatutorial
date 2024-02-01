package com.colak.jdbc.mysql.bigintunsignedtype;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class BigIntUnsignedTypeTest {

    private static final String TABLE_NAME = "integer_types_table";

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/userdb";
        String username = "root";
        String password = "12345678";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            clearTable(connection);

            insertMaxAsBigDecimal(connection);
            selectMaxAsBigDecimal(connection);

            // Does not work
            // insertMaxAsObject(connection);
            selectMaxAsObject(connection);

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

    /**
     * This does not work. Error is "Data truncation: Out of range value for column 'bigint_unsigned' at row 1"
     */
    private static void insertMaxAsObject(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (bigint_unsigned) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            //
            BigInteger bigInteger = new BigInteger("18446744073709551615");
            preparedStatement.setObject(1, bigInteger);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }


    private static void insertMaxAsBigDecimal(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (bigint_unsigned) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            BigDecimal bigDecimal = new BigDecimal("18446744073709551615");
            preparedStatement.setBigDecimal(1, bigDecimal);

            // Executing the INSERT operation
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                log.info("Insert successful!");
            } else {
                log.info("Insert failed.");
            }
        }
    }

    private static void selectMaxAsBigDecimal(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            resultSet.next();
            BigDecimal bigDecimal = resultSet.getBigDecimal("bigint_unsigned");

            // Do something with the retrieved values
            log.info("bigint_unsigned: " + bigDecimal);
        }
    }

    /**
     * This works but probably not standard behavior
     */
    private static void selectMaxAsObject(Connection connection) throws SQLException {
        String insertQuery = "SELECT * FROM " + TABLE_NAME;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
             ResultSet resultSet = preparedStatement.executeQuery();) {

            resultSet.next();
            BigInteger bigInteger = (BigInteger) resultSet.getObject("bigint_unsigned");

            // Do something with the retrieved values
            log.info("bigint_unsigned: " + bigInteger);
        }
    }

}
