CREATE TABLE supermarkets (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL
);
 
INSERT INTO supermarkets (name) VALUES
('Σκλαβενίτης'),
('Βασιλόπουλος'),
('Κρητικός'),
('Μπαλάσκας'),
('Μασούτης');
 
CREATE TABLE Products (
    product_id INTEGER PRIMARY KEY AUTOINCREMENT,
    product_name TEXT NOT NULL
);
 
INSERT INTO Products (product_name) VALUES
('Γάλα'),
('Γιαούρτι'),
('Γαλοπούλα'),
('Αυγά'),
('Ντομάτες 1 κιλό'),
('Πατάτες 1 κιλό'),
('Κρεμμύδια 1 κιλό'),
('Λάχανο 1 κιλό'),
('Μαρούλι 1 κιλό'),
('Μήλα 1 κιλό'),
('Αχλάδια 1 κιλό'),
('Λεμόνια 1 κιλό'),
('Πορτοκάλια 1 κιλό'),
('Χαρτί Υγείας'),
('Αποσμητικό'),
('Κέτσαπ'),
('Μουστάρδα'),
('Μαγιονέζα'),
('Ψωμί Τοστ'),
('Ψωμί Πολύσπορο Τοστ'),
('Ζαμπον'),
('Σαπούνι Χεριών'),
('Μαλακτικό Ρούχων'),
('Κάψουλες Πλυντηρίου'),
('Δημητριακά Βρώμης'),
('Δημητριακά Σοκολάτα'),
('Γάλα Εβαπορέ'),
('Ζάχαρη'),
('Καφές Χύμα'),
('Κάψουλες Espresso'),
('Καθαριστικό για Τζάμια'),
('Σπαράγγια'),
('Λουκάνικο Χωριάτικο'),
('Τυρί Τοστ'),
('Σπανάκι'),
('Σφολιάτα Κατεψυγμένη'),
('Σαλάτα Έτοιμη'),
('Αρακάς'),
('Φασολάκια Κατεψυγμένα'),
('Κρεμμύδι Φρέσκο 1 κιλό'),
('Φασολάκια Φρέσκα 1 κιλό'),
('Κοτόπουλο Στήθος'),
('Κοτόπουλο Μπούτι'),
('Βούτηρο'),
('Μαρμελάδα'),
('Μέλι'),
('Φρυγανιές'),
('Βρώμη'),
('Πλαστικά Πιάτα'),
('Πλαστικά Ποτήρια'),
('Μπισκότα Βανίλλια'),
('Μπισκότα Σοκολάτα'),
('Τσάι Χαμομήλι'),
('Πράσινο Τσάι'),
('Σολωμός Φρέσκος 1 κιλό'),
('Τόνος Κονσέρβα'),
('Λάδι'),
('Γάλα Αμυγδάλου'),
('Σόγια'),
('Ρύζι Μπασμάτι'),
('Ρύζι Jasmine'),
('Χαρτί Κουζίνας'),
('Χοιρινές Πανσέτες 1 κιλό'),
('Συκώτι 1 κιλό'),
('Φιλέτο Μοσχαρίσιο 1 κιλό'),
('Χοιρινά Καλαμάκια 1 κιλό'),
('Κεμπάπ 1 κιλό'),
('Καλαμάκια Κοτόπουλο 1 κιλό'),
('Γαρίδες Κατεψυγμένες'),
('Γαρίδες Φρέσκιες 1 κιλό'),
('Ψάρι Λευκο Κατεψυγμένο'),
('Σκόρδο'),
('Φέτα Π.Ο.Π. 1 κιλό'),
('Φρουτόκρεμα για Βρέφη'),
('Πάνες'),
('Οδοντόβουρτσα'),
('Οδοντόβουρτσα Ηλεκτρική'),
('Οδοντόκρεμα'),
('Οδοντικό Νήμα'),
('Στοματικό Διάλυμα'),
('Αφρός Ξυρίσματος'),
('Ξυραφάκι'),
('Αφρόλουτρο'),
('Σαμπουάν για Ίσια Μαλλιά'),
('Σαμπουάν για Σγουρά Μαλλιά'),
('Vetex'),
('Τροφή Σκύλων'),
('Τροφή Γατών'),
('Παξυμάδια'),
('Κουλουράκια'),
('Μπάρα Σοκολάτα'),
('Μπάρα Δημητριακών'),
('Οινόπνευμα'),
('Γάζες'),
('Σακούλες Σκουπιδιών'),
('Βαμβάκια'),
('Τσίχλες'),
('Καραμέλες'),
('Αλεύρι'),
('Καλαμπόκι');
 
CREATE TABLE Prices (
    product_id INTEGER NOT NULL,
    supermarket_id INTEGER NOT NULL,
    price REAL NOT NULL,
    PRIMARY KEY (product_id, supermarket_id),
    FOREIGN KEY (product_id) REFERENCES Products(product_id),
    FOREIGN KEY (supermarket_id) REFERENCES supermarkets(id)
);