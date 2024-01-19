package com.nguyendani.chess.pieces;

public class Knight extends Piece {

    public Knight(boolean isWhite, int startX, int startY){
        super(isWhite, startX, startY);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) { 
        if(isSameSpot(endX, endY)) {
            return false;
        }

        // L shaped movement
        int deltaRow = Math.abs(startY - endY);
        int deltaCol = Math.abs(startX - endX);

        return (deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2);
    }

}
