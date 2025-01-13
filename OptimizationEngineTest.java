package com.smartcart.smartcartproject;

import com.smartcart.SQLFileExecutor;
import com.smartcart.OptimizationEngine;
import org.junit.jupiter.api.Test;
import java.util.List; 
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class OptimizationEngineTest {

    // Test case to verify the best supermarket fetch functionality with valid data
    @Test
    public void testFetchBestSupermarket() {
        SQLFileExecutor sqlExecutor = new SQLFileExecutor();
        sqlExecutor.connect();
        try {
            // Set up the database with necessary data
            sqlExecutor.executeSQLFile("src/main/resources/SmartCartDB.sql");

            OptimizationEngine engine = new OptimizationEngine(sqlExecutor.getConnection());
            List<String> products = List.of("Γάλα", "Ζάχαρη");

            // Fetch the best supermarket for the products
            String result = engine.fetchBestSupermarket(products);

            assertNotNull(result);  // Ensure the result is not null
            assertTrue(result.contains("The best supermarket is"));  // Ensure the result contains expected text
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        } finally {
            sqlExecutor.close();
        }
    }

    // Test case for when no data is available for the requested products
    @Test
    public void testFetchBestSupermarketNoData() {
        SQLFileExecutor sqlExecutor = new SQLFileExecutor();
        sqlExecutor.connect();
        try {
            // Clear the database to ensure no data is available
            sqlExecutor.executeSQLFile("src/main/resources/ClearDatabase.sql");

            OptimizationEngine engine = new OptimizationEngine(sqlExecutor.getConnection());
            List<String> products = List.of("Γάλα", "Ζάχαρη");

            // Fetch the best supermarket, expecting no data
            String result = engine.fetchBestSupermarket(products);

            assertEquals("No data available for the selected products.", result);  // Expect specific error message
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        } finally {
            sqlExecutor.close();
        }
    }

    // Test case for when null products are provided
    @Test
    public void testFetchBestSupermarketWithNullProducts() {
        SQLFileExecutor sqlExecutor = new SQLFileExecutor();
        sqlExecutor.connect();
        try {
            OptimizationEngine engine = new OptimizationEngine(sqlExecutor.getConnection());

            // Fetch the best supermarket with null products, expecting a specific message
            String result = engine.fetchBestSupermarket(null);

            assertEquals("No products provided.", result);  // Expect specific error message
        } finally {
            sqlExecutor.close();
        }
    }
}
