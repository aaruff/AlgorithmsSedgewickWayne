package com.anwarruff.sedgewick.algorithms.week3.assignment;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Created by aruff on 11/18/16.
 */
public class Point implements Comparable<Point>{
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param p the other point
     */
    public void drawTo(Point p) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, p.x, p.y);
    }

    /**
     * The slope is defined to be
     * +0.0                         -> if the line segment is horizontal;
     * Double.POSITIVE_INFINITY     -> if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY -> if both points are equal.
     *
     * Otherwise the slope is
     * is (y1 - y0) / (x1 - x0).
     *
     * @param  p the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point p) {
        double HORIZONTAL = +0.0;
        double VERTICAL = Double.POSITIVE_INFINITY;
        double DEGENERATE = Double.NEGATIVE_INFINITY;
        if (p.y == y && p.x == x) {
            return DEGENERATE;
        }
        else if (p.x == x) {
            return VERTICAL;
        }
        else if (p.y == y) {
            return HORIZONTAL;
        }
        else {
            return (double) (p.y - y) / (double) (p.x - x);
        }
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  p the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point p) {
        int LESS = -1;
        int GREATER = 1;
        int EQUAL = 0;
        if (this.y < p.y) {
            return LESS;
        }
        else if (this.y > p.y) {
            return GREATER;
        }
        else if (this.x < p.x){
            return LESS;
        }
        else if (this.x > p.x){
            return GREATER;
        }
        else {
            return EQUAL;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        int LESS = -1;
        int GREATER = 1;
        int EQUAL = 0;
        return (p1, p2) -> {
            if (slopeTo(p1) < slopeTo(p2)) {
                return LESS;
            }
            else if (slopeTo(p1) > slopeTo(p2)) {
                return GREATER;
            }
            else {
                return EQUAL;
            }
        };
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
