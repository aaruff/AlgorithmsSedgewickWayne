package com.anwarruff.sedgewick.algorithms.course.part2.week3;

import edu.princeton.cs.algs4.StdOut;

public class FlowEdge {
    // to deal with floating-point roundoff errors
    private static final double FLOATING_POINT_EPSILON = 1E-10;

    private final int from;
    private final int to;
    private final double capacity;
    private double flow;

    public FlowEdge(int from, int to, double capacity) {
        if (from < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (to < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (!(capacity >= 0.0)) throw new IllegalArgumentException("Edge capacity must be non-negative");
        this.from = from;
        this.to = to;
        this.capacity  = capacity;
        this.flow      = 0.0;
    }

    public FlowEdge(int from, int to, double capacity, double flow) {
        if (from < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (to < 0) throw new IllegalArgumentException("vertex index must be a non-negative integer");
        if (!(capacity >= 0.0))  throw new IllegalArgumentException("edge capacity must be non-negative");
        if (!(flow <= capacity)) throw new IllegalArgumentException("flow exceeds capacity");
        if (!(flow >= 0.0))      throw new IllegalArgumentException("flow must be non-negative");
        this.from = from;
        this.to = to;
        this.capacity  = capacity;
        this.flow      = flow;
    }

    public FlowEdge(FlowEdge e) {
        this.from = e.from;
        this.to = e.to;
        this.capacity  = e.capacity;
        this.flow      = e.flow;
    }

    public int from() {
        return from;
    }  

    public int to() {
        return to;
    }  

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    public int other(int vertex) {
        if      (vertex == from) return to;
        else if (vertex == to) return from;
        else throw new IllegalArgumentException("invalid endpoint");
    }

    public double residualCapacityTo(int vertex) {
        if      (vertex == from) return flow;              // backward edge
        else if (vertex == to) return capacity - flow;   // forward edge
        else throw new IllegalArgumentException("invalid endpoint");
    }

    public void addResidualFlowTo(int vertex, double delta) {
        if (!(delta >= 0.0)) throw new IllegalArgumentException("Delta must be nonnegative");

        if      (vertex == from) flow -= delta;           // backward edge
        else if (vertex == to) flow += delta;           // forward edge
        else throw new IllegalArgumentException("invalid endpoint");

        // round flow to 0 or capacity if within floating-point precision
        if (Math.abs(flow) <= FLOATING_POINT_EPSILON)
            flow = 0;
        if (Math.abs(flow - capacity) <= FLOATING_POINT_EPSILON)
            flow = capacity;

        if (!(flow >= 0.0))      throw new IllegalArgumentException("Flow is negative");
        if (!(flow <= capacity)) throw new IllegalArgumentException("Flow exceeds capacity");
    }

    public String toString() {
        return from + "->" + to + " " + flow + "/" + capacity;
    }


    public static void main(String[] args) {
        FlowEdge e = new FlowEdge(12, 23, 4.56);
        StdOut.println(e);
    }

}
