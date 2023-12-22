package chess.pieces;

public class Rook extends Piece {
    
    public Rook(boolean isWhite){
        super("Rook", isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board){
        // Horizontal & Vertical movement
        if(startX == endX || startY == endY) {
            return isPathClear(startX, startY, endX, endY, board);
        }

        return false;
    }
}
