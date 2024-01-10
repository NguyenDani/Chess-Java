package com.nguyendani.chess.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI extends JFrame {
    private JPanel chessboardPanel;

    public ChessGUI() {
        setTitle("Chess Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createChessboard();
        createControls();

        setLayout(new BorderLayout());
        add(chessboardPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void createChessboard() {
        chessboardPanel = new JPanel(new GridLayout(8, 8));
        boolean isWhite = false;

        for (int i = 0; i < 8; i++) {
            isWhite = !isWhite; // flip color for each row
            for (int j = 0; j < 8; j++) {
                JButton square = new JButton();
                square.setBackground(isWhite ? Color.WHITE : Color.BLACK);
                isWhite = !isWhite; // flip color for the next square
                square.addActionListener(new ChessboardButtonListener());
                chessboardPanel.add(square);
            }
        }
    }

    private void createControls() {
        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new NewGameButtonListener());
        controlPanel.add(newGameButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    private class ChessboardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle chessboard button click
            JButton clickedButton = (JButton) e.getSource();
            // Add your logic for handling the button click (e.g., move pieces)
            // This is where you would implement the chess game logic.
        }
    }

    private class NewGameButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle new game button click
            // Add your logic for starting a new game
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChessGUI());
    }
}
