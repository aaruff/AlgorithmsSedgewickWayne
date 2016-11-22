package com.anwarruff.sedgewick.algorithms.week3.assignment;

import java.util.ArrayList;

/**
 * Created by aruff on 11/19/16.
 */
public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;

    /**
     * Find all line segments containing 4 points at a time, and checks whether they all lie
     * on the same line segment, returning all such line segments.
     * @param points
     */
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        ArrayList<LineSegment> segments = new ArrayList<>();
        for (int i = 0; i < points.length; ++i) {
            Point p = points[i];
            if (p == null) {
                throw new NullPointerException();
            }
            for (int j = i + 1; j < points.length; ++j) {
                Point q = points[j];
                if (q == null) {
                    throw new NullPointerException();
                }
                else if (p.compareTo(q) == 0) {
                    throw new IllegalArgumentException();
                }
                for (int k = j + 1; k < points.length; ++k) {
                    Point r = points[k];
                    if (r == null) {
                        throw new NullPointerException();
                    }
                    else if (p.compareTo(r) == 0 || q.compareTo(r) == 0) {
                        throw new IllegalArgumentException();
                    }
                    for (int l = k + 1; l < points.length; ++l) {
                        Point s = points[l];
                        if (s == null) {
                            throw new NullPointerException();
                        }
                        else if (p.compareTo(s) == 0 || q.compareTo(s) == 0 || r.compareTo(s) == 0) {
                            throw new IllegalArgumentException();
                        }

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            Point[] line = {p, q, r, s};
                            Point min = p;
                            Point max = p;
                            for (int m = 0; m < line.length; ++m) {
                                if (line[m].compareTo(max) == 1) {
                                    max = line[m];
                                }
                                if (line[m].compareTo(max) == -1) {
                                    min = line[m];
                                }
                            }

                            segments.add(new LineSegment(min, max));
                        }
                    }
                }
            }
        }

        lineSegments = segments.toArray(new LineSegment[segments.size()]);
    }

    /**
     * The number of line segments.
     * @return
     */
    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }
}
