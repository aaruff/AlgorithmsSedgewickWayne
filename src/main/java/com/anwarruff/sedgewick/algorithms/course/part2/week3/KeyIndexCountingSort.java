package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/14/17.
 */
public class KeyIndexCountingSort {
    public static int[] sort(int[] input) {
        int max = 0;
        // Find the max value in input array
        for (int i = 0; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        // Increase the size to max plus 1 for the offset, and 1 for the zero digit
        int[] count = new int[max + 2];
        // Count frequencies
        for (int i = 0; i < input.length; ++i) {
            count[input[i] + 1]++;
        }

        // Convert frequency count to value index ranges
        for (int i = 1; i < count.length; ++i) {
            count[i] += count[i-1];
        }

        int[] output = new int[input.length];
        // Distribute input values into output using the ordered positions in the count array
        for (int i = 0; i < input.length; ++i) {
            output[count[input[i]]++] = input[i];
        }

        return output;
    }
}
