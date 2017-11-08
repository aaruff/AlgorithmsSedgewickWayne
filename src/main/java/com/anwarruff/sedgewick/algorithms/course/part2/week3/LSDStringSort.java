package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/9/17.
 */
public class LSDStringSort {
    public static void sort(String[] strings, int length) {
        final int RADIX = 256;
        final int NUM_STRINGS = strings.length;

        String[] sortedStrings = new String[NUM_STRINGS];

        // loop though each character from right to left in the strings array
        for (int p = length-1; p >= 0; --p) {
            // first index is reserved for use in the sorting phase
            int OFFSET = 1;
            int[] count = new int[RADIX + OFFSET];

            // Count the frequencies
            for (int i = 0; i < NUM_STRINGS; i++) {
                count[strings[i].charAt(p) + OFFSET]++;
            }

            // Count the characters before each char
            for (int r = 0; r < RADIX; r++) {
                count[r + 1] += count[r];
            }

            // Sort
            for (int i = 0; i < NUM_STRINGS; i++) {
                sortedStrings[ count[strings[i].charAt(p)]++] = strings[i];
            }

            // Put the sorted result back into the input array
            for (int i = 0; i < NUM_STRINGS; i++) {
                strings[i] = sortedStrings[i];
            }
        }
    }
}
