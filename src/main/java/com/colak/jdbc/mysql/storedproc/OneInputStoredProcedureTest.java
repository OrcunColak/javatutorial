package com.colak.jdbc.mysql.storedproc;

import lombok.extern.slf4j.Slf4j;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@Slf4j
public class OneInputStoredProcedureTest {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/userdb";
        String username = "root";
        String password = "12345678";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             // Prepare the stored procedure call
             CallableStatement callableStatement = connection.prepareCall("{call GetEmployee(?)}")) {
            // Set the parameter for the stored procedure
            int employeeId = 1;
            callableStatement.setInt(1, employeeId);

            // Execute the stored procedure
            try (ResultSet resultSet = callableStatement.executeQuery()) {
                // Process the result set
                showTable(resultSet);
            }
        } catch (Exception exception) {
            log.error("Exception ", exception);
        }
    }

    private static void showTable(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (resultSet.next()) {
            for (int index = 1; index <= columnCount; index++) {
                log.info("{} : {}", metaData.getColumnName(index), resultSet.getObject(index));
            }
        }
    }
}
