package com.anwarruff.sedgewick.algorithms.week3.assignment;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by aruff on 11/25/16.
 */
public class FastCollinearPointsExceptionTest {
    @Test
    public void initializingBruteForceWithNullArrayThrowsNullPointerException(){
        assertThatThrownBy(() -> new FastCollinearPoints(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void initializingBruteForceWithArrayContainingNullElementThrowsNullPointerException(){
        Point[] points = {new Point(1,2), null, new Point(3,4)};
        assertThatThrownBy(() -> new FastCollinearPoints(points)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void passingIdenticalPointsToConstructorThrowsIllegalArgumentException() {
        Point[] points = {new Point(1,2), new Point(3,4), new Point(1,2)};
        assertThatThrownBy(() -> new FastCollinearPoints(points)).isInstanceOf(IllegalArgumentException.class);
    }
}
