package io.github.wkktoria.httpcodes.gui;

import io.github.wkktoria.httpcodes.model.HttpStatusCode;
import io.github.wkktoria.httpcodes.scraper.HttpStatusCodeScraper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

public class App extends JFrame {
    private final HttpStatusCode initialStatusCode = new HttpStatusCode("204", "No Content");
    private final HttpStatusCodeScraper httpStatusCodeScraper = new HttpStatusCodeScraper();

    public App() {
        super("HTTP Codes");

    }

    public void run() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(new Dimension(480, 480));
        setResizable(false);
        setLayout(new BorderLayout());

        JLabel statusCodeLabel = new JLabel("Status code: " + initialStatusCode.code());
        statusCodeLabel.setHorizontalAlignment(JLabel.CENTER);

        JTextArea statusDescriptionTextArea = new JTextArea(initialStatusCode.description());
        statusDescriptionTextArea.setEditable(false);
        statusDescriptionTextArea.setLineWrap(true);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JTextField statusCodeTextField = new JTextField();
        statusCodeTextField.setColumns(28);

        JButton searchButton = new JButton("Search");

        statusCodeTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchButton.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        searchButton.addActionListener(e -> {
            final String code = statusCodeTextField.getText();

            if (code == null || code.isEmpty()) {
                return;
            }

            final Optional<HttpStatusCode> httpStatusCode =
                    httpStatusCodeScraper.get(code);

            if (httpStatusCode.isPresent()) {
                statusCodeLabel.setText("Status code: " + httpStatusCode.get().code());
                statusDescriptionTextArea.setText(httpStatusCode.get().description());

                statusCodeTextField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Status code is invalid or there was a problem with information fetching.",
                        "Invalid Status Code", JOptionPane.ERROR_MESSAGE);
            }
        });


        inputPanel.add(statusCodeTextField, BorderLayout.WEST);
        inputPanel.add(searchButton, BorderLayout.EAST);

        add(statusCodeLabel, BorderLayout.NORTH);
        add(statusDescriptionTextArea, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);

    }
}