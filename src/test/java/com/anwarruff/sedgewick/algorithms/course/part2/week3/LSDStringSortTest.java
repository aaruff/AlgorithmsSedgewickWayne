package com.anwarruff.sedgewick.algorithms.course.part2.week3;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 3/10/17.
 */
public class LSDStringSortTest {
    @Test
    public void testSort() throws Exception {
        String[] input = new String[]{"xyz", "abc", "def"};
        String[] solution = new String[]{"abc", "def", "xyz"};
        LSDStringSort.sort(input, 3);
        for (int i = 0; i < solution.length; i++) {
            assertTrue(input[i].equals(solution[i]));
        }
    }
}