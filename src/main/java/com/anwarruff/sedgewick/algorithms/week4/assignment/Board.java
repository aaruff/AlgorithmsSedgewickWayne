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
    }

    public int hamming() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int solution = i* n + (j + 1);
                if (tiles[i][j] != solution) {
                    int row = tiles[i][j] / n;
                    int col = tiles[i][j] - (n * row) - 1;
                    sum += Math.abs(i - row) + Math.abs(j - col);
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
