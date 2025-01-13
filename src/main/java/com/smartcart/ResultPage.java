package com.smartcart;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Insets;

import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class ResultPage {
    private JFrame resultFrame;
    private JPanel logoPanel;
    private JScrollPane scrollPane;
    private JPanel buttonPanel;

    public ResultPage(String result) {
        // Create the result frame
        resultFrame = new JFrame("SmartCart - Results");
        resultFrame.setSize(500, 500);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setLayout(new BorderLayout());

        // Load custom font
        Font customFont = loadCustomFont("src/main/resources/fonts/Lobster-Regular.ttf", 28);
        if (customFont == null) {
            customFont = new Font("Verdana", Font.BOLD, 28); // Fallback font
        }

        // Create a logo panel with dark green background
        logoPanel = new JPanel();
        logoPanel.setLayout(new BorderLayout());
        logoPanel.setBackground(new Color(19, 59, 27)); // Dark Forest Green

        JLabel logoLabel = new JLabel("SmartCart", JLabel.CENTER);
        logoLabel.setFont(customFont);
        logoLabel.setForeground(new Color(255, 223, 0)); // Golden Yellow text for contrast

        logoPanel.add(logoLabel, BorderLayout.CENTER);

        // Add an image to the top of the result frame
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        try {
            ImageIcon supermarketImage = new ImageIcon("src/main/resources/images/supermarket.jpg");
            Image scaledImage = supermarketImage.getImage().getScaledInstance(450, 200, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            System.err.println("Image not found or failed to load: " + e.getMessage());
        }

        logoPanel.add(imageLabel, BorderLayout.SOUTH);

        // Create the result text area with updated styling
        JTextArea resultTextArea = new JTextArea(result);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setFont(new Font("Courier New", Font.BOLD, 16)); // Bold text for clarity
        resultTextArea.setMargin(new Insets(10, 10, 10, 10));
        resultTextArea.setBackground(new Color(233, 240, 215)); // Soft Light Green
        resultTextArea.setForeground(new Color(0, 43, 0)); // Very Dark Green text

        // Add result text area to a scroll pane
        scrollPane = new JScrollPane(resultTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create a bottom button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(19, 59, 27)); // Same dark green as the logo panel

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("SansSerif", Font.BOLD, 14)); // Bold text
        closeButton.setBackground(new Color(233, 240, 215)); // Same soft light green as the result background
        closeButton.setForeground(new Color(0, 43, 0)); // Very dark green text for readability
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> resultFrame.dispose());

        buttonPanel.add(closeButton);

        // Add components to the frame
        resultFrame.add(logoPanel, BorderLayout.NORTH);
        resultFrame.add(scrollPane, BorderLayout.CENTER);
        resultFrame.add(buttonPanel, BorderLayout.SOUTH);

        // Center the frame
        resultFrame.setLocationRelativeTo(null);

        // Display the frame
        resultFrame.setVisible(true);
    }

    // Add getters for the components
    public JPanel getLogoPanel() {
        return logoPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    // Load custom font method remains unchanged
    private Font loadCustomFont(String path, float size) {
        try {
            File fontFile = new File(path);
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            return font.deriveFont(size);
        } catch (FontFormatException | IOException e) {
            System.err.println("Failed to load custom font: " + e.getMessage());
            return null;
        }
    }
}
