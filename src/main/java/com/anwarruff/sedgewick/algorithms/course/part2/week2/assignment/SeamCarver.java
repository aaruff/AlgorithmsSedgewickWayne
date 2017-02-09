package com.anwarruff.sedgewick.algorithms.course.part2.week2.assignment;

import edu.princeton.cs.algs4.Picture;

import java.awt.*;

/**
 * Created by aruff on 2/9/17.
 */
public class SeamCarver {
    private Picture picture;

    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new NullPointerException();

        this.picture = new Picture(picture);
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
        if (col < 0 || col > picture.width()-1 || row < 0 || row > picture.height()-1)
            throw new IndexOutOfBoundsException();

        final int BorderPixelEnergy = 1000;
        if (col == 0 || col == picture.width()-1 || row == 0 || row == picture.height()-1)
            return BorderPixelEnergy;

        int colGradient = squaredGradient(col + 1, row, col - 1, row);
        int rowGradient = squaredGradient(col, row + 1, col, row - 1);
        return Math.sqrt(colGradient + rowGradient);
    }

    // TODO
    public int[] findHorizontalSeam() {
        int[] seam = new int[picture.width()];
        return seam;
    }

    // TODO
    public int[] findVerticalSeam() {
        int[] seam = new int[picture.height()];
        return seam;
    }

    // TODO
    private boolean isValidRowSeam(int[] seam) {
        if (seam == null)
            throw new NullPointerException();
        if (seam.length != picture.width() || picture.height() <= 1)
            throw new IllegalArgumentException();

        // TODO
        // also throw Illegal argument exception if
        // entry is outside it's prescribed range or,
        // two adjacent entries differ by more than one
        return false;
    }

    // TODO
    private boolean isValidColumnSeam(int[] seam) {
        if (seam == null)
            throw new NullPointerException();
        if (seam.length != picture.width() || picture.width() <= 1)
            throw new IllegalArgumentException();

        // TODO
        // also throw Illegal argument exception if
        // entry is outside it's prescribed range or,
        // two adjacent entries differ by more than one
        return false;
    }


    // TODO
    public void removeHorizontalSeam(int[] seam) {
        if ( ! isValidRowSeam(seam))
            return;

    }

    // TODO
    public void removeVerticalSeam(int[] seam) {
        if ( ! isValidColumnSeam(seam))
            return;

    }

}
