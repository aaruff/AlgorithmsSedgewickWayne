package com.anwarruff.sedgewick.algorithms.week3.assignment;

/**
 * Created by aruff on 11/18/16.
 */
public class Point implements Comparable<Point>{
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    /**
     * Compare two points by y-coordinate, break ties by x-coordinated.
     * @param p
     * @return
     */
    @Override
    public int compareTo(Point p) {
        if (this.y < p.getY()) {
            return -1;
        }
        else if (this.y > p.getY()) {
            return 1;
        }
        else if (this.x < p.getX()){
            return -1;
        }
        else if (this.x > p.getX()){
            return 1;
        }
        else {
            return 0;
        }
    }
}
