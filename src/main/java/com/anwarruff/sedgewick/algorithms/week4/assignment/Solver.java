
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

/**
 * Created by aruff on 12/2/16.
 */
public class Solver {
    private Iterable<Board> solution;
    private boolean solvable = false;
    private int movesToSolve = -1;

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

    public Solver(Board board) {
        ArrayList<SearchNode> closedSet = new ArrayList<>();

        MinPQ<SearchNode> minPQ = new MinPQ<>();
        boolean unsolvable = false;

        minPQ.insert(new SearchNode(board));
        minPQ.insert(new SearchNode(board.twin()));

        while( !solvable && !unsolvable) {
            if (minPQ.isEmpty()) {
                break;
            }

            SearchNode searchNode = minPQ.delMin();
            SearchNode previous = searchNode.previous;

            closedSet.add(searchNode);

            if (searchNode.board.isGoal()) {
                if (isSearchNodeRootBoard(board, searchNode)) {
                    solvable = true;
                    movesToSolve = searchNode.moves;
                    solution = getSolution(closedSet);
                } else {
                    unsolvable = true;
                    solution = null;
                }
            } else {
                for (Board child : searchNode.board.neighbors()) {
                    if (previous == null || !previous.board.equals(child)) {
                        SearchNode neighbor = new SearchNode(child, searchNode);
                        minPQ.insert(neighbor);
                    }
                }
            }
        }

    }

    private boolean isSearchNodeRootBoard(Board rootBoard, SearchNode searchNode) {
        SearchNode node = searchNode;
        while (node.previous != null) {
            node = node.previous;
        }

        return node.board.equals(rootBoard);
    }

    private Iterable<Board> getSolution(ArrayList<SearchNode> closedSet) {
        Stack<Board> solution = new Stack<>();
        SearchNode searchNode = closedSet.get(closedSet.size() - 1);
        while (searchNode != null) {
            solution.push(searchNode.board);
            searchNode = searchNode.previous;
        }
        return solution;
    }

    public Iterable<Board> solution() {
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

