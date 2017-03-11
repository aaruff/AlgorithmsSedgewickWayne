package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/10/17.
 */
public class CharIntValuePrinter {
    public static void main(String[] args) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < alphabet.length(); i++) {
            System.out.println(String.format("%c - %d", alphabet.charAt(i), (int) alphabet.charAt(i)));
        }
    }
}
