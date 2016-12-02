package com.anwarruff.sedgewick.algorithms.week4.assignment;

/**
 * Created by aruff on 12/1/16.
 */
public class SearchNode implements Comparable<SearchNode>{
    private Board board;
    private int priority;
    private SearchNode previous;


    public SearchNode(Board board, SearchNode previous, int moves) {
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

    public SearchNode getPrevious() {
        return previous;
    }

    @Override
    public int compareTo(SearchNode s) {
        int LESS = -1;
        int GREATER = 1;
        int EQUAL = 0;
        if (getPriority() > s.getPriority()) {
            return GREATER;
        }
        else if (getPriority() < s.getPriority()) {
            return LESS;
        }
        else {
            return EQUAL;
        }
    }
}

