package com.nguyendani.chess.pieces;

public class Rook extends Piece {
    
    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board){
        // Horizontal & Vertical movement
        if(startX == endX || startY == endY) {
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
