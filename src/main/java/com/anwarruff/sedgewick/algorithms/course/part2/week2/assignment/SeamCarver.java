package com.anwarruff.sedgewick.algorithms.course.part2.week2.assignment;

import edu.princeton.cs.algs4.Picture;

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

    // TODO
    public double energy(int column, int row) {
        if (column < 0 || column > picture.width()-1 || row < 0 || row > picture.height()-1)
            throw new IndexOutOfBoundsException();
        return 0.0;
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
