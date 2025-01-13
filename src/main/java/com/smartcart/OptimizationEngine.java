package com.smartcart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class OptimizationEngine {

    private Connection connection;

    // Κατασκευαστής που δέχεται το Connection
    public OptimizationEngine(Connection connection) {
        this.connection = connection;
    }

    // Μέθοδος για εύρεση του καλύτερου σούπερ μάρκετ με βάση τα επιλεγμένα προϊόντα
    public String fetchBestSupermarket(List<String> products) {
        String query = """
                    SELECT
                        s.name AS supermarket_name,
                        SUM(pr.price) AS total_price
                    FROM Prices pr
                    JOIN Products p ON pr.product_id = p.product_id
                    JOIN Supermarkets s ON pr.supermarket_id = s.id
                    WHERE p.product_name IN (%s)
                    GROUP BY s.id
                    ORDER BY total_price ASC
                    LIMIT 1; 
                """;

        // Δημιουργία placeholders για τα προϊόντα
        String placeholders = String.join(",", Collections.nCopies(products.size(), "?"));
        query = String.format(query, placeholders);

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            // Δέσμευση τιμών στα placeholders
            for (int i = 0; i < products.size(); i++) {
                statement.setString(i + 1, products.get(i));
            }

            // Εκτέλεση query
            ResultSet resultSet = statement.executeQuery();

            // Επιστροφή αποτελέσματος
            if (resultSet.next()) {
                String supermarketName = resultSet.getString("supermarket_name");
                double totalPrice = resultSet.getDouble("total_price");
                return "The best supermarket is: " + supermarketName + " with total price: " + totalPrice + "€.";
            } else {
                return "No data available for the selected products.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error while fetching data.";
        }
    }
}