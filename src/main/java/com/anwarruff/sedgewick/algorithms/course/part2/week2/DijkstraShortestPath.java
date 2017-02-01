package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.IndexMinPQ;

/**
 * Created by aruff on 2/1/17.
 */
public class DijkstraShortestPath {
    private DirectedEdge[] from;
    private double[] weight;
    private IndexMinPQ<Double> pq;

    public DijkstraShortestPath(EdgeWeightedDigraph G, int s) {
        from = new DirectedEdge[G.V()];
        weight = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for (int v = 0; v < G.V(); v++) {
            weight[v] = Double.POSITIVE_INFINITY;
        }

        weight[s] = 0.0;
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    // relax all vertices incident to v
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge edge : G.adj(v)) {
            int neighbor = edge.to();
            // if the existing path weight (weight[neighbor]) is strictly greater than the
            // current path weight (weight[v] + v->neighbor.weight)
            if (weight[neighbor] > weight[v] + edge.weight()) {

            }

        }

    }

}
