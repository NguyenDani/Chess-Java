package com.nguyendani.chess.pieces;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int deltaRow = Math.abs(startY - endY);
        int deltaCol = Math.abs(startX - endX);

        if ((deltaRow == 1 && deltaCol == 0) || (deltaRow == 0 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 1)) {
            if (board[endY][endX] != null) {
                return capture(endX, endY, board);
            } else {
                return true;
            }
        }
        return false;
    }
}
