package com.nguyendani.chess.pieces;

public class Rook extends Piece {
    public boolean isFirstMove;

    public Rook(boolean isWhite) {
        super(isWhite);
        this.isFirstMove = true;
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Horizontal & Vertical movement
        if (startX == endX || startY == endY) {
            if (isPathClear(startX, startY, endX, endY, board)) {
                if (board[endY][endX] != null) {
                    if (capture(endX, endY, board)) {
                        return true;
                    }
                } else {
                    return true;
                }
            }
        }

        return false;
    }

    public void updateFirstMove() {
        if (isFirstMove) {
            isFirstMove = false;
        }
    }
}
