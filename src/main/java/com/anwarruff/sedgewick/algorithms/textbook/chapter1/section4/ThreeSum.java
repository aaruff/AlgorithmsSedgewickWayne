package com.anwarruff.sedgewick.algorithms.textbook.chapter1.section4;

/**
 * Created by aruff on 12/29/16.
 */
public class ThreeSum {
    /**
     * Counts the number of times when 3 numbers chosen from a list of n equal zero.
     * Note that the total number of ways to choose 3 from n is if we check every grouping is:
     * (n Choose 3) = n! / 3!(n-3)! = n*(n-1)*(n-2) / 6 = (n^2-n)(n-2) / 6 = (n^3 -3n^2 -2n) / 6.
     * Which is O(n^3)
     * @param a
     * @return
     */
    public static int count(int[] a) {
        int count = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        ++count;
                    }
                }
            }
        }

        return count;
    }
}
