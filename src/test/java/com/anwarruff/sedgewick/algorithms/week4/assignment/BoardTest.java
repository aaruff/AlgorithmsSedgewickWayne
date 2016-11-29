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
        /*
         *   Solution
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 4 | 5 | 6 |
         * +---+---+---+
         * | 7 | 8 |   |
         * +---+---+---+
         *
         *    Entry
         * +---+---+---+
         * | 8 | 1 | 3 |
         * +---+---+---+
         * | 4 |   | 2 |
         * +---+---+---+
         * | 7 | 6 | 5 |
         * +---+---+---+
         *
         * value: (VerticalDistance, HorizontalDistance, SumHorizontalVerticalDistances)
         * 8: (2, 1, 3)
         * 1: (0, 1, 1)
         * 3: (0, 0, 0)
         * 4: (0, 0, 0)
         * 2: (1, 1, 2)
         * 7: (0, 0, 0)
         * 6: (1, 1, 2)
         * 5: (1, 1, 2)
         * Total = 10
         */
        int[][] initial = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(initial);
        assertEquals(10, board.hamming());
    }

}