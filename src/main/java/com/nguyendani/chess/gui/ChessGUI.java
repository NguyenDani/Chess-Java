package com.nguyendani.chess.gui;

import com.nguyendani.chess.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessGUI extends JFrame {
    private JPanel chessboardPanel;
    private JButton[][] squares;

    public ChessGUI() {
        setTitle("Chess Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        createChessboard();
        setBoard();
        createNewGame();
        
        setVisible(true);
    }

    // Chessboard
    private void createChessboard() {
        chessboardPanel = new JPanel(new GridLayout(8, 8));
        boolean isWhite = true;
        squares = new JButton[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                JButton square = new JButton();
                // Tile Colors
                square.setBackground(isWhite ? Color.WHITE : Color.BLACK);

                isWhite = !isWhite;
                chessboardPanel.add(square);
                squares[i][j] = square;
            }
            isWhite = !isWhite;
        }

        add(chessboardPanel, BorderLayout.CENTER);
    }

    private void setBoard() {
        // Array to store piece types and colors for the initial board setup
        String[][] initialBoard = {
            {"blackRook", "blackKnight", "blackBishop", "blackQueen", "blackKing", "blackBishop", "blackKnight", "blackRook"},
            {"blackPawn", "blackPawn", "blackPawn", "blackPawn", "blackPawn", "blackPawn", "blackPawn", "blackPawn"},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"", "", "", "", "", "", "", ""},
            {"whitePawn", "whitePawn", "whitePawn", "whitePawn", "whitePawn", "whitePawn", "whitePawn", "whitePawn"},
            {"whiteRook", "whiteKnight", "whiteBishop", "whiteQueen", "whiteKing", "whiteBishop", "whiteKnight", "whiteRook"}
        };

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String pieceName = initialBoard[i][j];
                if (!pieceName.isEmpty()) {
                    // Load piece image based on the piece type and color
                    ImageIcon pieceIcon = loadImageIcon(pieceName + ".png");
                    squares[i][j].setIcon(pieceIcon);
                    new Queen(true, i, j, pieceIcon);
                }
                else {
                    squares[i][j].setIcon(null);
                }
            }
        }
    }

    // Load Image
    private ImageIcon loadImageIcon(String imageName) {
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL imageUrl = classLoader.getResource("images/" + imageName);
        if (imageUrl != null) {
            return new ImageIcon(imageUrl);
        } else {
            // Handle the case where the image is not found
            return new ImageIcon(); // Default empty icon
        }
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
