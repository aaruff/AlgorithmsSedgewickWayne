package com.anwarruff.sedgewick.algorithms.week4.assignment;

import java.util.ArrayList;

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
        this.tiles = copyTiles(tiles);
    }

    public int manhattan() {
        int man = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (tiles[i][j] != 0) {
                    int row = (tiles[i][j] - 1) / n;
                    int col = (tiles[i][j] - 1) % n;
                    man += Math.abs(row - i) + Math.abs(col - j);
                }
            }
        }

        return man;
    }

    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int solution = i * n + (j + 1);
                if (tiles[i][j] != solution && tiles[i][j] != 0) {
                    ++hamming;
                }
            }
        }

        return hamming;
    }

    public boolean isGoal() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int solution = i * n + (j + 1);
                if (tiles[i][j] != solution && (i != (n-1) || j != (n-1))) {
                    return false;
                }
            }
        }

        return true;
    }

    public Board twin() {
        int[][] copiedTiles = copyTiles(tiles);
        for (int i = 0, l = n-1 ; i < n; i++, l--) {
            for (int j = 0, m = n-1; j < n; j++, m--) {
                if (copiedTiles[i][j] != 0 && copiedTiles[l][m] != 0) {
                    swapTiles(copiedTiles, i, j, l, m);
                    return new Board(copiedTiles);
                }
            }
        }

        return new Board(copiedTiles);
    }

    private int[][] swapTiles(int[][] tiles, int i, int j, int l, int m) {
        int tmp = tiles[i][j];
        tiles[i][j] = tiles[l][m];
        tiles[l][m] = tmp;

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
        ArrayList<Board> neighbors = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
               if (tiles[i][j] == 0)  {
                   // up
                   if (i > 0) {
                       neighbors.add(new Board(swapTiles(copyTiles(tiles), i - 1, j, i, j)));
                   }
                   // Bottom
                   if (i < n - 1) {
                       neighbors.add(new Board(swapTiles(copyTiles(tiles), i + 1, j, i, j)));
                   }
                   // Left
                   if (j > 0) {
                       neighbors.add(new Board(swapTiles(copyTiles(tiles), i, j - 1, i, j)));
                   }
                   // Right
                   if (j < n - 1) {
                       neighbors.add(new Board(swapTiles(copyTiles(tiles), i, j + 1, i, j)));
                   }

                   return neighbors;

               }
            }
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

        if (board.tiles.length != n) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != board.tiles[i][j]) {
                    return false;
                }

            }
        }

        return true;
    }
}
