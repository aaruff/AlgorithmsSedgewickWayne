package com.anwarruff.sedgewick.algorithms.chapter1;

/**
 * Created by aruff on 12/21/16.
 */
public class TripleIntCompare {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Error: this program requires the number of arguments equal 3 not " + args.length);
        }

        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        if (a == b && b == c && a == c) {
            System.out.println("equals");
        }
        else {
            System.out.println("not equal");
        }
    }
}
