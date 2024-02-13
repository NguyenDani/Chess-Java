package com.nguyendani.chess.pieces;

public class Knight extends Piece {

    public Knight(boolean isWhite){
        super(isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) { 
        // L shaped movement
        int deltaRow = Math.abs(startY - endY);
        int deltaCol = Math.abs(startX - endX);

        if ((deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2)) {
            if (board[endY][endX] != null) {
                return capture(endX, endY, board);
            } else {
                return true;
            }
        }

        return false;
    }
}
