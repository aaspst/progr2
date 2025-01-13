package com.smartcart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLFileExecutor {
    private static final String DB_URL = "jdbc:sqlite:SmartCartDB.sqlite"; // SQLite connection
    private Connection connection;

    // Open connection
    public void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
                System.out.println("Database connection established.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    // Close connection
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while closing the database connection.", e);
        }
    }

    // Execute SQL from file
    public void executeSQLFile(String filePath) throws IOException, SQLException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("File not found: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             Statement statement = connection.createStatement()) {

            String line;
            StringBuilder sqlQuery = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sqlQuery.append(line).append("\n");
                if (line.trim().endsWith(";")) { // End of SQL command
                    statement.execute(sqlQuery.toString());
                    sqlQuery.setLength(0);
                }
            }
            System.out.println("SQL file executed successfully.");

        } catch (SQLException e) {
            throw new SQLException("Error executing SQL statement: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new IOException("Error reading SQL file: " + e.getMessage(), e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
