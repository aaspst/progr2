package com.smartcart.smartcartproject;

import com.smartcart.SQLFileExecutor;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SQLFileExecutorTest {

    // Test case to verify if the connection to the database is successful
    @Test
    public void testConnection() {
        SQLFileExecutor sqlExecutor = new SQLFileExecutor();
        sqlExecutor.connect();
        try {
            Connection connection = sqlExecutor.getConnection();
            // Assert that the connection is not null and is open
            assertNotNull(connection, "Connection should not be null.");
            assertFalse(connection.isClosed(), "Connection should be open.");
        } catch (SQLException e) {
            fail("Connection test failed: " + e.getMessage());
        } finally {
            sqlExecutor.close();  // Ensure to close the connection after the test
        }
    }

    // Test case to verify if an SQL file can be executed successfully
    @Test
    public void testExecuteSQLFile() {
        SQLFileExecutor sqlExecutor = new SQLFileExecutor();
        sqlExecutor.connect();
        try {
            // Execute the SQL file to initialize the database schema
            sqlExecutor.executeSQLFile("src/main/resources/SmartCartDB.sql");

            // Check if the 'Products' table exists in the database
            try (Connection connection = sqlExecutor.getConnection();
                 Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(
                    "SELECT name FROM sqlite_master WHERE type='table' AND name='Products';"
                );
                // Assert that the 'Products' table exists
                assertTrue(resultSet.next(), "Table 'Products' should exist in the database.");
            }
        } catch (Exception e) {
            fail("Failed to execute SQL file or verify schema: " + e.getMessage());
        } finally {
            sqlExecutor.close();  // Ensure to close the connection after the test
        }
    }

    // Test case to verify if executing a non-existent SQL file results in an exception
    @Test
    public void testExecuteNonExistentSQLFile() {
        SQLFileExecutor sqlExecutor = new SQLFileExecutor();
        sqlExecutor.connect();
        try {
            // Assert that executing a non-existent file throws an exception
            assertThrows(Exception.class, () -> sqlExecutor.executeSQLFile("nonexistent.sql"),
                "Executing a non-existent SQL file should throw an exception.");
        } finally {
            sqlExecutor.close();  // Ensure to close the connection after the test
        }
    }
}
