package com.anwarruff.sedgewick.algorithms.week4.assignment;

/**
 * Created by aruff on 11/28/16.
 */
public class Board {
    private int[][] tiles;
    private int n;
    public Board(int[][] tiles) {
        if (tiles == null) {
            throw new NullPointerException();
        }

        n = tiles.length;

        this.tiles = new int[n][n];
        for (int i = 0; i < n; ++i) {
          for (int j = 0; j < n; ++j) {
              this.tiles[i][j] = tiles[i][j];
          }
        }
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int solution = i * n + (j + 1);
                if (tiles[i][j] != solution && tiles[i][j] != 0) {
                    int row = (tiles[i][j]-1) / n;
                    int col = tiles[i][j]-1 - (n * row);
                    sum += Math.abs(row-i) + Math.abs(col - j);
                }
            }
        }

        return sum;
    }

    public int hamming() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int solution = i * n + (j + 1);
                if (tiles[i][j] != solution && tiles[i][j] != 0) {
                    ++sum;
                }
            }
        }

        return sum;

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
