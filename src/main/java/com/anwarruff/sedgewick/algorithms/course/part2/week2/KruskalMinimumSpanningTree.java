package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

/**
 * Created by aruff on 1/25/17.
 */
public class KruskalMinimumSpanningTree {
    private Queue<Edge> minimumSpanningTreeQueue;

    public KruskalMinimumSpanningTree(EdgeWeightedGraph G) {
        minimumSpanningTreeQueue = new Queue<>();
        MinPQ<Edge> edgePQ = new MinPQ<>();
        UF vertexSet = new UF(G.V());

        while(!edgePQ.isEmpty() && minimumSpanningTreeQueue.size() < G.V() - 1) {
            Edge edge = edgePQ.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (!vertexSet.connected(v, w)) {
                vertexSet.union(v, w);
                minimumSpanningTreeQueue.enqueue(edge);
            }
        }
    }

    public Iterable<Edge> edges() {
        return minimumSpanningTreeQueue;
    }
}
