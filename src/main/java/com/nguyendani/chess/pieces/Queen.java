package com.nguyendani.chess.pieces;

public class Queen extends Piece{
 
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Rook + Bishop
        if(startX == endX || startY == endY || Math.abs(startX - endX) == Math.abs(startY - endY)) {
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
