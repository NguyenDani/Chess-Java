package com.nguyendani.chess.pieces;

import static org.junit.Assert.*;
import org.junit.*;

public class RookTest {
    private Piece[][] board;

    @Before
    public void setUp() {
        board = new Piece[8][8];
    }

    @Test
    public void testValidHorizontalMove() {
        // Test that a Rook can move horizontally
        board[4][3] = new Rook(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 4, board);
        assertTrue("Rook should be able to move horizontally", isValidMove);
    }

    @Test
    public void testValidVerticalMove() {
        // Test that a Rook can move vertically
        board[4][3] = new Rook(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 3, 6, board);
        assertTrue("Rook should be able to move vertically", isValidMove);
    }

    @Test
    public void testValidVerticalCapture() {
        // Test that a Rook can capture opponent's piece vertically
        board[4][3] = new Rook(true);
        board[6][3] = new Rook(false);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 3, 6, board);
        assertTrue("Rook should be able to capture an oppent's piece vertically", isValidMove);

        // Test that a Rook can capture opponent's piece horizontally
        board[4][5] = new Rook(false);
        isValidMove = board[4][3].isValidMove(3, 4, 5, 4, board);
        assertTrue("Rook should be able to capture an oppent's piece horizontally", isValidMove);
    }

    @Test
    public void testInvalidCapture() {
        // Test that a Rook cannot capture an ally piece
        board[4][3] = new Rook(true);
        board[4][5] = new Rook(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 4, board);
        assertFalse("Rook should not be able to capture an ally piece", isValidMove);
    }

    @Test
    public void testInvalidMove() {
        // Test an invalid move for Rook
        board[4][3] = new Rook(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 2, board);
        assertFalse("Rook should not be able to move", isValidMove);
    }

    @Test
    public void testBlockMove() {
        // Test that a Rook cannot move if there's a piece blocking its path
        board[4][3] = new Rook(true);
        board[4][4] = new Rook(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 4, board);
        assertFalse("Rook should not be able to move if there's a piece blocking its path", isValidMove);
    }
}
