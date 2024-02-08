package com.nguyendani.chess.pieces;

public class Queen extends Piece{
 
    public Queen(boolean isWhite, int startX, int startY){
        super(isWhite, startX, startY);
        System.out.println("Create New Queen");
        System.out.println("isWhite: " + isWhite);
        System.out.println("From Queen startX: " + startX + " startY: " + startY);
        test();
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        if(isSameSpot(endX, endY)) {
            return false;
        }

        // Horizontal & Vertical movement
        if(startX == endX || startY == endY) {
            return isPathClear(endX, endY, board);
        }
        
        // Diagonal movement
        if(Math.abs(startX - endX) == Math.abs(startY - endY)) {
            return isPathClear(endX, endY, board);
        }

        return false;
    }
}
