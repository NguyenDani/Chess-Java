package chess;

public class ChessBoard {
    private Piece[][] board;

    public ChessBoard() {
        initBoard();
    }

    private void initBoard() {
        // Set up the inital position of pieces
    }

    public void displayBoard(){
        // Update the SwingGUI to reflect the current state
        // Call this method whenever the sate of the chessboard changes
        if(movePiece()) { // Validate move through movePiece
            // Update the board 
        } 
    }

    public boolean movePiece (Player player, int startX, int startY, int endX, int endY) {
        // Move a piece on the board
        // Check for the validity of the move
        // Check for checkmate, stalemate, etc.
        // Update the board state
        if(board[startX][startY].isValidMove(endX, endY)) {
            // Perform the move and update the board state
            return true;
        } else {
            return false; // Invalid move
        }
    }
}
