package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

/**
 * Created by aruff on 1/26/17.
 */
public class PrimLazyMinimumSpanningTree {
    private boolean[] marked;
    private Queue<Edge> minimumSpanningTree;
    private MinPQ<Edge> minPriorityQueue;

    public PrimLazyMinimumSpanningTree(EdgeWeightedGraph G) {
        minPriorityQueue = new MinPQ<>();
        minimumSpanningTree = new Queue<>();
        marked = new boolean[G.V()];

        visit(G, 0);
        while (!minPriorityQueue.isEmpty()) {
            Edge edge = minPriorityQueue.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (!marked[v] || !marked[w]) {
                minimumSpanningTree.enqueue(edge);
                if (!marked[v]) {
                    visit(G, v);
                }
                if (!marked[w]) {
                    visit(G, w);
                }
            }
        }
    }

    public void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge edge : G.adj(v)) {
            if (!marked[edge.other(v)]) {
                minPriorityQueue.insert(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return minimumSpanningTree;
    }
}
