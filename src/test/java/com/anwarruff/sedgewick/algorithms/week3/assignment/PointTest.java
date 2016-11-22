package com.anwarruff.sedgewick.algorithms.week3.assignment;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by aruff on 11/18/16.
 */
public class PointTest {
    @Test
    public void testComparator_WhenOnePointIsSmallerThanTheOther() {
        Point p1 = new Point(0,0);
        Point p2 = new Point(1,1);

        assertTrue(p1.compareTo(p2) < 0);
        assertTrue(p2.compareTo(p1) > 0);
    }
}
