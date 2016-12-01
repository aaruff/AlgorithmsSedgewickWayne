package com.anwarruff.sedgewick.algorithms.week4.assignment;

import com.anwarruff.sedgewick.algorithms.week4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by aruff on 11/28/16.
 */
public class Solver {
    private ArrayList<Board> solution;
    private boolean solvable;

    public Solver(Board board) {
        int n = board.dimension();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tiles[i][j] = ((i * n) + j + 1) % (n*n);
            }
        }
        Board solutionBoard = new Board(tiles);

        MinPQ<Board> solvablePQ = new MinPQ<>(getBoardComparator());
        MinPQ<Board> unsolvablePQ = new MinPQ<>(getBoardComparator());
        solvablePQ.insert(board);
        unsolvablePQ.insert(board.twin());

        solution = new ArrayList<>();
        solvable = false;
        boolean unsolvable = false;
        while( ! solvable && ! unsolvable) {
            Board minBoard = solvablePQ.delMin();
            solution.add(minBoard);

            Board minUnsolvableBoard = unsolvablePQ.delMin();
            if (minUnsolvableBoard.equals(solutionBoard)) {
                unsolvable = true;
                solution = new ArrayList<>();
            }
            else {
                for (Board b : minUnsolvableBoard.neighbors()) {
                    if ( ! minUnsolvableBoard.equals(b)) {
                        unsolvablePQ.insert(b);
                    }
                }
            }

            if (minBoard.equals(solutionBoard)) {
                solvable = true;
            }
            else {
                for (Board b : minBoard.neighbors()) {
                    if ( ! minBoard.equals(b)) {
                        solvablePQ.insert(b);
                    }
                }
            }
        }
    }

    public Iterable<Board> solution() {
        return solution;
    }

    public int moves() {
        return (solvable) ? solution.size() : -1;
    }

    public boolean isSolvable() {
        return solvable;
    }

    public static Comparator<Board> getBoardComparator() {
        int LESS = -1;
        int GREATER = 1;
        int EQUAL = 0;
        return (b1, b2) -> {
            if (b1.manhattan() < b2.manhattan()) {
                return LESS;
            }
            else if (b1.manhattan() > b2.manhattan()) {
                return GREATER;
            }
            else {
                return EQUAL;
            }
        };
    }
}
