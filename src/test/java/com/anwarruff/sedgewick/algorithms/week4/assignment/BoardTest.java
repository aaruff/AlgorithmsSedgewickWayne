package com.anwarruff.sedgewick.algorithms.week4.assignment;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

/**
 * Created by aruff on 11/28/16.
 */
public class BoardTest {

    @Test
    public void testConstructor() {
        assertThatThrownBy(() -> new Board(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testDimension() {
        int[][] initial = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(initial);
        assertEquals(3, board.dimension());
    }

    @Test
    public void testHamming() {
        int[][] initial = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(initial);
        assertEquals(3, board.hamming());

    }

}