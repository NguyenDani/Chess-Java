package com.nguyendani.chess.pieces;

public class Rook extends Piece {
    
    public Rook(boolean isWhite, int startX, int startY){
        super(isWhite, startX, startY);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board){
        if(isSameSpot(endX, endY)) {
            return false;
        }

        // Horizontal & Vertical movement
        if(startX == endX || startY == endY) {
            if(isPathClear(endX, endY, board)) {
                if(board[endX][endY] != null) {
                    capture(endX, endY, board);
                }

                return true;
            }
        }

        return false;
    }
}