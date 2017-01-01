package com.anwarruff.sedgewick.algorithms.course.part1.week4.assignment;

import edu.princeton.cs.algs4.In;

/**
 * Created by aruff on 12/3/16.
 */
public class ExampleBoard {
    public final String fileName;
    public final int moves;

    public ExampleBoard(String fileName, int moves) {
        this.fileName = fileName;
        this.moves = moves;
    }

    public static Board boardReader(String fileName) {
        In in = new In(fileName);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        return new Board(blocks);
    }
}
