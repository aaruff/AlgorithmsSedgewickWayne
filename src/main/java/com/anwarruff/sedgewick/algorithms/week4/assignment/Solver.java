package com.anwarruff.sedgewick.algorithms.week4.assignment;

import java.util.ArrayList;

/**
 * Created by aruff on 11/28/16.
 */
public class Solver {
    private Iterable<Board> solution;
    private boolean solvable;
    private int moves;

    public Solver(Board board) {
        int n = board.dimension();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                tiles[i][j] = ((i * n) + j + 1) % (n*n);
            }
        }
        Board solution = new Board(tiles);
        BFSSolver solvableSolver = new BFSSolver(new SearchNode(board, null, 0), solution);
        BFSSolver unsolvableSolver = new BFSSolver(new SearchNode(board.twin(), null, 0), solution);

        solvable = false;
        boolean unsolvable = false;
        while( !solvable && !unsolvable) {

            // solvable
            if (solvableSolver.step()) {
                this.solvable = true;
                this.solution = solvableSolver.getSolution();
                this.moves = solvableSolver.getNumMoves();
            }
            // unsolvable
            else if (unsolvableSolver.step()) {
                this.solvable = false;
                unsolvable = true;
                this.moves = -1;
            }
        }
    }

    public Iterable<Board> solution() {
        if (solvable) {
            return solution;
        }
        else {
            return new ArrayList<>();
        }
    }

    public int moves() {
        return moves;
    }

    public boolean isSolvable() {
        return solvable;
    }
}
