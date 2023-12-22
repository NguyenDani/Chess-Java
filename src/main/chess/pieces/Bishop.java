package chess.pieces;

public class Bishop extends Piece {
    
    public Bishop(boolean isWhite) {
        super("Bishop", isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Diagonal movement
        if(Math.abs(startX - endX) == Math.abs(startY - endY)){
            return isPathClear(startX, startY, endX, endY, board);
        }

        return false;
    }
}
