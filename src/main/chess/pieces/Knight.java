package chess.pieces;

public class Knight extends Piece {

    public Knight(boolean isWhite){
        super("Knight", isWhite);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board) {
        // L shaped movement
        int deltaRow = Math.abs(startY - endY);
        int deltaCol = Math.abs(startX - endX);

        return (deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2);
    }

}
