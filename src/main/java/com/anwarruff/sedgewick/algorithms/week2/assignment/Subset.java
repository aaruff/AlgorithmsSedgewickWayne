package com.anwarruff.sedgewick.algorithms.week2.assignment;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by aruff on 11/14/16.
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);

        RandomizedQueue<String> r = new RandomizedQueue<>();
        while (! StdIn.isEmpty()) {
            String in = StdIn.readString();
            if (! in.trim().isEmpty()) {
                r.enqueue(in.trim());
            }
        }

        Iterator<String> iter = r.iterator();
        for(int i = 0; i < k; ++i) {
            StdOut.println(iter.next());
        }
    }
}
