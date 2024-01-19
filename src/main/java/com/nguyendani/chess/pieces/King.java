package com.nguyendani.chess.pieces;

public class King extends Piece {

    public King(boolean isWhite, int startX, int startY) {
        super(isWhite, startX, startY);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        if(isSameSpot(endX, endY)) {
            return false;
        }

        int deltaRow = Math.abs(startY - endY);
        int deltaCol = Math.abs(startX - endX);

        if((deltaRow == 1 && deltaCol == 0) || (deltaRow == 0 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 1)) {
            return isPathClear(endX, endY, board);
        }

        return false;
    }
}
