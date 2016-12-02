package com.anwarruff.sedgewick.algorithms.week4.assignment;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;


/**
 * Created by aruff on 11/28/16.
 */
public class SolverTest {
    @Test
    public void testComparator() {
        /*
         *  Board 1
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 4 | 5 | 6 |
         * +---+---+---+
         * | 7 | 8 |   |
         * +---+---+---+
         * hamming = 0
         *
         *  Board 2
         * +---+---+---+
         * | 1 | 2 | 3 |
         * +---+---+---+
         * | 4 | 5 |   |
         * +---+---+---+
         * | 7 | 8 | 6 |
         * +---+---+---+
         * hamming = 1
         *
         *  Board 2
         * +---+---+---+
         * | 3 | 2 | 1 |
         * +---+---+---+
         * | 4 | 5 | 6 |
         * +---+---+---+
         * | 7 |   | 8 |
         * +---+---+---+
         * hamming = 3
        */
        int[][] v1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b1 = new Board(v1);
        assertEquals(0, b1.hamming());
        int[][] v2 = {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}};
        Board b2 = new Board(v2);
        assertEquals(1, b2.hamming());
        int[][] v3 = {{3, 2, 1}, {4, 5, 6}, {7, 0, 8}};
        Board b3 = new Board(v3);
        assertEquals(3, b3.hamming());
        ArrayList<SearchNode> searchNodes = new ArrayList<>();
        searchNodes.add(new SearchNode(b3, null, 0));
        searchNodes.add(new SearchNode(b2, null, 0));
        searchNodes.add(new SearchNode(b1, null, 0));

        Collections.sort(searchNodes);
        assertTrue(searchNodes.get(0).isBoardEqual(b1));
        assertTrue(searchNodes.get(1).isBoardEqual(b2));
        assertTrue(searchNodes.get(2).isBoardEqual(b3));
    }

    @Test
    public void testSolutionBoard() {
        int[][] solutionTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board solutionBoard = new Board(solutionTiles);
        Solver solver = new Solver(solutionBoard);
        assertTrue(solver.isSolvable());
    }

    @Test
    public void testBFSSolver() {
        int[][] solutionTiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board solution = new Board(solutionTiles);

        int[][] v1 = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board b1 = new Board(v1);

        BFSSolver bfsSolver = new BFSSolver(new SearchNode(b1, null, 0), solution);

        assertFalse(bfsSolver.step());

        ArrayList<Board> moves = bfsSolver.getSolution();
        assertTrue(b1.equals(moves.get(0)));

        int[][] v2 = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};
        Board b2 = new Board(v2);
        assertFalse(bfsSolver.step());
        moves = bfsSolver.getSolution();
        assertTrue(b2.equals(moves.get(1)));

        int[][] v3 = {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}};
        Board b3 = new Board(v3);
        assertFalse(bfsSolver.step());
        moves = bfsSolver.getSolution();
        assertTrue(b3.equals(moves.get(2)));
    }

}