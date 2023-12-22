package chess.pieces;

public class Pawn extends Piece {

    public Pawn(boolean isWhite){
        super("Pawn", isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board){
        // Implement pawn-specific move validation logic
        int direction = isWhite() ? 1 : -1; // White increase Y Black decrease Y
       
        // First move: 2 space forward
        if(startY + 2 * direction == endY && startX == endX && isFirstMove()) {
            return isPathClear(startX, startY, endX, endY, board);
        }

        // Regular move: 1 space forward
        if(startY + direction == endY && startX == endX) {
            return isPathClear(startX, startY, endX, endY, board);
        }

        // Capture: 1 square diagonal forward
        if(startY + direction == endY && Math.abs(startX - endX) == 1){
            // Make capture function in Piece
            // Implement capture
            return true;
        }

        return false;
    }

    private boolean isFirstMove() {
        // Implement logic to check if it's the pawn's first move
        return false;
    }
}
