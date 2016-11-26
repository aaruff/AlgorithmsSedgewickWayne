package com.anwarruff.sedgewick.algorithms.week3.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by aruff on 11/19/16.
 */
public class BruteCollinearPoints {
    private final LineSegment[] lineSegments;
    private final int EQUAL = 0;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        Point[] copy = new Point[points.length];
        System.arraycopy(points, 0, copy, 0, points.length);

        ArrayList<LineSegment> segments = new ArrayList<>();
        for (int i = 0; i < copy.length; ++i) {
            Point p = copy[i];
            if (p == null)
                throw new NullPointerException();
            for (int j = i + 1; j < copy.length; ++j) {
                Point q = copy[j];
                if (q == null)
                    throw new NullPointerException();
                if (p.compareTo(q) == EQUAL)
                    throw new IllegalArgumentException();

                for (int k = j + 1; k < copy.length; ++k) {
                    Point r = copy[k];
                    if (r == null)
                        throw new NullPointerException();
                    if (r.compareTo(p) == EQUAL || r.compareTo(q) == EQUAL)
                        throw new IllegalArgumentException();
                    for (int l = k + 1; l < copy.length; ++l) {
                        Point s = copy[l];
                        if (s == null)
                            throw new NullPointerException();
                        if (s.compareTo(p) == EQUAL || s.compareTo(q) == EQUAL || s.compareTo(r) == EQUAL)
                            throw new IllegalArgumentException();

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            ArrayList<Point> line = new ArrayList<>(Arrays.asList(p, q, r, s));
                            segments.add(new LineSegment(Collections.min(line), Collections.max(line)));
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
        LineSegment[] copy = new LineSegment[lineSegments.length];
        System.arraycopy(lineSegments, 0, copy, 0, lineSegments.length);
        return copy;
    }
}
