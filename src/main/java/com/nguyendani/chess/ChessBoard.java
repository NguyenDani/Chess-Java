package com.nguyendani.chess;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        this.board = new Piece[8][8];
        // Initalize the board with pieces in their starting positions
        // Example: board[1][0] = new Pawn(true, 1, 0);
        // White pawn at (1,0)
    }

    public void displayBoard(){
        // Update the Swing GUI or console to reflect the current state of the board
    }

    public boolean movePiece (Player player, int startX, int startY, int endX, int endY) {
        // Move a piece on the board
        // Check for the validity of the move
        // Check for checkmate, stalemate, etc.
        // Update the board state
        if(board[startX][startY] != null && board[startX][startY].isValidMove(endX, endY, board)) {
            // If the move is valid, update the position of the piece on the board
            board[startX][startY].move(endX, endY, board);
            return true;
        } 
        else {
            return false; // Invalid move
        }
    }
}
