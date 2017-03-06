package com.anwarruff.sedgewick.algorithms.course.part2.week3;

/**
 * Created by aruff on 3/5/17.
 */
public class CountingSort {
    private int[] solution;

    public CountingSort(int[] input) {
        int[] position = new int[maxInteger(input)];

        for (int i = 0; i < input.length; i++) {
            ++position[input[i]];
        }

        for (int i = 1; i <= input.length; ++i) {
            position[i] += position[i-1];
        }

        solution = new int[input.length];
        for (int i = input.length - 1; i >= 0; --i) {
            solution[position[input[i]-1]] = input[i];
            --position[input[i]];
        }
    }

    private int maxInteger(int[] values) {
        int max = 0;
        for (Integer val : values) {
            if (val > max) {
                max = val;
            }
        }

        return max;
    }
}
