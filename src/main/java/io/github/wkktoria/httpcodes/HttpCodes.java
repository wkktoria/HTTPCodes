package io.github.wkktoria.httpcodes;

import com.formdev.flatlaf.FlatLightLaf;
import io.github.wkktoria.httpcodes.controller.ScraperController;
import io.github.wkktoria.httpcodes.model.HttpStatusCode;
import io.github.wkktoria.httpcodes.service.ScraperService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Optional;

class HttpCodes {
    private static final ScraperController scraperController = new ScraperController(new ScraperService());
    private static final HttpStatusCode initialStatusCode = new HttpStatusCode("204", "No Content");

    public static void main(String[] args) {

        if (args.length == 1) {
            final String statusCode = args[0];
            Optional<HttpStatusCode> httpStatusCode = scraperController.getHttpStatusCode(statusCode);
            httpStatusCode.ifPresent(System.out::println);

            return;
        }

        FlatLightLaf.setup();

        JFrame frame = new JFrame("HTTP Codes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(380, 380));
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(380, 380));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel codeLabel = new JLabel("Status code: " + initialStatusCode.code());
        codeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea descriptionTextArea = new JTextArea(initialStatusCode.description());
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setLineWrap(true);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JTextField codeTextField = new JTextField();
        codeTextField.setColumns(20);

        JButton searchButton = new JButton("Search");

        inputPanel.add(codeTextField);
        inputPanel.add(searchButton);

        mainPanel.add(codeLabel);
        mainPanel.add(descriptionTextArea);
        mainPanel.add(inputPanel);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);

        codeTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {

            }

            @Override
            public void keyPressed(final KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchButton.doClick();
                }
            }

            @Override
            public void keyReleased(final KeyEvent e) {

            }
        });

        searchButton.addActionListener(e -> {
            final String code = codeTextField.getText();

            if (code != null && !code.isEmpty()) {
                final Optional<HttpStatusCode> httpStatusCode = scraperController.getHttpStatusCode(code);

                if (httpStatusCode.isPresent()) {
                    codeLabel.setForeground(Color.BLACK);
                    codeLabel.setText("Status code: " + httpStatusCode.get().code());

                    descriptionTextArea.setForeground(Color.BLACK);
                    descriptionTextArea.setText(httpStatusCode.get().description());
                } else {
                    JOptionPane.showMessageDialog(frame, "Status code is invalid or there was a problem during information fetching.", "Invalid status code", JOptionPane.ERROR_MESSAGE);
                }

                codeTextField.setText("");
            }
        });
    }
}
