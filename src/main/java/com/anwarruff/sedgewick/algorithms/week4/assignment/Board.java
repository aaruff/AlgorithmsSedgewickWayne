package com.anwarruff.sedgewick.algorithms.week4.assignment;

/**
 * Created by aruff on 11/28/16.
 */
public class Board {
    private int[][] tiles;
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
        int correctTiles = 0;
        for (int i = 0; i < n; ++i) {
          for (int j = 0; j < n; ++j) {
              this.tiles[i][j] = tiles[i][j];
              int solution = i * n + (j + 1);
              if (tiles[i][j] != 0) {
                  if (tiles[i][j] != solution) {
                      int row = (tiles[i][j] - 1) / n;
                      int col = tiles[i][j] - 1 - (n * row);
                      manhattan += Math.abs(row - i) + Math.abs(col - j);
                      ++hamming;
                  }
                  else {
                      ++correctTiles;
                  }
              }
              else {
                  if (i == n-1 && j == n-1) {
                      ++correctTiles;
                  }
              }
          }
        }

        isGoal = correctTiles == n*n;
    }

    public int manhattan() {
        return manhattan;
    }

    public int hamming() {
        return hamming;
    }

    public boolean isGoal() {
        return isGoal;
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

}
