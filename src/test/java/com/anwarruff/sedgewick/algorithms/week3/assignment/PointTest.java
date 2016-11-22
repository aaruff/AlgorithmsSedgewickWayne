package com.anwarruff.sedgewick.algorithms.week3.assignment;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by aruff on 11/18/16.
 */
public class PointTest {
    @Test
    public void testComparator() {
        Point p1 = new Point(0, 0);

        Point p2 = new Point(0, 0);
        assertTrue(p1.compareTo(p2) == 0);
        assertTrue(p2.compareTo(p1) == 0);

        Point p3 = new Point(0, 1);
        assertTrue(p1.compareTo(p3) < 0);
        assertTrue(p3.compareTo(p1) > 0);

        Point p4 = new Point(1, 1);
        assertTrue(p1.compareTo(p4) < 0);
        assertTrue(p4.compareTo(p1) > 0);

        Point p5 = new Point(1, 0);
        assertTrue(p1.compareTo(p5) < 0);
        assertTrue(p5.compareTo(p1) > 0);

        Point p6 = new Point(1, -1);
        assertTrue(p1.compareTo(p6) > 0);
        assertTrue(p6.compareTo(p1) < 0);

        Point p7 = new Point(0, -1);
        assertTrue(p1.compareTo(p7) > 0);
        assertTrue(p7.compareTo(p1) < 0);

        Point p8 = new Point(-1, -1);
        assertTrue(p1.compareTo(p8) > 0);
        assertTrue(p8.compareTo(p1) < 0);

        Point p9 = new Point(-1, 0);
        assertTrue(p1.compareTo(p9) > 0);
        assertTrue(p9.compareTo(p1) < 0);

        Point p10 = new Point(-1, 1);
        assertTrue(p1.compareTo(p10) < 0);
        assertTrue(p10.compareTo(p1) > 0);
    }

    @Test
    public void testSlopeTo() {
        Point p1 = new Point(0, 0);

        // Same position
        Point p2 = new Point(0, 0);
        assertTrue(p1.slopeTo(p2) == Double.NEGATIVE_INFINITY);
        assertTrue(p2.slopeTo(p1) == Double.NEGATIVE_INFINITY);

        // Above
        Point p3 = new Point(0, 1);
        assertTrue(p1.slopeTo(p3) == Double.POSITIVE_INFINITY);
        assertTrue(p3.slopeTo(p1) == Double.POSITIVE_INFINITY);

        // Top Right
        Point p4 = new Point(1, 1);
        assertTrue(p1.slopeTo(p4) > 0.0);
        assertTrue(p4.slopeTo(p1) > 0.0);

        // Horizontal
        Point p5 = new Point(1, 0);
        assertTrue(p1.slopeTo(p5) == +0.0);
        assertTrue(p5.slopeTo(p1) == +0.0);

        // Bottom Right
        Point p6 = new Point(1, -1);
        assertTrue(p1.slopeTo(p6) < 0.0);
        assertTrue(p6.slopeTo(p1) < 0.0);

        // Below
        Point p7 = new Point(0, -1);
        assertTrue(p1.slopeTo(p7) == Double.POSITIVE_INFINITY);
        assertTrue(p7.slopeTo(p1) == Double.POSITIVE_INFINITY);

        // Bottom Left
        Point p8 = new Point(-1, -1);
        assertTrue(p1.slopeTo(p8) > 0.0);
        assertTrue(p8.slopeTo(p1) > 0.0);

        // Left
        Point p9 = new Point(-1, 0);
        assertTrue(p1.slopeTo(p9) == +0.0);
        assertTrue(p9.slopeTo(p1) == +0.0);

        // Upper left
        Point p10 = new Point(-1, 1);
        assertTrue(p1.slopeTo(p10) < 0.0);
        assertTrue(p10.slopeTo(p1) < 0.0);
    }

    @Test
    public void testSlopeOrder() {
        Point point = new Point(0, 0);
        Point above = new Point(0, 1);
        Point right = new Point(1, 0);
        Point left = new Point(-1, 0);
        Point topRight = new Point(1, 1);
        Point topLeft = new Point(-1, 1);
        Point bottomRight = new Point(1, -1);

        // degenerate vs vertical
        assertTrue(point.slopeOrder().compare(point, above) < 0);

        // vertical vs vertical
        assertTrue(point.slopeOrder().compare(above, new Point(0, 2)) == 0);
        assertTrue(point.slopeOrder().compare(above, new Point(0, -1)) == 0);

        // vertical vs positive slope
        assertTrue(point.slopeOrder().compare(above, topRight) > 0);

        // equal positive slopes
        assertTrue(point.slopeOrder().compare(topRight, new Point(2,2)) == 0);

        // greater vs lesser slope
        assertTrue(point.slopeOrder().compare(new Point(1, 100), topRight) > 0);

        // degenerate vs horizontal
        assertTrue(point.slopeOrder().compare(point, right) < 0);

        // vertical vs horizontal
        assertTrue(point.slopeOrder().compare(above, right) > 0);

        // horizontal vs horizontal
        assertTrue(point.slopeOrder().compare(right, new Point(2, 0)) == 0);
        assertTrue(point.slopeOrder().compare(right, left) == 0);

        // degenerate vs negative slope
        assertTrue(point.slopeOrder().compare(point, bottomRight) < 0);

        // positive slope vs negative slope
        assertTrue(point.slopeOrder().compare(topRight, topLeft) > 0);

        // horizontal vs negative slope
        assertTrue(point.slopeOrder().compare(right, bottomRight) > 0);

        // vertical vs negative slope
        assertTrue(point.slopeOrder().compare(above, bottomRight) > 0);
    }
}
