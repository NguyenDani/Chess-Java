package com.nguyendani.chess.pieces;

public abstract class Piece {
    private boolean isWhite;
    private int startX;
    private int startY;

    public Piece(boolean isWhite, int startX, int startY) {
        this.isWhite = isWhite;
        this.startX = startX;
        this.startY = startY;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board);

    protected boolean isSameSpot(int endX, int endY) {
        if(startX == endX && startY == endY){
            return true;
        }

        return false;
    }

    protected boolean isPathClear(int endX, int endY, Piece[][] board){
        int deltaRow = Integer.compare(endY, startY);
        int deltaCol = Integer.compare(endX, startX);

        int currentY = startY + deltaRow;
        int currentX = startX + deltaCol;

        while(currentX != endX || currentY != endY) {
            if(board[currentX][currentY] != null) {
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
        Piece targetPiece = board[targetX][targetY];
        if(targetPiece != null && !isSameColor(targetPiece)) {
            board[targetX][targetY] = null;
            return true;
        }
        // No piece to capture
        return false;
    }

    public boolean isSameColor(Piece otherPiece) {
        return this.isWhite == otherPiece.isWhite;
    }

    public void movePiece(int endX, int endY, Piece[][] board) {
        // Update the piece's position
        board[startX][startY] = null;
        board[endX][endY] = this;
        this.startX = endX;
        this.startY = endY;
    }
}
