package com.anwarruff.sedgewick.algorithms.course.part1.week1;

import edu.princeton.cs.algs4.StdOut;

/**
 * Created by aruff on 6/2/16.
 */
public class BinarySearchNaive implements BinarySearch
{
    public int binarySearch(int[] a, int key)
    {
        int l = 0, m;
        int h = a.length - 1;
        while (l <= h) {
            m = (h-l)/2 + l;
            if (key < a[m])
                h = m - 1;
            else if (key > a[m])
                l = m + 1;
            else
                return m;
        }
        return -1;
    }

    public static void main(String[] args)
    {
        BinarySearch binarySearch = new BinarySearchNaive();
        for (int i = 1; i <= 10; ++i) {
            int index = binarySearch.binarySearch(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, i);
            if (index < 0)  {
               StdOut.println("Failed to find key = " + i);
            }
            else {
                StdOut.println("Found key " + i + " at index " + index);
            }
        }
    }
}
