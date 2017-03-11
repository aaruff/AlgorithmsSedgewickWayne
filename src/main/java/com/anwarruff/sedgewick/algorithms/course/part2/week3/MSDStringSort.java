package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/10/17.
 */
public class MSDStringSort {
    private static int RADIX = 256;
    private static final int ZERO_OFFSET = 1;
    private static final int OFFSET = 2; // 1 (zero) + 1 (negative one offset)
    private static final int CUTOFF = 15;
    private static String[] aux;

    // Returns the ASCII integer value of character at the specified position.
    private static int charAt(String s, int position) {
        return (position < s.length()) ? s.charAt(position) : -1;
    }

    public static void sort(String[] strings) {
        int numStrings = strings.length;
        aux = new String[numStrings];
        sort(strings, 0, numStrings-1, 0);
    }

    private static void sort(String[] strings, int low, int high, int position) {
        if (high <= low + CUTOFF) {
            insertionSort(strings, low, high, position);
            return;
        }

        // Count frequencies
        final int COUNT_SIZE = RADIX + OFFSET;
        int[] count = new int[COUNT_SIZE];
        for (int i = low; i <= high; i++) {
            int charInt = charAt(strings[i], position);
            count[charInt + OFFSET]++;
        }

        // Transform counts to indices
        for (int p = 1; p < COUNT_SIZE; p++) {
            count[p] += count[p - 1];
        }

        // Using indices in count[] to distribute strings into aux[]
        for (int i = low; i <= high; i++) {
            int charInt = charAt(strings[i], position);
            aux[count[charInt + ZERO_OFFSET]++] = strings[i];
        }

        // Note: aux[] is accessed from 0 to (high - low), where strings[] is accessed from low to high
        for (int i = low; i <= high; i++) {
            strings[i] = aux[i - low];
        }

        // Recursively sort remaining columns
        for (int r = 0; r < RADIX; r++) {
            sort(strings, low + count[r], low + count[r + 1] - 1, position + 1);
        }
    }

    private static void insertionSort(String[] str, int low, int high, int position) {
        for (int i = low; i <= high; i++) {
            for (int j = i; j > low && isLess(str[j], str[j-1], position); j--) {
                swap(str, j, j-1);
            }
        }
    }

    private static void swap(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean isLess(String stringA, String stringB, int position) {
        for (int i = position; i < Math.min(stringA.length(), stringB.length()); i++) {
            if (stringA.charAt(i) < stringB.charAt(i)) {
                return true;
            }
            if (stringA.charAt(i) > stringB.charAt(i)) {
                return false;
            }
        }

        // If the strings are equal from position to the min length, then the string with the fewest characters is less
        return stringA.length() < stringB.length();
    }
}
