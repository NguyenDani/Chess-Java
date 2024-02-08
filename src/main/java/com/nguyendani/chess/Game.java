package com.nguyendani.chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.nguyendani.chess.gui.ChessGUI;
import com.nguyendani.chess.pieces.*;

public class Game {
    private ChessGUI chessGUI;
    private Piece[][] board;

    private String[][] initialBoard = {
        {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"},
        {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn"},
        {"Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook"}
    };

    // Set Board
    private void setBoard() {
        JButton[][] chessTile = chessGUI.getChessTile();
        board = new Piece[8][8];

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                chessTileActionListener(chessTile[i][j], i, j);
                String pieceName = initialBoard[i][j];
                
                if (!pieceName.isEmpty()) {
                    String color = (i <= 2) ? "black" : "white";
                    boolean isWhitePlayer = (i <= 2) ? false : true;

                    // Load piece image based on the piece type and color
                    ImageIcon pieceIcon = loadImageIcon(color + pieceName + ".png");
                    chessTile[i][j].setIcon(pieceIcon);

                    switch (pieceName) {
                    case "Rook":
                        board[i][j] = new Rook(isWhitePlayer, j, i);
                        break;
                    case "Knight":
                        board[i][j] = new Knight(isWhitePlayer, j, i);
                        break;
                    case "Bishop":
                        board[i][j] = new Bishop(isWhitePlayer, j, i);
                        break;
                    case "Queen":
                        board[i][j] = new Queen(isWhitePlayer, j, i);
                        break;
                    case "King":
                        board[i][j] = new King(isWhitePlayer, j, i);
                        break;
                    case "Pawn":
                        board[i][j] = new Pawn(isWhitePlayer, j, i);
                        break;
                    default:
                        board[i][j] = null;
                        break;
                    }
                }
                else {
                    chessTile[i][j].setIcon(null);
                }
                
            }
        }
    }

    // New Game Event Listener
    public static void newGameActionListener(JButton newGameButton) {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New Game Button Clicked");
            }   
        });
    }

    // Chess Tile Event Listener
    private void chessTileActionListener(JButton button, int row, int col) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JButton clickButton = (JButton) e.getSource();

                int clickedRow = row;
                int clickedColumn = col;
                System.out.println("Button clicked at row: " + clickedRow + ", column: " + clickedColumn);
            }
        });
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

    private void move() {
        
    }

    public Game() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create an instance of ChessGUI
        SwingUtilities.invokeLater(() -> {
            chessGUI = new ChessGUI();
            setBoard();
        });
    }

    // Main
    public static void main(String[] args) {
        new Game();
    }
}
