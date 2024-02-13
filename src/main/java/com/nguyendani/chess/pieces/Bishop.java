package com.nguyendani.chess.pieces;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Diagonal movement
        if (Math.abs(startX - endX) == Math.abs(startY - endY)) {
            if (isPathClear(startX, startY, endX, endY, board)) {
                if (board[endY][endX] != null) {
                    return capture(endX, endY, board);
                } else {
                    return true;
                }
            }
        }

        return false;
    }
}
