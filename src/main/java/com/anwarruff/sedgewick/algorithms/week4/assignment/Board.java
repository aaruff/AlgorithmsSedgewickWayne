package com.anwarruff.sedgewick.algorithms.week4.assignment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aruff on 11/28/16.
 */
public class Board {
    private int[][] tiles;
    private int[][] twin;
    private int hamming;
    private int manhattan;
    private boolean isGoal;
    private ArrayList<Neighbor> neighbors;
    private ArrayList<Board> neighboringBoards;

    private int n;
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new NullPointerException();
        }

        n = tiles.length;
        this.tiles = new int[n][n];
        this.twin = new int[n][n];


        int blankRow = 0;
        int blankColumn = 0;
        int[][] swap = new int[2][2];
        for (int i = 0, k = 0; i < n; ++i) {
          for (int j = 0; j < n; ++j) {
              this.tiles[i][j] = tiles[i][j];
              this.twin[i][j] = tiles[i][j];
              int solution = i * n + (j + 1);
              if (tiles[i][j] != 0) {
                  // store swap indices for twin board
                  if (k < 2) {
                      swap[k][0] = i;
                      swap[k][1] = j;
                      ++k;
                  }

                  if (tiles[i][j] != solution) {
                      int row = (tiles[i][j] - 1) / n;
                      int col = tiles[i][j] - 1 - (n * row);
                      manhattan += Math.abs(row - i) + Math.abs(col - j);
                      ++hamming;
                  }
              }
              else {
                  blankRow = i;
                  blankColumn = j;
              }
          }
        }

        // swap twin tiles
        int tmp = twin[swap[0][0]][swap[0][1]];
        twin[swap[0][0]][swap[0][1]] = twin[swap[1][0]][swap[1][1]];
        twin[swap[1][0]][swap[1][1]] = tmp;

        neighbors = new ArrayList<>();
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int k = 0; k < direction.length; ++k) {
            int newBlankRow = blankRow + direction[k][0];
            int newBlankColumn = blankRow + direction[k][1];
            if (newBlankRow >= 0 && newBlankRow < n && newBlankColumn >=0 && newBlankColumn < n) {
                int[][] temp = new int[n][n];
                for (int i = 0; i < n; ++i) {
                    for (int j = 0; j < n; ++j) {
                        if (i == newBlankRow && j == newBlankColumn ) {
                            temp[i][j] = 0; // blank
                        }
                        else if (i == blankRow && j == blankColumn) {
                            temp[i][j] = this.tiles[newBlankRow][newBlankColumn];
                        }
                        else {
                            temp[i][j] = this.tiles[i][j];
                        }
                    }
                }

                neighbors.add(new Neighbor(temp));
            }
        }
    }

    public int manhattan() {
        return manhattan;
    }

    public int hamming() {
        return hamming;
    }

    public boolean isGoal() {
        return manhattan == 0;
    }

    public Board twin() {
        return new Board(twin);
    }

    public Iterable<Board> neighbors()
    {
        if (neighboringBoards != null) {
            return neighboringBoards;
        }

        neighboringBoards = new ArrayList<>();
        for (Neighbor n : neighbors) {
            neighboringBoards.add(n.getBoard());
        }

        return neighboringBoards;
    }

    public int dimension() {
        return n;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(tiles);
    }

    private class Neighbor {
        private int[][] boardTiles;
        public Neighbor(int[][] boardTiles) {
            this.boardTiles = boardTiles;
        }

        public Board getBoard() {
            return new Board(boardTiles);
        }
    }
}
