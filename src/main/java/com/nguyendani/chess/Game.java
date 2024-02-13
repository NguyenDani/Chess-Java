package com.nguyendani.chess;

import com.nguyendani.chess.gui.ChessGUI;
import com.nguyendani.chess.pieces.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Game {
    private ChessGUI chessGUI;
    private Piece[][] board;
    private JButton[][] chessTile;
    private boolean initalClick = true;
    private int storedCol, storedRow;
    private JButton storedButton;
    private boolean whiteTurn = true;

    private String[][] initialBoard = {
            { "Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook" },
            { "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "", "", "", "", "", "", "", "" },
            { "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn", "Pawn" },
            { "Rook", "Knight", "Bishop", "Queen", "King", "Bishop", "Knight", "Rook" }
    };

    // Set Board
    private void setBoard() {
        chessTile = chessGUI.getChessTile();
        board = new Piece[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
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
                            board[i][j] = new Rook(isWhitePlayer);
                            break;
                        case "Knight":
                            board[i][j] = new Knight(isWhitePlayer);
                            break;
                        case "Bishop":
                            board[i][j] = new Bishop(isWhitePlayer);
                            break;
                        case "Queen":
                            board[i][j] = new Queen(isWhitePlayer);
                            break;
                        case "King":
                            board[i][j] = new King(isWhitePlayer);
                            break;
                        case "Pawn":
                            board[i][j] = new Pawn(isWhitePlayer);
                            break;
                    }
                } else {
                    chessTile[i][j].setIcon(null);
                    board[i][j] = null;
                }

            }
        }
    }

    // Chess Tile Event Listener
    private void chessTileActionListener(JButton button, int row, int col) {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // must have first click
                if (initalClick && board[row][col] != null && board[row][col].isWhite == whiteTurn) {
                    storedCol = col;
                    storedRow = row;
                    storedButton = button;

                    button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));

                    initalClick = false;
                } else if (!initalClick) {
                    if (storedButton != button)
                        move(storedCol, storedRow, col, row);

                    storedButton.setBorder(null);
                    initalClick = true;
                }

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

    // Move Piece
    private void move(int oldCol, int oldRow, int newCol, int newRow) {
        if (board[oldRow][oldCol].isValidMove(oldCol, oldRow, newCol, newRow, board)) {
            
            // Move Piece
            board[newRow][newCol] = board[oldRow][oldCol];
            board[oldRow][oldCol] = null;
            // Move New Image
            ImageIcon oldIcon = (ImageIcon) chessTile[oldRow][oldCol].getIcon();
            chessTile[newRow][newCol].setIcon(oldIcon);
            chessTile[oldRow][oldCol].setIcon(null);

            whiteTurn = !whiteTurn;

            // Check for checkmate
            boolean isWhiteKingInCheck = isInCheck(true);
            boolean isBlackKingInCheck = isInCheck(false);

            if (isWhiteKingInCheck && isCheckmate(true)) {
                // White player is in checkmate
                // Handle game over for white
                chessGUI.endGame("Black Won");
            } else if (isBlackKingInCheck && isCheckmate(false)) {
                // Black player is in checkmate
                // Handle game over for black
                chessGUI.endGame("White Won");
            } else if (isStalemate(true) || isStalemate(false)) {
                // Stalemate
                // Handle game over for both players
                chessGUI.endGame("Stalemate Game Over");
            }
        }
    }

    // Check
    private boolean isInCheck(boolean isWhitePlayer) {
        int kingRow = -1;
        int kingCol = -1;
    
        // Find the position of the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].isWhite == isWhitePlayer) {
                    kingRow = i;
                    kingCol = j;
                    break;
                }
            }
        }
    
        // Check if any opponent's piece can attack the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].isWhite != isWhitePlayer) {
                    if (board[i][j].isValidMove(j, i, kingCol, kingRow, board)) {
                        return true;
                    }
                }
            }
        }
    
        return false;
    }

    //Checkmate
    private boolean isCheckmate(boolean isWhitePlayer) {
        // Check if the player is in check
        if (!isInCheck(isWhitePlayer))
            return false;
    
        // Try all possible moves for the player
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].isWhite == isWhitePlayer) {
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            if (board[i][j].isValidMove(j, i, newCol, newRow, board)) {
                                // Simulate moving the piece
                                Piece temp = board[newRow][newCol];
                                board[newRow][newCol] = board[i][j];
                                board[i][j] = null;
    
                                // Check if the king is still in check after the move
                                if (!isInCheck(isWhitePlayer)) {
                                    // Undo the move
                                    board[i][j] = board[newRow][newCol];
                                    board[newRow][newCol] = temp;
                                    return false;
                                }
    
                                // Undo the move
                                board[i][j] = board[newRow][newCol];
                                board[newRow][newCol] = temp;
                            }
                        }
                    }
                }
            }
        }
    
        return true;
    }

    // Stalemate
    private boolean isStalemate(boolean isWhitePlayer) {
        // Check if the player has no legal moves and their king is not in check
        if (isInCheck(isWhitePlayer))
            return false;
    
        // Try all possible moves for the player
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].isWhite == isWhitePlayer) {
                    for (int newRow = 0; newRow < 8; newRow++) {
                        for (int newCol = 0; newCol < 8; newCol++) {
                            if (board[i][j].isValidMove(j, i, newCol, newRow, board)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
    
        return true;
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
