package com.nguyendani.chess.pieces;

public class Pawn extends Piece {
    private boolean isFirstMove;
    private boolean isWhite;

    public Pawn(boolean isWhite, int startX, int startY){
        super(isWhite, startX, startY);
        this.isFirstMove = true;
        this.isWhite = isWhite;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board){ 
        if(isSameSpot(endX, endY)) {
            return false;
        }
        // Implement pawn-specific move validation logic
        int direction = this.isWhite ? 1 : -1; // White increase Y Black decrease Y
       
        // First move: 2 space forward
        if(startY + 2 * direction == endY && startX == endX && isFirstMove) {
            if(isPathClear(endX, endY, board)) {
                updateFirstMove();
                return true;
            }
            return false;
        }

        // Regular move: 1 space forward
        if(startY + direction == endY && startX == endX) {
            if(isPathClear(endX, endY, board)) {
                updateFirstMove();
                return true;
            }
            return false;
        }

        // Capture: 1 square diagonal forward
        if(startY + direction == endY && Math.abs(startX - endX) == 1) {
            if(capture(endX, endY, board)) {
                updateFirstMove();
                return true;
            }
            return false;
        }

        return false;
    }
        private void updateFirstMove() {
            if(isFirstMove) {
                isFirstMove = false;
            }
        }
}
