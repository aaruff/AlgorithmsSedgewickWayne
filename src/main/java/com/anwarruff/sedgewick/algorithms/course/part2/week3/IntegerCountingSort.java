package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/5/17.
 */
public class IntegerCountingSort {

    private static int maxInteger(int[] values) {
        int max = 0;
        for (int val : values) {
            if (val > max) {
                max = val;
            }
        }

        return max;
    }

    public static int[] sort(int[] input) {
        int[] position = new int[maxInteger(input) + 1];

        for (int i = 0; i < input.length; i++) {
            ++position[input[i]];
        }

        for (int i = 1; i < position.length; ++i) {
            position[i] += position[i - 1];
        }

        int[] solution = new int[input.length];
        for (int i = input.length - 1; i >= 0; --i) {
            solution[position[input[i]]-1] = input[i];
            --position[input[i]];
        }

        return solution;
    }
}
