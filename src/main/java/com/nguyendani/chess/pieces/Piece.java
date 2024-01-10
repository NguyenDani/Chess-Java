package com.nguyendani.chess.pieces;

public abstract class Piece {
    private String type;
    private boolean isWhite; // Or isBlack
    private int currentX;
    private int currentY;

    public Piece(String type, boolean isWhite, int startX, int startY) {
        // Initialize the piece type and color
        this.type = type;
        this.isWhite = isWhite;
        this.currentX = startX;
        this.currentY = startY;
    }

    public abstract boolean isValidMove(int endX, int endY, Piece[][] board);

    public boolean isSameColor(Piece otherPiece) {
        return this.isWhite == otherPiece.isWhite;
    }

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

    public void move(int endX, int endY, Piece[][] board) {
        // Update the piece's position
        board[currentX][currentY] = null;
        board[endX][endY] = this;
        this.currentX = endX;
        this.currentY = endY;
    }
}
