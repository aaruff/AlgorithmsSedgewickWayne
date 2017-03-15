package com.anwarruff.sedgewick.algorithms.course.part2.week3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 3/14/17.
 */
public class KeyIndexCountingSortTest {
    @Test
    public void testAlgorithm() throws Exception {
        int[] input = new int[]{0, 1, 2, 1, 0, 0};
        int[] output = new int[]{0, 0, 0, 1, 1, 2};
        int[] sorted = KeyIndexCountingSort.sort(input);
        for (int i = 0; i < output.length; i++) {
            assertEquals(output[i], sorted[i]);
        }
    }
}