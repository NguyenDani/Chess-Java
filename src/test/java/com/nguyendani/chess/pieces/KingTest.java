package com.nguyendani.chess.pieces;

import static org.junit.Assert.*;
import org.junit.*;

public class KingTest {
    private Piece[][] board;

    @Before
    public void setUp() {
        board = new Piece[8][8];
    }

    @Test
    public void testValidHorizontalMove() {
        board[4][3] = new King(true);
        // Test that a King can move 1 tile horizontally
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 4, board);
        assertTrue("King should be able to move horizontally", isValidMove);
        // Test that a King can move 1 tile vertically
        isValidMove = board[4][3].isValidMove(3, 4, 3, 5, board);
        assertTrue("King should be able to move vertically", isValidMove);
        // Test that a King can move 1 tile diagonally
        isValidMove = board[4][3].isValidMove(3, 4, 4, 5, board);
        assertTrue("King should be able to move diagonally", isValidMove);
    }

    @Test
    public void testValidCapture() {
        // Test that a King can capture opponent's piece
        board[4][3] = new King(true);
        board[5][4] = new Rook(false);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 5, board);
        assertTrue("King should be able to capture opponent's piece", isValidMove);
    }

    @Test
    public void testInvalidInitialMove() {
        // Test an invalid move for King
        board[4][3] = new King(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 6, 6, board);
        assertFalse("King should not be able to move", isValidMove);
    }

    @Test
    public void testBlockMove() {
        // Test that a King cannot move if there's a piece blocking its path
        board[4][3] = new King(true);
        board[5][4] = new Rook(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 5, board);
        assertFalse("King should not be able to move if there's a piece blocking its path", isValidMove);       
    }
}
