package com.anwarruff.sedgewick.algorithms.course.part1.week1.assignment;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationOld
{
    private static final boolean OPEN = true;

    private static final int AUX_SITES = 2;
    private static final int TOP_SITE = 0;
    private static final int BOTTOM_SITE = 1;

    private enum Shift {UP, DOWN, LEFT, RIGHT}

    private final int width;
    private final WeightedQuickUnionUF topDownGraph, bottomUpGraph;

    private boolean percolation;

    private boolean[][] siteState;

    public PercolationOld(int width)
    {
        if (width <= 0) {
            throw new IllegalArgumentException();
        }

        this.width = width;
        percolation = false;
        siteState = new boolean[width][width];
        topDownGraph = new WeightedQuickUnionUF(width * width + AUX_SITES);
        bottomUpGraph = new WeightedQuickUnionUF(width * width + AUX_SITES);
    }

    public void open(int i, int j)
    {
        if (isOutOfBounds(i-1, j-1)) {
            throw new IndexOutOfBoundsException();
        }

        openSite(i-1, j-1);
    }

    public boolean isOpen(int i, int j)
    {
        if (isOutOfBounds(i-1, j-1)) {
            throw new IndexOutOfBoundsException();
        }

        return isSiteOpen(i-1, j-1);
    }

    public boolean isFull(int i, int j)
    {
        if (isOutOfBounds(i-1, j-1)) {
            throw new IndexOutOfBoundsException();
        }

        return isSiteFull(i-1, j-1);
    }

    public boolean percolates()
    {
        return percolation;
    }

    /* --------------------------------------------------------------------------
     *                          Private Methods
     * --------------------------------------------------------------------------*/
    private boolean isSiteFull(int row, int col)
    {
        return this.topDownGraph.connected(flatten(row, col), TOP_SITE);
    }

    private boolean isSiteOpen(int row, int col)
    {
        return this.siteState[row][col];
    }

    private void openSite(int row, int col)
    {
        if ( ! isSiteOpen(row, col)) {
            this.siteState[row][col] = OPEN;
            connectAdjacentOpenSites(row, col);

            if (isTopBottomConnected(row, col)) {
                this.percolation = true;
            }
        }
    }

    private boolean isOutOfBounds(int row, int col)
    {
        return (row < 0 || row >= width || col < 0 || col >= width);
    }


    private void connectAdjacentOpenSites(int row, int col)
    {
        int siteIndex = flatten(row, col);
        for(Shift direction : Shift.values()) {
            if ( isShiftOutOfBounds(row , col, direction)) {
                continue;
            }

            int shiftedRow = shift(row, direction);
            int shiftedCol = shift(col, direction);
            if (isSiteOpen(shiftedRow, shiftedCol)) {
                topDownGraph.union(siteIndex, flatten(shiftedRow, shiftedCol));
                bottomUpGraph.union(siteIndex, flatten(shiftedRow, shiftedCol));
            }
        }

        if (row == 0) {
            topDownGraph.union(siteIndex, TOP_SITE);
        }

        if (row == width - 1) {
            bottomUpGraph.union(siteIndex, BOTTOM_SITE);
        }
    }

    private boolean isTopBottomConnected(int row, int col)
    {
        int siteIndex = flatten(row, col);
        return topDownGraph.connected(siteIndex, TOP_SITE) && bottomUpGraph.connected(siteIndex, BOTTOM_SITE);
    }

    private boolean isShiftOutOfBounds(int row, int col, Shift shift)
    {
        switch(shift) {
            case UP:
                return isOutOfBounds(row-1, col);
            case DOWN:
                return isOutOfBounds(row+1, col);
            case LEFT:
                return isOutOfBounds(row, col-1);
            case RIGHT:
                return isOutOfBounds(row, col+1);
            default:
                throw new IndexOutOfBoundsException("Shift not specified.");
        }
    }

    private int shift(int pos, Shift shift)
    {
        switch(shift) {
            case UP:   case LEFT:
                return pos - 1;
            case DOWN: case RIGHT:
                return pos + 1;
            default:
                return pos;
        }
    }

    private int flatten(int row, int col)
    {
        return width *row + col + AUX_SITES;
    }
}
