package com.anwarruff.sedgewick.algorithms.course.part1.week3.assignment;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aruff on 11/24/16.
 */
public class FastCollinearPointsTest {
    @Test
    public void testCollinearPointsReturnsExpectedSize() {
        int[][] coordinates = {{0,-2}, {-1,-1}, {1,-1}, {0,0}, {-2, 0}, {-3,1}, {-1,1}, {1,1}, {-2,2}, {0,2}, {2,2}, {1,3}, {3,3}};
        Point[] points = new Point[coordinates.length];
        int i = 0;
        for (int[] xy : coordinates) {
            points[i++] = new Point(xy[0], xy[1]);
        }
        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(4, fastCollinearPoints.numberOfSegments());
    }

    @Test
    public void testCollinearPointsReturnsExpectedEndPoints() {
        int[][] coordinates = {{0,-2}, {-1,-1}, {1,-1}, {0,0}, {-2, 0}, {-3,1}, {-1,1}, {1,1}, {-2,2}, {0,2}, {2,2}, {1,3}, {3,3}};
        int[][] endpoints = {{-1, -1, 3, 3}, {0, -2, -3, 1}, {1, -1, -2, 2}, {-2, 0, 1, 3}};
        Point[] points = new Point[coordinates.length];
        int i = 0;
        for (int[] xy : coordinates) {
            points[i++] = new Point(xy[0], xy[1]);
            points[i-1].draw();
        }
        String[] lineSegmentStrings = new String[endpoints.length];
        for (int j = 0; j < endpoints.length; ++j) {
            Point p = new Point(endpoints[j][0], endpoints[j][1]);
            Point q = new Point(endpoints[j][2], endpoints[j][3]);
            lineSegmentStrings[j] = p + " -> " + q;
        }

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        LineSegment[] lineSegments = fastCollinearPoints.segments();

        assertEquals(lineSegments.length, lineSegmentStrings.length);

        for (int j = 0; j < lineSegments.length; ++j) {
            boolean matchFound = false;
            LineSegment lineSegment = lineSegments[j];
            for (int k = 0; k < lineSegmentStrings.length; ++k) {
                if (lineSegment.toString().equals(lineSegmentStrings[k])) {
                    matchFound = true;
                    break;
                }
            }

            assertTrue(matchFound);
        }


    }
}
