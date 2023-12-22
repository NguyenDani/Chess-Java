package chess.pieces;

public abstract class Piece {
    private String type;
    private boolean isWhite; // Or isBlack

    public Piece(String type, boolean isWhite) {
        // Initialize the piece type and color
        this.type = type;
        this.isWhite = isWhite;
    }

    public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Piece[][] board);

    public boolean isSameColor(Piece otherPiece) {
        return this.isWhite == otherPiece.isWhite;
    }

    protected boolean isPathClear(int startX, int startY, int endX, int endY, Piece[][] board){
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

    protected boolean capture(int startX, int startY, int endX, int endY, Piece[][] board) {
        // Implement capture
        return true;
    }
}
