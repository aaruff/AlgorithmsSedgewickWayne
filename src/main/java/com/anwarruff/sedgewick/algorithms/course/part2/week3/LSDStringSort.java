package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/9/17.
 */
public class LSDStringSort {
    public static void sort(String[] strings, int stringLength) {
        final int RADIX = 256;
        final int NUM_STRINGS = strings.length;

        String[] sortedStrings = new String[NUM_STRINGS];

        for (int position = stringLength-1; position >= 0; --position) {
            int[] count = new int[RADIX + 1]; // zero'th index left blank
            // Count the frequencies
            for (int i = 0; i < NUM_STRINGS; i++) {
                count[strings[i].charAt(position) + 1]++;
            }
            // Count the characters before each char
            for (int r = 0; r < RADIX; r++) {
                count[r + 1] += count[r];
            }
            // Sort
            for (int i = 0; i < NUM_STRINGS; i++) {
                sortedStrings[ count[strings[i].charAt(position)]++] = strings[i];
            }
            // Put the sorted result back into the input array
            for (int i = 0; i < NUM_STRINGS; i++) {
                strings[i] = sortedStrings[i];
            }
        }
    }
}
