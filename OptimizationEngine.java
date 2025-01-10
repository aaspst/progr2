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
        String query = """ """;

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