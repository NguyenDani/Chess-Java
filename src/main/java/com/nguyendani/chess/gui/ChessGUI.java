package com.nguyendani.chess.gui;

import javax.swing.*;
import java.awt.*;

public class ChessGUI extends JFrame {
    private JPanel chessBoard;
    private JButton[][] chessTile;

    public ChessGUI() {
        setTitle("Chess Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        createChessboard();

        setVisible(true);
    }

    // Chessboard
    private void createChessboard() {
        chessBoard = new JPanel(new GridLayout(8, 8));
        chessTile = new JButton[8][8];
        boolean isWhite = true;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                JButton tile = new JButton();
                tile.setFocusPainted(false);

                tile.setBackground(isWhite ? Color.WHITE : Color.GRAY);

                isWhite = !isWhite;
                chessBoard.add(tile);
                chessTile[row][col] = tile;
            }
            isWhite = !isWhite;
        }
        add(chessBoard, BorderLayout.CENTER);
    }
    
    public void endGame(Boolean result) {
        JPanel endJPanel = new JPanel();
        JLabel endText = new JLabel();
        if (result == null) {
            endText.setText("Stalemate Game Over");
        } else {
            String endGameText = (result) ? "White" : "Black";
            endText.setText(endGameText + " Wins!");
        }
        endText.setFont(new Font("Arial", Font.BOLD, 25));
        endText.setForeground(Color.BLACK);
        endJPanel.add(endText);
        add(endJPanel, BorderLayout.NORTH);
    }

    public JButton[][] getChessTile() {
        return chessTile;
    }
}
