package com.anwarruff.sedgewick.algorithms.week4.assignment;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

/**
 * Created by aruff on 12/2/16.
 */
public class Solver {
    private ArrayList<SearchNode> closedSet;
    private boolean solvable;
    private int movesToSolve;

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode previous;
        private int moves;
        private int manhattan;

        public SearchNode(Board board) {
            this.board = board;
            this.moves = 0;
            this.manhattan = board.manhattan();
        }


        public SearchNode(Board board, SearchNode previous) {
            this.board = board;
            this.previous = previous;
            this.moves = previous.moves + 1;
            this.manhattan = board.manhattan();
        }

        @Override
        public int compareTo(SearchNode s) {
            if ((manhattan + moves) > (s.manhattan + s.moves)) {
                return 1;
            } else if ((manhattan + moves) < (s.manhattan + s.moves)) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private class AStarSolver {
        private ArrayList<SearchNode> closedSet = new ArrayList<>();
        private MinPQ<SearchNode> minPQ = new MinPQ<>();
        private boolean solved = false;
        private boolean unsolvable = false;
        private int movesToSolve = -1;

        public AStarSolver(SearchNode searchNode) {
            minPQ.insert(searchNode);
        }

        public void step() {
            if (unsolvable || solved) {
                return;
            }
            if (minPQ.isEmpty()) {
                unsolvable = true;
                return;
            }

            SearchNode searchNode = minPQ.delMin();
            SearchNode previous = searchNode.previous;

            closedSet.add(searchNode);

            if (searchNode.board.isGoal()) {
                solved = true;
                movesToSolve = searchNode.moves;
                minPQ = null;

            }
            else {
                for (Board child : searchNode.board.neighbors()) {
                    if ( previous == null || ! previous.board.equals(child)) {
                        SearchNode neighbor = new SearchNode(child, searchNode);
                        minPQ.insert(neighbor);
                    }
                }
            }
        }
    }

    public Solver(Board board) {
        AStarSolver solvableSolver = new AStarSolver(new SearchNode(board));
        AStarSolver unsolvableSolver = new AStarSolver(new SearchNode(board.twin()));

        solvable = false;
        boolean unsolvable = false;
        while( !solvable && !unsolvable) {

            // solvable
            solvableSolver.step();
            if (solvableSolver.solved) {
                this.solvable = true;
                this.closedSet = solvableSolver.closedSet;
                this.movesToSolve = solvableSolver.movesToSolve;
            }
            // unsolvable
            unsolvableSolver.step();
            if (unsolvableSolver.solved) {
                unsolvable = true;
                this.solvable = false;
                this.movesToSolve = -1;
            }
        }
    }

    public Iterable<Board> solution() {
        if ( ! solvable) {
            return null;
        }

        Stack<Board> solution = new Stack<>();
        SearchNode searchNode = closedSet.get(closedSet.size() - 1);
        while (searchNode != null) {
            solution.push(searchNode.board);
            searchNode = searchNode.previous;
        }
        return solution;
    }

    public int moves() {
        return movesToSolve;
    }

    public boolean isSolvable() {
        return solvable;
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
        Solver boardSolver = new Solver(initial);

        // print closedSet to standard output
        if (!boardSolver.isSolvable())
            StdOut.println("No closedSet possible");
        else {
            StdOut.println("Minimum number of closedSet = " + boardSolver.moves());
            for (Board board : boardSolver.solution())
                StdOut.println(board);
        }
    }
}

