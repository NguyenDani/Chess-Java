package com.nguyendani.chess.pieces;

import static org.junit.Assert.*;
import org.junit.*;

public class QueenTest {
    private Piece[][] board;

    @Before
    public void setUp() {
        board = new Piece[8][8];
    }

    @Test
    public void testValidMove() {
        // Test that a Queen can move horizontally
        board[4][3] = new Queen(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 4, board);
        assertTrue("Queen should be able to move horizontally", isValidMove);

        // Test that a Queen can move vertically
        isValidMove = board[4][3].isValidMove(3, 4, 3, 6, board);
        assertTrue("Queen should be able to move vertically", isValidMove);

        // Test that a Queen can move diagonally
        isValidMove = board[4][3].isValidMove(3, 4, 5, 6, board);
        assertTrue("Queen should be able to move diagnoally", isValidMove);
    }

    @Test
    public void testValidVerticalCapture() {
        // Test that a Queen can capture opponent's piece vertically
        board[4][3] = new Queen(true);
        board[6][3] = new Queen(false);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 3, 6, board);
        assertTrue("Queen should be able to capture an oppent's piece vertically", isValidMove);

        // Test that a Queen can capture opponent's piece horizontally
        board[4][5] = new Queen(false);
        isValidMove = board[4][3].isValidMove(3, 4, 5, 4, board);
        assertTrue("Queen should be able to capture an oppent's piece horizontally", isValidMove);

        // Test that a Queen can capture oppoenent's piece diagonally
        board[5][4] = new Queen(false);
        isValidMove = board[4][3].isValidMove(3, 4, 4, 5, board);
        assertTrue("Queen should be able to capture an opponent's piece", isValidMove);
    }

    @Test
    public void testInvalidCapture() {
         // Test that a Queen cannot capture an ally piece
         board[4][3] = new Queen(true);
         board[5][4] = new Queen(true);
         boolean isValidMove = board[4][3].isValidMove(3, 4, 4, 5, board);
         assertFalse("Queen should not be able to capture an ally piece", isValidMove);
    }

    @Test
    public void testInvalidMove() {
        // Test an invalid move for Queen
        board[4][3] = new Queen(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 0, 0, board);
        assertFalse("Queen should not be able to move", isValidMove);
    }

    @Test
    public void testBlockMove() {
        // Test that a Queen cannot move if there's a piece blocking its path
        board[4][3] = new Queen(true);
        board[5][4] = new Queen(true);
        boolean isValidMove = board[4][3].isValidMove(3, 4, 5, 6, board);
        assertFalse("Queen should not be able to move forward if there's a piece blocking its path",isValidMove);
    }
}
