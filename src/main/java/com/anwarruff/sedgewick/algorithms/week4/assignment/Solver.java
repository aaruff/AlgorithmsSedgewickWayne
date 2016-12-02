package com.anwarruff.sedgewick.algorithms.week4.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

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
        PrivateSolver solvableSolver = new PrivateSolver(new PrivateSearchNode(board, null, 0), solution);
        PrivateSolver unsolvableSolver = new PrivateSolver(new PrivateSearchNode(board.twin(), null, 0), solution);

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

    private class PrivateSolver {
        private ArrayList<Board> moves = new ArrayList<>();
        private MinPQ<PrivateSearchNode> minPQ = new MinPQ<>();
        private boolean solved = false;

        private Board solution;

        public PrivateSolver(PrivateSearchNode privateSearchNode, Board solution) {
            this.solution = solution;
            minPQ.insert(privateSearchNode);
        }

        public boolean step() {
            if (solved) {
                return solved;
            }

            PrivateSearchNode privateSearchNode = minPQ.delMin();
            PrivateSearchNode previous = privateSearchNode.getPrevious();

            moves.add(privateSearchNode.getBoard());

            if (privateSearchNode.isBoardEqual(solution)) {
                solved = true;
                return solved;
            }
            else {
                for (Board neighbor : privateSearchNode.getNeighbors()) {
                    if ( previous == null || ! previous.isBoardEqual(neighbor)) {
                        minPQ.insert(new PrivateSearchNode(neighbor, privateSearchNode, moves.size()));
                    }
                }

                return solved;
            }
        }

        public ArrayList<Board> getSolution() {
            return moves;
        }

        public int getNumMoves() {
            return (moves.size() == 0) ? -1 : moves.size() - 1;
        }
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class PrivateSearchNode implements Comparable<PrivateSearchNode> {
        private Board board;
        private int priority;
        private PrivateSearchNode previous;


        public PrivateSearchNode(Board board, PrivateSearchNode previous, int moves) {
            this.board = board;
            this.priority = board.hamming() + moves;
            this.previous = previous;
        }

        public Board getBoard() {
            return board;
        }

        public Iterable<Board> getNeighbors() {
            return board.neighbors();
        }

        public int getPriority() {
            return priority;
        }

        public boolean isBoardEqual(Board b) {
            return board.equals(b);
        }

        public PrivateSearchNode getPrevious() {
            return previous;
        }

        @Override
        public int compareTo(PrivateSearchNode s) {
            int LESS = -1;
            int GREATER = 1;
            int EQUAL = 0;
            if (getPriority() > s.getPriority()) {
                return GREATER;
            } else if (getPriority() < s.getPriority()) {
                return LESS;
            } else {
                return EQUAL;
            }
        }
    }
}
