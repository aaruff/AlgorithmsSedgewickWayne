package com.anwarruff.sedgewick.algorithms.course.part2.week2;

/**
 * Created by aruff on 1/24/17.
 */
public class Edge {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        return (vertex == v) ? w : v;
    }

    public int compare(Edge that) {
        if (this.weight < that.weight) {
            return -1;
        }
        else if (this.weight > that.weight) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
