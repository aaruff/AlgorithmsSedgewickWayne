package com.anwarruff.sedgewick.algorithms.week4.assignment;

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

    private int n;
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new NullPointerException();
        }

        n = tiles.length;
        this.tiles = new int[n][n];
        this.twin = new int[n][n];

        int k = 0;
        int[][] swap = new int[2][2];
        for (int i = 0; i < n; ++i) {
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
          }
        }

        // swap twin tiles
        int tmp = twin[swap[0][0]][swap[0][1]];
        twin[swap[0][0]][swap[0][1]] = twin[swap[1][0]][swap[1][1]];
        twin[swap[1][0]][swap[1][1]] = tmp;
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

    public int[][] twin() {
        return twin;
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
}
