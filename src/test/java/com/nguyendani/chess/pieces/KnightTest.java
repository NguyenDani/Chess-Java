package com.nguyendani.chess.pieces;

import static org.junit.Assert.*;
import org.junit.*;

public class KnightTest {
    private Piece[][] board;

    @Before
    public void setUp() {
        board = new Piece[8][8];
    }

    @Test
    public void testValidMove() {
        // Test that a Knight can move in an L shape
        board[4][3] = new Knight(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 6, board);
        assertTrue("Knight should be able to move in an L shape", isValidMove);
    }

    @Test
    public void testValidCapture() {
        // Test that a Knight can capture opponent's piece in an L shape
        board[4][3] = new Knight(true);
        board[6][4] = new Knight(false);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 6, board);
        assertTrue("Knight should be able to capture an opponent's piece in an L shape", isValidMove);
    }

    @Test
    public void testInvalidMove() {
        // Test an invalid move for Knight
        board[4][3] = new Knight(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 6, 6, board);
        assertFalse("Knight should not be able to move", isValidMove);
    }

    @Test
    public void testInvalidCapture() {
        // Test that a knight cannot capture an ally piece
        board[4][3] = new Knight(true);
        board[6][4] = new Knight(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 6, board);
        assertFalse("Knight should not be able to capture an ally piece", isValidMove);
    }
}
