package com.anwarruff.sedgewick.algorithms.course.part1.week1.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
    private final int n;
    private final Grid grid;

    public Percolation(int n) throws IllegalArgumentException {
        if (n <= 0) {
            throw new IllegalArgumentException("Grid size is invalid.");
        }

        this.n = n;
        grid = new Grid(n);
    }

    public void open(int i, int j) throws IndexOutOfBoundsException {
        if (isOutOfBounds(i, j)) {
            throw new IndexOutOfBoundsException("Grid size is invalid.");
        }

        grid.open(i - 1, j - 1);
    }

    public boolean isOpen(int i, int j) throws IndexOutOfBoundsException {
        if (isOutOfBounds(i, j)) {
            throw new IndexOutOfBoundsException("Grid size is invalid.");
        }

        return grid.isOpen(i - 1, j - 1);
    }


    public boolean isFull(int i, int j) throws IndexOutOfBoundsException {
        if (isOutOfBounds(i, j)) {
            throw new IndexOutOfBoundsException("Grid size is invalid.");
        }

        return grid.isFull(i - 1, j - 1);
    }

    public boolean percolates()
    {
        return grid.percolates();
    }

    /* -----------------------------------------------
     *  Private
     * ------------------------------------------------- */

    private boolean isOutOfBounds(int i, int j)
    {
        return i < 1 || i > n || j < 1 || j > n;
    }

    private class Grid
    {
        private final WeightedQuickUnionUF grid;
        private final WeightedQuickUnionUF percolateGrid;
        private static final int TOP_SITE = 0;
        private static final int BOTTOM_SITE = 1;
        private static final int OFFSET = 2;
        private final int n;
        private final boolean[][] state;

        public Grid(int n)
        {
            this.n = n;
            state = new boolean[n][n];
            grid = new WeightedQuickUnionUF(OFFSET + n*n);
            percolateGrid = new WeightedQuickUnionUF(OFFSET + n*n);
            for (int j = 0; j < n; ++j) {
                grid.union(TOP_SITE, toGridId(0,j));
                percolateGrid.union(TOP_SITE, toGridId(0,j));
            }
        }

        public boolean percolates() {
            return percolateGrid.connected(TOP_SITE, BOTTOM_SITE);
        }

        public boolean isOpen(int row, int col)
        {
            return state[row][col];
        }

        public boolean isFull(int row, int col) {
            return isOpen(row, col) && grid.connected(toGridId(row, col), TOP_SITE);
        }

        public void open(int row, int col) {
            if (state[row][col]) {
                return;
            }

            state[row][col] = true;

            int site = toGridId(row, col);
            // Above
            if(0 <= (row - 1) && isOpen(row - 1, col)) {
                grid.union(site, toGridId(row - 1, col));
                percolateGrid.union(site, toGridId(row - 1, col));
            }
            // Below
            if (n > (row + 1) && isOpen(row + 1, col)) {
                grid.union(site, toGridId(row + 1, col));
                percolateGrid.union(site, toGridId(row + 1, col));
            }
            // Left
            if (0 <= (col - 1) && isOpen(row, col - 1)) {
                grid.union(site, toGridId(row, col - 1));
                percolateGrid.union(site, toGridId(row, col - 1));
            }
            // Right
            if (n > (col + 1) && isOpen(row, col + 1)) {
                grid.union(site, toGridId(row, col + 1));
                percolateGrid.union(site, toGridId(row, col + 1));
            }

            if (row == n-1) {
                percolateGrid.union(site, BOTTOM_SITE);
            }
        }

        private int toGridId(int row, int col) {
            return OFFSET + (n * row) + col;
        }

        private int toBottomId(int col) {
            return OFFSET + (n * (n-1)) + col;
        }
    }
}
