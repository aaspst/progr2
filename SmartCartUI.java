import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SmartCartUI {
    public static void main(String[] args) {
        // Δημιουργία παραθύρου
        JFrame frame = new JFrame("Supermarket Optimizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Δημιουργία πάνελ
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        // Ετικέτα για τα προϊόντα
        JLabel productsLabel = new JLabel("Select the products you want:");
        productsLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Κεντραρισμένος τίτλος
        panel.add(productsLabel);

        // Δημιουργία πάνελ για τα checkboxes με διάταξη τύπου "bunch με 3 στήλες"
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; // Στήλη
        gbc.gridy = 0; // Γραμμή
        gbc.insets = new Insets(5, 5, 5, 5); // Αποστάσεις μεταξύ στοιχείων

        // Δημιουργία checkboxes για προϊόντα

        // TODO add boxes for every product
        String[] productOptions = { "Γάλα", "Χαρτί Υγείας", "Αποσμητικό", "Κέτσαπ", "Μουστάρδα",
                "Μαγιονέζα", "Ψωμί Τοστ", "Ψωμί Πολύσπορο Τοστ", "Ζαμπόν", "Σαπούνι Χεριών",
                "Μαλακτικό Ρούχων", "Κάψουλες Πλυντηρίου", "Δημητριακά Βρώμης", "Δημητριακα Σοκολάτα",
                "Γάλα Εβαπορέ", "Ζάχαρη", "Κάψουλες Espresso", "Καθαριστικό για Τζάμια", "Μαρμελάδα",
                "Μέλι", "Φρυγανιές", "Βρώμη", "Πλαστικά Πιάτα", "Πλαστικά Ποτήρια" };
        List<JCheckBox> productCheckboxes = new ArrayList<>();
        for (int i = 0; i < productOptions.length; i++) {
            JCheckBox checkBox = new JCheckBox(productOptions[i]);
            productCheckboxes.add(checkBox);

            // Υπολογισμός θέσης στοιχείου
            gbc.gridx = i % 3; // Εναλλαγή μεταξύ 0, 1, 2 για 3 στήλες
            gbc.gridy = i / 3; // Μετακίνηση στη νέα γραμμή μετά από κάθε 3 στοιχεία

            productsPanel.add(checkBox, gbc); // Προσθήκη στο GridBagLayout
        }
        panel.add(productsPanel);

        // Ετικέτα για την τοποθεσία
        JLabel locationLabel = new JLabel("Select your location:");
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Κεντραρισμένος τίτλος
        panel.add(locationLabel);

        // Dropdown menu για την τοποθεσία

        // TODO add the right districts
        String[] locations = { "Κηφισιά", "Χαλάνδρι", "Αμπελόκηποι", "Γλυφάδα", "Μαρούσι", "Πατήσια", "Παγκράτι" };
        JComboBox<String> locationDropdown = new JComboBox<>(locations);
        panel.add(locationDropdown);

        // Κουμπί υποβολής
        JButton submitButton = new JButton("Find Best Supermarket");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Κεντραρισμένο κουμπί
        panel.add(submitButton);

        // Πεδίο αποτελεσμάτων
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setAlignmentX(Component.CENTER_ALIGNMENT); // Κεντραρισμένο πεδίο
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
                if (selectedProducts.isEmpty()) {
                    resultArea.setText("Please select at least one product.");
                    return;
                }

                // Σύνδεση με τη βάση δεδομένων και εύρεση αποτελεσμάτων
                DatabaseHandler dbHandler = new DatabaseHandler();
                String result = dbHandler.fetchBestSupermarket(selectedProducts);

                // Εμφάνιση αποτελεσμάτων
                resultArea.setText("Selected products: " + String.join(", ", selectedProducts) + "\n" +
                        "Selected location: " + selectedLocation + "\n" +
                        result);
            }
        });

        // Εμφάνιση παραθύρου
        frame.setVisible(true);
    }
}
