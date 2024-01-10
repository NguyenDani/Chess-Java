package com.nguyendani.chess.pieces;

public class Bishop extends Piece {
    
    public Bishop(boolean isWhite, int startX, int startY) {
        super("Bishop", isWhite, startX, startY);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        if(isSameSpot) {
            return false;
        }

        // Diagonal movement
        if(Math.abs(startX - endX) == Math.abs(startY - endY)) {
            if(isPathClear(startX, startY, endX, endY, board)) {
                if(board[endX][endY] != null) {
                    capture(endX, endY, board);
                }
                else {
                    // Move piece here
                }
            }
        }

        return false;
    }
}
