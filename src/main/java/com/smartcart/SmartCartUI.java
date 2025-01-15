package com.smartcart;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SmartCartUI {

    public static void main(String[] args) {
        // Δημιουργία SQL Executor
        SQLFileExecutor sqlExecutor = new SQLFileExecutor();
        sqlExecutor.connect();

        // Εκτέλεση SQL αρχείου
        try {
            sqlExecutor.executeSQLFile("SmartCartDB.sql");
        } catch (Exception e) {
            System.out.println("Failed to execute SQL file: " + e.getMessage());
            e.printStackTrace();
            sqlExecutor.close();
            return;
        }

        // Δημιουργία παραθύρου
        JFrame frame = new JFrame("Supermarket Optimizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Δημιουργία πάνελ
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        // Δημιουργία scroll pane
        JScrollPane scrollPane = new JScrollPane(panel); // Προσθήκη μπάρας κύλισης
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane); // Αντικατάσταση του panel απευθείας στο frame

        // Ετικέτα για τα προϊόντα
        JLabel productsLabel = new JLabel("Select the products you want:");
        productsLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Κεντραρισμένος τίτλος
        panel.add(productsLabel);

        // Δημιουργία πάνελ για τα checkboxes
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new GridLayout(0, 3, 5, 5));

        String[] productOptions = {
                "Γάλα", "Γιαούρτι", "Γαλοπούλα", "Αυγά",
                "Ντομάτες 1 κιλό", "Πατάτες 1 κιλό", "Κρεμμύδια 1 κιλό", "Λάχανο 1 κιλό", "Μαρούλι 1 κιλό",
                "Μήλα 1 κιλό", "Αχλάδια 1 κιλό", "Λεμόνια 1 κιλό", "Πορτοκάλια 1 κιλό",
                "Χαρτί Υγείας", "Αποσμητικό", "Κέτσαπ", "Μουστάρδα", "Μαγιονέζα",
                "Ψωμί Τοστ", "Ψωμί Πολύσπορο Τοστ", "Ζαμπον",
                "Σαπούνι Χεριών", "Μαλακτικό Ρούχων", "Κάψουλες Πλυντηρίου",
                "Δημητριακά Βρώμης", "Δημητριακά Σοκολάτα",
                "Γάλα Εβαπορέ", "Ζάχαρη", "Καφές Χύμα", "Κάψουλες Espresso",
                "Καθαριστικό για Τζάμια", "Σπαράγγια", "Λουκάνικο Χωριάτικο", "Τυρί Τοστ",
                "Σπανάκι", "Σφολιάτα Κατεψυγμένη", "Σαλάτα Έτοιμη",
                "Αρακάς", "Φασολάκια Κατεψυγμένα", "Κρεμμύδι Φρέσκο 1 κιλό", "Φασολάκια Φρέσκα 1 κιλό",
                "Κοτόπουλο Στήθος", "Κοτόπουλο Μπούτι",
                "Βούτηρο", "Μαρμελάδα", "Μέλι", "Φρυγανιές",
                "Βρώμη", "Πλαστικά Πιάτα", "Πλαστικά Ποτήρια",
                "Μπισκότα Βανίλλια", "Μπισκότα Σοκολάτα", "Τσάι Χαμομήλι", "Πράσινο Τσάι",
                "Σολωμός Φρέσκος 1 κιλό", "Τόνος Κονσέρβα",
                "Λάδι", "Γάλα Αμυγδάλου", "Σόγια",
                "Ρύζι Μπασμάτι", "Ρύζι Jasmine", "Χαρτί Κουζίνας",
                "Χοιρινές Πανσέτες 1 κιλό", "Συκώτι 1 κιλό", "Φιλέτο Μοσχαρίσιο 1 κιλό",
                "Χοιρινά Καλαμάκια 1 κιλό", "Κεμπάπ 1 κιλό", "Καλαμάκια Κοτόπουλο 1 κιλό",
                "Γαρίδες Κατεψυγμένες", "Γαρίδες Φρέσκιες 1 κιλό", "Ψάρι Λευκο Κατεψυγμένο",
                "Σκόρδο", "Φέτα Π.Ο.Π. 1 κιλό",
                "Φρουτόκρεμα για Βρέφη", "Πάνες",
                "Οδοντόβουρτσα", "Οδοντόβουρτσα Ηλεκτρική", "Οδοντόκρεμα", "Οδοντικό Νήμα", "Στοματικό Διάλυμα",
                "Αφρός Ξυρίσματος", "Ξυραφάκι", "Αφρόλουτρο",
                "Σαμπουάν για Ίσια Μαλλιά", "Σαμπουάν για Σγουρά Μαλλιά",
                "Vetex", "Τροφή Σκύλων", "Τροφή Γατών",
                "Παξυμάδια", "Κουλουράκια", "Μπάρα Σοκολάτα", "Μπάρα Δημητριακών",
                "Οινόπνευμα", "Γάζες", "Σακούλες Σκουπιδιών", "Βαμβάκια",
                "Τσίχλες", "Καραμέλες",
                "Αλεύρι", "Καλαμπόκι"

        };

        List<JCheckBox> productCheckboxes = new ArrayList<>();
        for (String product : productOptions) {
            JCheckBox checkBox = new JCheckBox(product);
            productCheckboxes.add(checkBox);
            productsPanel.add(checkBox);
        }
        panel.add(productsPanel);

        // Ετικέτα για την τοποθεσία
        JLabel locationLabel = new JLabel("Select your location:");
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(locationLabel);

        // Dropdown menu για την τοποθεσία
        String[] locations = { "Κηφισιά", "Χαλάνδρι", "Αμπελόκηποι", "Γλυφάδα", "Μαρούσι", "Πατήσια", "Παγκράτι" };
        JComboBox<String> locationDropdown = new JComboBox<>(locations);
        locationDropdown.setMaximumSize(new Dimension(200, locationDropdown.getPreferredSize().height)); // Κέντρο μέσω διάστασης
        locationDropdown.setAlignmentX(Component.CENTER_ALIGNMENT); // Τοποθέτηση στο κέντρο
        panel.add(locationDropdown);

        // Κουμπί υποβολής
        JButton submitButton = new JButton("Find Best Supermarket");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(submitButton);

        // Πεδίο αποτελεσμάτων
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(resultArea);

        // Λειτουργικότητα κουμπιού
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ανάκτηση επιλεγμένων προϊόντων
                List<String> selectedProducts = new ArrayList<>();
                for (JCheckBox checkBox : productCheckboxes) {
                    if (checkBox.isSelected()) {
                        selectedProducts.add(checkBox.getText());
                    }
                }

                // Ανάκτηση επιλεγμένης τοποθεσίας
                String selectedLocation = (String) locationDropdown.getSelectedItem();

                // Έλεγχος αν επιλέχθηκαν προϊόντα
                if (selectedProducts.size() < 2) {
                    JOptionPane.showMessageDialog(frame, "Please select at least two products.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Αναζήτηση στο OptimizationEngine
                OptimizationEngine optimizer = new OptimizationEngine(sqlExecutor.getConnection());
                String result = optimizer.fetchBestSupermarket(selectedProducts);

                // Προσθήκη τοποθεσίας στο αποτέλεσμα
                if (result.contains("The best supermarket is")) {
                    result += " in " + selectedLocation + ".";
                }

                // Άνοιγμα νέας σελίδας για την εμφάνιση αποτελεσμάτων
                new ResultPage("Selected products: " + String.join(", ", selectedProducts) + "\n" +
                               "Selected location: " + selectedLocation + "\n" +
                               result);
            }
        });


        // Εμφάνιση παραθύρου
        frame.setVisible(true);

        // Κλείσιμο πόρων κατά την έξοδο
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                sqlExecutor.close();
            }
        });
    }
}
