package com.smartcart;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ResultPageTest {

    private ResultPage resultPage;

    // Setup the ResultPage instance before each test
    @BeforeEach
    void setUp() {
        resultPage = new ResultPage("Test result message.");
    }

    // Test case to verify if the ResultPage instance is created successfully
    @Test
    void testResultPageCreation() {
        assertNotNull(resultPage, "ResultPage instance should be created successfully.");
    }

    // Test case to verify the title of the JFrame
    @Test
    void testFrameTitle() {
        JFrame frame = getFrameFromResultPage();
        assertEquals("SmartCart - Results", frame.getTitle(), "Frame title should be 'SmartCart - Results'");
    }

    // Test case to verify the size of the JFrame
    @Test
    void testFrameSize() {
        JFrame frame = getFrameFromResultPage();
        assertEquals(500, frame.getWidth(), "Frame width should be 500 pixels.");
        assertEquals(500, frame.getHeight(), "Frame height should be 500 pixels.");
    }

    // Test case to verify the logo label text
    @Test
    void testLogoLabel() {
        JPanel logoPanel = getLogoPanelFromResultPage();
        JLabel logoLabel = (JLabel) logoPanel.getComponent(0);
        assertEquals("SmartCart", logoLabel.getText(), "Logo label text should be 'SmartCart'");
    }

    // Test case to verify the content of the result text area
    @Test
    void testResultTextArea() {
        JTextArea textArea = getResultTextAreaFromResultPage();
        assertNotNull(textArea, "Result text area should be created.");
        assertTrue(textArea.getText().contains("Test result message."), "Text area should contain the result message.");
    }

    // Test case to verify the close button and its text
    @Test
    void testCloseButton() {
        JPanel buttonPanel = getButtonPanelFromResultPage();
        JButton closeButton = (JButton) buttonPanel.getComponent(0);
        assertNotNull(closeButton, "Close button should be present.");
        assertEquals("Close", closeButton.getText(), "Button text should be 'Close'");
    }

    // Helper method to retrieve the JFrame from ResultPage
    private JFrame getFrameFromResultPage() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(resultPage.getLogoPanel());
        return frame;
    }

    // Helper method to retrieve the logo panel from ResultPage
    private JPanel getLogoPanelFromResultPage() {
        return (JPanel) resultPage.getLogoPanel();
    }

    // Helper method to retrieve the result text area from ResultPage
    private JTextArea getResultTextAreaFromResultPage() {
        JScrollPane scrollPane = (JScrollPane) resultPage.getScrollPane();
        return (JTextArea) scrollPane.getViewport().getView();
    }

    // Helper method to retrieve the button panel from ResultPage
    private JPanel getButtonPanelFromResultPage() {
        return resultPage.getButtonPanel();
    }
}
