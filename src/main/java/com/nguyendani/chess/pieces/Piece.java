package com.nguyendani.chess.pieces;

public abstract class Piece {
    public boolean isWhite;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board);

    protected boolean isPathClear(int startX, int startY, int endX, int endY, Piece[][] board){
        int deltaRow = Integer.compare(endY, startY);
        int deltaCol = Integer.compare(endX, startX);

        int currentY = startY + deltaRow;
        int currentX = startX + deltaCol;

        while(currentX != endX || currentY != endY) {
            if(board[currentY][currentX] != null) {
                // There is a piece in the way
                return false;
            }

            currentX += deltaCol;
            currentY += deltaRow;
        }
        // Path is clear
        return true;
    }

    protected boolean capture(int targetX, int targetY, Piece[][] board) {
        if(board[targetY][targetX] != null && board[targetY][targetX].isWhite != isWhite) {
            return true;
        }
        // No piece to capture
        return false;
    }
}
