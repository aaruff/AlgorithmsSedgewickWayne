package com.anwarruff.sedgewick.algorithms.course.part2.week2.assignment;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

/**
 * Created by aruff on 2/9/17.
 */
public class SeamCarver {
    private Picture picture;
    private int rows;
    private int columns;

    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new NullPointerException();

        this.picture = new Picture(picture);
        rows = picture.height();
        columns = picture.width();


    }

    public Picture picture() {
        return picture;
    }

    public int width() {
        return picture.width();
    }

    public int height() {
        return picture.height();
    }

    private int squaredGradient(int xf, int yf, int xi, int yi) {
        Color ci = picture.get(xi, yi);
        Color cf = picture.get(xf, yf);
        int redDiff = cf.getRed() - ci.getRed();
        int greenDiff = cf.getGreen() - ci.getGreen();
        int blueDiff = cf.getBlue() - ci.getBlue();

        return redDiff*redDiff + greenDiff*greenDiff + blueDiff*blueDiff;
    }

    public double energy(int col, int row) {
        if (col < 0 || col > columns-1 || row < 0 || row > rows-1)
            throw new IndexOutOfBoundsException("column = " + col + ", row = " + row);

        final int BorderPixelEnergy = 1000;
        if (col == 0 || col == columns-1 || row == 0 || row == rows-1)
            return BorderPixelEnergy;

        int colGradient = squaredGradient(col + 1, row, col - 1, row);
        int rowGradient = squaredGradient(col, row + 1, col, row - 1);
        return Math.sqrt(colGradient + rowGradient);
    }

    public int[] findHorizontalSeam() {
        if (columns == 1) {
            return new int[] {0};
        }

        int[][] from = new int[rows][columns];
        double[][] weight = new double[rows][columns];

        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                if (col == 0) {
                    weight[row][col] = energy(col, row);
                    from[row][col] = row;
                }
                else {
                    relaxHorizontalEdges(row, col, from, weight);
                }
            }
        }

        int[] path = new int[columns];
        int minRow = findMinRow(weight, columns - 1);
        for (int col = columns - 1; col >= 0; col--) {
            path[col] = minRow;
            minRow = from[minRow][col];
        }

        return path;
    }

    public int[] findVerticalSeam() {
        if (rows == 1) {
            return new int[] {0};
        }

        int[][] from = new int[rows][columns];
        double[][] weight = new double[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                if (row == 0) {
                    weight[row][col] = energy(col, row);
                    from[row][col] = col;
                }
                else {
                    relaxVerticalEdges(row, col, from, weight);
                }
            }
        }

        int[] path = new int[rows];
        int minColumn = findMinColumn(weight, rows - 1);
        for (int row = rows - 1; row >= 0; row--) {
            path[row] = minColumn;
            minColumn = from[row][minColumn];
        }

        return path;
    }

    private void relaxVerticalEdges(int row, int col, int[][] from, double[][] weight) {
        int parentRow = row - 1;
        double parentWeight = weight[parentRow][col];

        double middleChildWeight = energy(col, row) + parentWeight;
        if (weight[row][col] == 0 || middleChildWeight < weight[row][col]) {
            weight[row][col] = middleChildWeight;
            from[row][col] = col;
        }

        if (col > 0 && col <= columns - 1) {
            double leftChildWeight = energy(col - 1, row) + parentWeight;
            if (weight[row][col - 1] == 0 || leftChildWeight < weight[row][col - 1]) {
                weight[row][col - 1] = leftChildWeight;
                from[row][col - 1] = col;
            }
        }

        if (col >= 0 && col < columns - 1) {
            double rightChildWeight = energy(col + 1, row) + parentWeight;
            if (weight[row][col + 1] == 0 || rightChildWeight < weight[row][col + 1]) {
                weight[row][col + 1] = rightChildWeight;
                from[row][col + 1] = col;
            }
        }
    }

    private void relaxHorizontalEdges(int row, int col, int[][] from, double[][] weight) {
        int parentColumn = col - 1;
        double parentWeight = weight[row][parentColumn];

        double middleChildWeight = energy(col, row) + parentWeight;
        if (weight[row][col] == 0 || middleChildWeight < weight[row][col]) {
            weight[row][col] = middleChildWeight;
            from[row][col] = row;
        }

        if (row > 0 && row <= rows - 1) {
            double topChildWeight = energy(col, row - 1) + parentWeight;
            if (weight[row - 1][col] == 0 || topChildWeight < weight[row - 1][col]) {
                weight[row - 1][col] = topChildWeight;
                from[row - 1][col] = row;
            }
        }

        if (row >= 0 && row < rows - 1) {
            double bottomChildWeight = energy(col, row + 1) + parentWeight;
            if (weight[row + 1][col] == 0 || bottomChildWeight < weight[row + 1][col]) {
                weight[row + 1][col] = bottomChildWeight;
                from[row + 1][col] = row;
            }
        }
    }

    private int findMinColumn(double[][] cost, int index) {
        int minIndex = 0;
        for (int i = 1; i < columns; i++) {
            if (cost[index][minIndex] > cost[index][i]) {
                minIndex = i;
            }
        }

        return minIndex;
    }

    private int findMinRow(double[][] cost, int index) {
        int minIndex = 0;
        for (int i = 1; i < rows; i++) {
            if (cost[minIndex][index] > cost[i][index]) {
                minIndex = i;
            }
        }

        return minIndex;
    }

    private boolean isValidRowSeam(int[] seam) {
        if (seam == null)
            throw new NullPointerException();
        if (seam.length != columns)
            throw new IllegalArgumentException();

        for (int i = 0; i < seam.length; i++) {
           if (seam[i] < 0 || seam[i] >= rows) {
               throw new IllegalArgumentException();
           }
           if (i > 0) {
               int diff = seam[i] - seam[i-1];
               if (diff != 1 && diff != -1 && diff != 0) {
                   throw new IllegalArgumentException();
               }
           }
        }
        return true;
    }


    private boolean isValidColumnSeam(int[] seam) {
        if (seam == null)
            throw new NullPointerException();
        if (seam.length != rows)
            throw new IllegalArgumentException();

        for (int i = 0; i < seam.length; i++) {
            if (seam[i] < 0 || seam[i] >= columns) {
                throw new IllegalArgumentException();
            }
            if (i > 0) {
                int diff = seam[i] - seam[i-1];
                if (diff != 1 && diff != -1 && diff != 0) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return true;
    }


    public void removeHorizontalSeam(int[] seam) {
        if ( ! isValidRowSeam(seam))
            return;

        Picture picture = new Picture(columns, rows - 1);
        for (int col = 0; col < columns; col++) {
            int skip = seam[col];
            for (int row = 0, prow = 0; row < rows; row++) {
                if (row == skip)
                    continue;
                picture.set(col, prow, this.picture.get(col, row));
                ++prow;
            }
        }

        this.picture = picture;
        rows = picture.height();
        columns = picture.width();
    }

    public void removeVerticalSeam(int[] seam) {
        if ( ! isValidColumnSeam(seam))
            return;

        Picture picture = new Picture(columns - 1, rows);
        for (int row = 0; row < rows; row++) {
            int skip = seam[row];
            for (int col = 0, pcol = 0; col < columns; col++) {
                if (col == skip)
                    continue;
                picture.set(pcol, row, this.picture.get(col, row));
                ++pcol;
            }
        }

        this.picture = picture;
        rows = picture.height();
        columns = picture.width();
    }

}
