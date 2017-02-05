package com.anwarruff.sedgewick.algorithms.course.part2.week2;

/**
 * Created by aruff on 2/1/17.
 */
public class DirectedEdge<T> {
    private final T v;
    private final T w;
    private final double weight;

    public DirectedEdge(T v, T w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public T from() {
        return v;
    }

    public T to() {
        return w;
    }

    public String toString() {
        return String.format("%s->%s %.2f", String.valueOf(v), String.valueOf(w), weight);
    }
}
