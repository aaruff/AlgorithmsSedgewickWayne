package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/9/17.
 */
public class LSDStringSort {
    public static void sort(String[] strings, int fixedLength) {
        final int RADIX = 256;
        String[] sorted = new String[strings.length];

        for (int column = fixedLength-1; column >= 0; --column) {
            int[] count = new int[RADIX - 1];
            // Count the frequencies
            for (int i = 0; i < strings.length; i++) {
                count[strings[i].charAt(column) + 1]++;
            }
            // Count the characters before each char
            for (int r = 0; r < RADIX; r++) {
                count[r + 1] += count[r];
            }
            // Sort
            for (int i = 0; i < strings.length; i++) {
                sorted[ count[strings[i].charAt(column)]++] = strings[i];
            }
            // Put the sorted result back into the input array
            for (int i = 0; i < strings.length; i++) {
                strings[i] = sorted[i];
            }
        }
    }
}
