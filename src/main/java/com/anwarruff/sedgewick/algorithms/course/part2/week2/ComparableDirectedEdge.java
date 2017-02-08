package com.anwarruff.sedgewick.algorithms.course.part2.week2;

/**
 * Created by aruff on 2/8/17.
 */
public class ComparableDirectedEdge implements Comparable<ComparableDirectedEdge>{
    private final String from;
    private final String to;
    private final double weight;

    public ComparableDirectedEdge(String from, String to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(ComparableDirectedEdge other) {
        return to.compareTo(other.to);
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }

    public double weight() {
        return weight;
    }

}
