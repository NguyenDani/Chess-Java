package com.nguyendani.chess;

import com.nguyendani.chess.gui.ChessGUI;
import com.nguyendani.chess.pieces.*;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Game {
    private static Game instance;

    private ChessGUI chessGUI;
    private Piece[][] board;
    private JButton[][] chessTile;
    private boolean initalClick = true;
    private int storedCol, storedRow;
    private JButton storedButton;
    private boolean whiteTurn = true;
    private boolean isAnyCheck;
    private int[] kingLoc;

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

                    if (board[storedRow][storedCol] instanceof King) {
                        if (isInCheck(whiteTurn)) {
                            storedButton.setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                        } else {
                            storedButton.setBorder(null);
                        }
                    } else {
                        storedButton.setBorder(null);
                    }
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
            return new ImageIcon();
        }
    }

    // Move Piece
    private void move(int oldCol, int oldRow, int newCol, int newRow) {
        // Check if castling is requested
        if (board[oldRow][oldCol] instanceof King
                && ((King) board[oldRow][oldCol]).isValidCastling(oldCol, oldRow, newCol, newRow, board)) {
            if (ownCheck(oldCol, oldRow, newCol, newRow))
                return;

            // Perform castling
            int direction = (newCol > oldCol) ? -1 : 1;
            int rookCol = (newCol > oldCol) ? 7 : 0;

            // Move the king
            board[newRow][newCol] = board[oldRow][oldCol];
            board[oldRow][oldCol] = null;
            ((King) board[newRow][newCol]).updateFirstMove();
            chessTile[newRow][newCol].setIcon(chessTile[oldRow][oldCol].getIcon());
            chessTile[oldRow][oldCol].setIcon(null);

            // Move the rook
            board[oldRow][newCol + direction] = board[oldRow][rookCol];
            board[oldRow][rookCol] = null;
            ((Rook) board[oldRow][newCol + direction]).updateFirstMove();
            chessTile[oldRow][newCol + direction].setIcon(chessTile[oldRow][rookCol].getIcon());
            chessTile[oldRow][rookCol].setIcon(null);

            // Switch player turn
            whiteTurn = !whiteTurn;
            return;
        }

        else if (board[oldRow][oldCol].isValidMove(oldCol, oldRow, newCol, newRow, board)) {
            if (isAnyCheck) {
                // Only moves to get King out of check
                if (!uncheckAfterMove(oldCol, oldRow, newCol, newRow))
                    return;
                else
                    chessTile[kingLoc[0]][kingLoc[1]].setBorder(null);
            }

            // Prevent own check
            if (ownCheck(oldCol, oldRow, newCol, newRow))
                return;

            // Move Piece and icon
            board[newRow][newCol] = board[oldRow][oldCol];
            board[oldRow][oldCol] = null;
            ImageIcon oldIcon = (ImageIcon) chessTile[oldRow][oldCol].getIcon();
            chessTile[newRow][newCol].setIcon(oldIcon);
            chessTile[oldRow][oldCol].setIcon(null);

            if (board[newRow][newCol] instanceof King) {
                ((King) board[newRow][newCol]).updateFirstMove();
            }
            if (board[newRow][newCol] instanceof Pawn) {
                ((Pawn) board[newRow][newCol]).updateFirstMove();
            }
            if (board[newRow][newCol] instanceof Rook) {
                ((Rook) board[newRow][newCol]).updateFirstMove();
            }

            // Check if pawn is available for promotion
            if (board[newRow][newCol] instanceof Pawn && (newRow == 0 || newRow == 7))
                promotePawn(newRow, newCol, whiteTurn);

            // switch player turn
            whiteTurn = !whiteTurn;

            // Check for Game state (Check / Checkmate / Stalemate)
            isAnyCheck = (whiteTurn) ? isInCheck(true) : isInCheck(false);

            if (isAnyCheck) {
                kingLoc = getKing(whiteTurn);
                chessTile[kingLoc[0]][kingLoc[1]].setBorder(BorderFactory.createLineBorder(Color.RED, 5));
                if (isCheckmate(whiteTurn))
                    if (whiteTurn)
                        chessGUI.endGame(false);
                    else
                        chessGUI.endGame(true);
            } else {
                if ((whiteTurn) ? isStalemate(true) : isStalemate(false)) {
                    chessGUI.endGame(null);
                }
            }
        }
    }

    // Promote pawn
    private void promotePawn(int x, int y, boolean color) {
        // Create promotion GUI
        SwingUtilities.invokeLater(() -> {
            // JFrame promotionFrame = new JFrame("Pawn Promotion");
            JPanel promotionPanel = new JPanel(new GridLayout(1, 4));

            String[] options = { "Rook", "Knight", "Bishop", "Queen" };
            for (String option : options) {
                JButton button = new JButton(option);
                button.addActionListener(e -> {
                    switch (option) {
                        case "Rook":
                            board[x][y] = new Rook(color);
                            break;
                        case "Knight":
                            board[x][y] = new Knight(color);
                            break;
                        case "Bishop":
                            board[x][y] = new Bishop(color);
                            break;
                        case "Queen":
                            board[x][y] = new Queen(color);
                            break;
                    }
                    String colorString = (color) ? "white" : "black";
                    ImageIcon pieceIcon = loadImageIcon(colorString + option + ".png");
                    chessTile[x][y].setIcon(pieceIcon);

                    // Close promotion GUI
                    SwingUtilities.getWindowAncestor(promotionPanel).dispose();
                });
                promotionPanel.add(button);
            }

            // Create modal dialog
            JDialog promotionDialog = new JDialog((JFrame) null, "Pawn Promotion", true);
            promotionDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            promotionDialog.getContentPane().add(promotionPanel);
            promotionDialog.pack();
            promotionDialog.setLocationRelativeTo(null); // Center the dialog on the screen
            promotionDialog.setVisible(true);
        });

    }

    // Get location of King
    private int[] getKing(boolean isWhitePlayer) {
        int[] location = { -1, -1 };
        // Find the position of the king
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] instanceof King && board[i][j].isWhite == isWhitePlayer) {
                    location[0] = i;
                    location[1] = j;
                    break;
                }
            }
        }
        return location;
    }

    // Uncheck
    private boolean uncheckAfterMove(int oldCol, int oldRow, int newCol, int newRow) {
        // Simulate the move
        Piece temp = board[newRow][newCol];
        board[newRow][newCol] = board[oldRow][oldCol];
        board[oldRow][oldCol] = null;

        // Check if the king is in check after the move
        boolean kingInCheck = isInCheck(board[newRow][newCol].isWhite);
        chessTile[newRow][newCol].setBorder(null);

        // Undo the move
        board[oldRow][oldCol] = board[newRow][newCol];
        board[newRow][newCol] = temp;

        // If king is in check after the move, return false
        return !kingInCheck;
    }

    // Simulate move
    private boolean ownCheck(int oldCol, int oldRow, int newCol, int newRow) {
        // Make sure not check own king
        // Simulate the move
        Piece temp = board[newRow][newCol];
        board[newRow][newCol] = board[oldRow][oldCol];
        board[oldRow][oldCol] = null;

        // Check if the king is in check after the move
        boolean kingInCheck = isInCheck(board[newRow][newCol].isWhite);

        // Undo the move
        board[oldRow][oldCol] = board[newRow][newCol];
        board[newRow][newCol] = temp;

        // If king is in check after the move, prevent the move
        return kingInCheck;
    }

    // Check
    public boolean isInCheck(boolean isWhitePlayer) {
        // Find the position of the king
        kingLoc = getKing(isWhitePlayer);

        // Check if any opponent's piece can attack the king
        boolean kingInCheck = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null && board[i][j].isWhite != isWhitePlayer) {
                    if (board[i][j].isValidMove(j, i, kingLoc[1], kingLoc[0], board)) {
                        kingInCheck = true;
                        break;
                    }
                }
            }
            if (kingInCheck)
                break;
        }

        return kingInCheck;
    }

    // Checkmate
    private boolean isCheckmate(boolean isWhitePlayer) {
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
                                if (newRow != i && newCol != j)
                                    chessTile[newRow][newCol].setBorder(null);
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

    private Game() {
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

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // Main
    public static void main(String[] args) {
        Game.getInstance();
    }
}
