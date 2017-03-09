package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/9/17.
 */
public class LSDRadixSort {
    public static void sort(String[] words, int fixedLength) {
        final int RADIX = 256;
        String[] sorted = new String[words.length];

        for (int column = fixedLength-1; column >= 0; --column) {
            int[] count = new int[RADIX - 1];
            // Count the frequencies
            for (int i = 0; i < words.length; i++) {
                count[words[i].charAt(column) + 1]++;
            }
            // Count the characters before each char
            for (int r = 0; r < RADIX; r++) {
                count[r + 1] += count[r];
            }
            // Sort
            for (int i = 0; i < words.length; i++) {
                sorted[ count[words[i].charAt(column)]++] = words[i];
            }
            // Put the sorted result back into the input array
            for (int i = 0; i < words.length; i++) {
                words[i] = sorted[i];
            }
        }
    }
}
