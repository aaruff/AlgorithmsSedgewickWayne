package com.anwarruff.sedgewick.algorithms.week3.assignment;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by aruff on 11/21/16.
 */
public class BruteCollinearPointsTest {
    @Test
    public void testSegments() {
        Point[] points = {new Point(-1,-1), new Point(1,1), new Point(0,2), new Point(2,2), new Point(1,3), new Point(3,3)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        LineSegment[] lineSegments = bruteCollinearPoints.segments();
        assertTrue(lineSegments.length == 1);
    }
    @Test
    public void testnumberOfSegments() {
        Point[] points = {new Point(-1,-1), new Point(1,1), new Point(0,2), new Point(2,2), new Point(1,3), new Point(3,3)};
        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        assertTrue(bruteCollinearPoints.numberOfSegments() == 1);
    }
}
