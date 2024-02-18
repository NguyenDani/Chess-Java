package com.nguyendani.chess.pieces;

import com.nguyendani.chess.Game;

public class King extends Piece {
    public boolean isFirstMove;

    public King(boolean isWhite) {
        super(isWhite);
        this.isFirstMove = true;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        int deltaRow = Math.abs(startY - endY);
        int deltaCol = Math.abs(startX - endX);

        if ((deltaRow == 1 && deltaCol == 0) || (deltaRow == 0 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 1)) {
            updateFirstMove();
            if (board[endY][endX] != null) {
                
                return capture(endX, endY, board);
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isValidCastling(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Check if it's the first move and if the king is not in check
        if (!isFirstMove || Game.getInstance().isInCheck(isWhite)) {
            return false;
        }
    
        // Check if the destination is within two squares of the initial position
        if (Math.abs(endX - startX) != 2 || endY != startY) {
            return false;
        }
    
        // Check if there are no pieces between the king and rook
        int rookX = (endX > startX) ? 7 : 0;
        int rookY = startY;
        int direction = (endX > startX) ? 1 : -1;
    
        for (int i = startX + direction; i != endX; i += direction) {
            if (board[startY][i] != null) {
                return false;
            }
        }
    
        // Check if the rook is at its initial position
        Piece rook = board[rookY][rookX];
        if (!(rook instanceof Rook) || !((Rook) rook).isFirstMove) {
            return false;
        }
    
        return true;
    }

    private void updateFirstMove() {
        if (isFirstMove) {
            isFirstMove = false;
        }
    }
}
