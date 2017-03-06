package com.anwarruff.sedgewick.algorithms.course.part2.week3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by aruff on 3/6/17.
 */
public class CountingSortTest {
    @Test
    public void testAlgorithm() throws Exception {
        int[] input = new int[]{1, 2, 3, 4, 2, 0, 1, 3, 4};
        int[] solution = new int[]{0, 1, 1, 2, 2, 3, 3, 4, 4};
        int[] sorted = CountingSort.sort(input);
        for (int i = 0; i < solution.length; i++) {
            assertEquals(solution[i], sorted[i]);
        }
    }
}