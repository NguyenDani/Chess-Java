package chess;

public class Game {
    private ChessBoard board;
    private Player whitePlayer;
    private Player blackPlayer;

    public Game() {
        // Initialize the game
        initGame();
    }

    private void initGame() {
        // Set up the chessboard, players, etc.
    }

    public void startGame(){
        // Implement the main game loop
        while(!gameOver()){
            // Take turns, make moves, update game state
        }
    }

    private boolean gameOver() {
        // Check for game over conditions
        return false;
    }
}
