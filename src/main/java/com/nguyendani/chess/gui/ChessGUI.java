package com.nguyendani.chess.gui;

import com.nguyendani.chess.pieces.*;

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

        setLayout(new BorderLayout());

        createChessboard();
        createNewGame();
        
        setVisible(true);
    }

    // Chessboard Tiles
    private void createChessboard() {
        chessboardPanel = new JPanel(new GridLayout(8, 8));
        boolean isWhite = true;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton square = new JButton();
                square.setBackground(isWhite ? Color.WHITE : Color.BLACK);

                // Temporary Piece
                if(i == 5 && j == 5){
                    new Queen(true, i, j);
                }

                isWhite = !isWhite;
                chessboardPanel.add(square);
            }
            isWhite = !isWhite;
        }

        add(chessboardPanel, BorderLayout.CENTER);
    }

    // New Game Button
    private void createNewGame() {
        JPanel controlPanel = new JPanel();
        JButton newGameButton = new JButton("New Game");
        controlPanel.add(newGameButton);

        add(controlPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        SwingUtilities.invokeLater(() -> new ChessGUI());
    }
}
