package com.anwarruff.sedgewick.algorithms.week4.assignment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aruff on 11/28/16.
 */
public class Board {
    private int[][] tiles;
    private Board twinBoard;
    private ArrayList<Board> neighbors;
    private int pivotRow;
    private int pivotColumn;
    private int hamming;
    private int manhattan;
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
              int solution = i * n + (j + 1);
              if (tiles[i][j] != 0) {
                  // calculate manhattan and hamming
                  if (tiles[i][j] != solution) {
                      int row = (tiles[i][j] - 1) / n;
                      int col = tiles[i][j] - 1 - (n * row);
                      manhattan += Math.abs(row - i) + Math.abs(col - j);
                      ++hamming;
                  }
              }
              // set blank tile
              else {
                  pivotRow  = i;
                  pivotColumn = j;
              }
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
        if (twinBoard != null) {
            return twinBoard;
        }

        int[][] copiedTiles = copyTiles(tiles);
        int tileRow1 = -1;
        int tileColumn1 = -1;
        int tileRow2 = -1;
        int tileColumn2 = -1;
        int copied = 0;
        for (int i = 0, l = n-1; i < n && copied < 2; i++, l--) {
            for (int j = 0, m = n-1; j < n && copied < 2; j++, m--) {
                if (copiedTiles[i][j] != 0 && tileRow1 < 0 && tileColumn1 < 0) {
                    tileRow1 = i;
                    tileColumn1 = j;
                    ++copied;
                }
                if (copiedTiles[l][m] != 0 && tileRow2 < 0 && tileColumn2 < 0) {
                    tileRow2 = l;
                    tileColumn2 = m;
                    ++copied;
                }
            }
        }

        swapTiles(copiedTiles, tileRow1, tileColumn1, tileRow2, tileColumn2);
        twinBoard = new Board(copiedTiles);

        return twinBoard;
    }

    private int[][] swapTiles(int[][] tiles, int row1, int col1, int row2, int col2) {
        int tmp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = tmp;

        return tiles;
    }

    private int[][] copyTiles(int[][] tiles) {
        int [][] copiedTiles = new int[tiles.length][];
        for(int i = 0; i < tiles.length; i++)
            copiedTiles[i] = tiles[i].clone();

        return copiedTiles;
    }


    public Iterable<Board> neighbors()
    {
        if (neighbors != null) {
            return neighbors;
        }

        neighbors = new ArrayList<>();
        // top
        if ((pivotRow - 1) >= 0 && (pivotRow - 1) < n) {
            int upShift = pivotRow - 1;
            neighbors.add(new Board(swapTiles(copyTiles(tiles), upShift, pivotColumn, pivotRow, pivotColumn)));
        }
        // Bottom
        if ((pivotRow + 1) >= 0 && (pivotRow + 1) < n) {
            int downShift = pivotRow + 1;
            neighbors.add(new Board(swapTiles(copyTiles(tiles), downShift, pivotColumn, pivotRow, pivotColumn)));
        }
        // Left
        if ((pivotColumn - 1) >= 0 && (pivotColumn - 1) < n) {
            int leftShift = pivotColumn - 1;
            neighbors.add(new Board(swapTiles(copyTiles(tiles), pivotRow, leftShift, pivotRow, pivotColumn)));
        }
        // Right
        if ((pivotColumn + 1) >= 0 && (pivotColumn + 1) < n) {
            int leftShift = pivotColumn + 1;
            neighbors.add(new Board(swapTiles(copyTiles(tiles), pivotRow, leftShift, pivotRow, pivotColumn)));
        }

        return neighbors;
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
