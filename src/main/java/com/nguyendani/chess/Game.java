package com.nguyendani.chess;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.nguyendani.chess.gui.ChessGUI;
import com.nguyendani.chess.pieces.*;

public class Game {
    private ChessGUI chessGUI;

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

    // Set Board
    private void setBoard() {
        JButton[][] chessTile = chessGUI.getChessTile();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                String pieceName = initialBoard[i][j];
                
                if (!pieceName.isEmpty()) {
                    String color = (i <= 2) ? "black" : "white";
                    boolean isWhite = (i <= 2) ? false : true;

                    // Load piece image based on the piece type and color
                    ImageIcon pieceIcon = loadImageIcon(color + pieceName + ".png");
                    System.out.println(color + pieceName + ".png");
                    chessTile[i][j].setIcon(pieceIcon);
                    new Queen(true, i, j, pieceIcon);

                    Piece chessPiece;
                    switch (pieceName) {
                    case "Rook":
                        chessPiece = new Rook(isWhite, i, j);
                        break;
                    case "Knight":
                        chessPiece = new Knight(isWhite, i, j);
                        break;
                    case "Bishop":
                        chessPiece = new Bishop(isWhite, i, j);
                        break;
                    case "Queen":
                        chessPiece = new Queen(isWhite, i, j);
                        break;
                    case "King":
                        chessPiece = new King(isWhite, i, j);
                        break;
                    case "Pawn":
                        chessPiece = new Pawn(isWhite, i, j);
                        break;
                    default:
                        chessPiece = null;
                        break;
                }
                }
                else {
                    chessTile[i][j].setIcon(null);
                }
                
            }
        }
    }

    // New Game
    private void newGame() {

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

    // Main
    public static void main(String[] args) {
        new Game();
    }
}
