package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/10/17.
 */
public class MSDStringSort {
    private static int R = 256;
    private static final int cutoff = 15;
    private static String[] aux;

    private static int charAt(String s, int position) {
        return (position < s.length()) ? s.charAt(position) : -1;
    }

    public static void sort(String[] strings) {
        int N = strings.length;
        aux = new String[N];
        sort(strings, 0, N-1, 0);
    }

    private static void sort(String[] strings, int low, int high, int position) {
        if (high <= low + cutoff) {
            insertionSort(strings, low, high, position);
            return;
        }

        // Count frequencies
        int[] count = new int[R + 2];
        for (int i = low; i < high; i++) {
            count[charAt(strings[i], position) + 2]++;
        }

        // Count to Indices
        for (int radix = 0; radix < R+1; radix++) {
            count[radix + 1] += count[radix];
        }

        // Copy back
        for (int i = low; i <= high; i++) {
            aux[count[charAt(strings[i], position) + 1]++] = strings[i];
        }

        // Recursively sort remaining columns
        for (int r = 0; r < R; r++) {
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
