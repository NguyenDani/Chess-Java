package com.nguyendani.chess.pieces;

import static org.junit.Assert.*;
import org.junit.*;

public class BishopTest {
    private Piece[][] board;

    @Before
    public void setUp() {
        board = new Piece[8][8];
    }

    @Test
    public void testValidDiagonalMove() {
        // Test that a Bishop can move Diagonally
        board[4][3] = new Bishop(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 6, board);
        assertTrue("Bishop should be able to move diagnoally", isValidMove);
    }

    @Test
    public void testValidDiagnalCapture() {
        // Test that a Bishop can capture oppoenent's piece diagonally
        board[4][3] = new Bishop(true);
        board[5][4] = new Bishop(false);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 5, board);
        assertTrue("Bishop should be able to capture an opponent's piece", isValidMove);
    }

    @Test
    public void testInvalidCapture() {
        // Test that a Bishop cannot capture an ally piece
        board[4][3] = new Bishop(true);
        board[5][4] = new Bishop(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 5, board);
        assertFalse("Bishop should not be able to capture an ally piece", isValidMove);
    }

    @Test
    public void testInvalidMove() {
        // Test an invalid move for Bishop
        board[4][3] = new Bishop(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 3, 6, board);
        assertFalse("Bishop should not be able to move", isValidMove);
    }

    @Test
    public void testBlockMove() {
        // Test that a Bishop cannot move if there's a piece blocking its path
        board[4][3] = new Bishop(true);
        board[5][4] = new Bishop(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 6, board);
        assertFalse("Bishop should not be able to move forward if there's a piece blocking its path",isValidMove);
    }
}
