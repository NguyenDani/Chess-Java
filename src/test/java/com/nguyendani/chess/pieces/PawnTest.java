package com.nguyendani.chess.pieces;

import static org.junit.Assert.*;
import org.junit.*;

public class PawnTest {
    private Piece[][] board;

    @Before
    public void setUp() {
        // Initialize a new board for each test case
        board = new Piece[8][8];
    }

    @Test
    public void testValidInitialMove() {
        // Test that a pawn can move two squares forward from its initial position
        board[6][3] = new Pawn(true);
        boolean isValidMove = board[6][3].isValidMove(3, 6, 3, 4, board);
        assertTrue("Pawn should be able to move two squares forward from its initial position", isValidMove);
    }

    @Test
    public void testInvalidInitialBlockMove() {
        // Test that a pawn cannot move two squares forward if there's a piece blocking
        // its path
        board[6][3] = new Pawn(true);
        board[5][3] = new Pawn(true);
        boolean isValidMove = board[6][3].isValidMove(3, 6, 3, 4, board);
        assertFalse("Pawn should not be able to move two squares forward if there's a piece blocking its path",
                isValidMove);
    }

    @Test
    public void testValidRegularMove() {
        // Test that a pawn can move one square forward from a non-initial position
        board[4][3] = new Pawn(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 3, 3, board);
        assertTrue("Pawn should be able to move one square forward from a non-initial position", isValidMove);
    }

    @Test
    public void testValidCapture() {
        // Test that a pawn can capture an opponent's piece diagonally
        board[4][3] = new Pawn(true);
        board[3][4] = new Pawn(false);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 3, board);
        assertTrue("Pawn should be able to capture an opponent's piece diagonally", isValidMove);
    }

    @Test
    public void testInvalidCapture() {
        // Test that a pawn cannot capture an ally piece diagonally
        board[4][3] = new Pawn(true);
        board[3][4] = new Pawn(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 3, board);
        assertFalse("Pawn should not be able to capture an ally piece diagonally", isValidMove);
    }

    @Test
    public void testInvalidMove() {
        // Test an invalid move for a pawn
        board[4][3] = new Pawn(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 5, board);
        assertFalse("Pawn should not be able to move", isValidMove);
    }

    @Test
    public void testBlockMove() {
        // Test that a pawn cannot move forward if there's a piece blocking its path
        board[4][3] = new Pawn(true);
        board[3][3] = new Pawn(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 3, 3, board);
        assertFalse("Pawn should not be able to move forward if there's a piece blocking its path", isValidMove);
    }
}
