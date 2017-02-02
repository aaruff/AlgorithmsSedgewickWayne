package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 * Created by aruff on 2/1/17.
 */
public class DijkstraShortestPath {
    private DirectedEdge[] edgeTo;
    private double[] pathWeightTo;
    private IndexMinPQ<Double> pq;

    public DijkstraShortestPath(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        pathWeightTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        for (int v = 0; v < G.V(); v++) {
            pathWeightTo[v] = Double.POSITIVE_INFINITY;
        }

        pathWeightTo[s] = 0.0;
        while (!pq.isEmpty()) {
            relax(G, pq.delMin());
        }
    }

    // relax all vertices incident to v
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge edgeToW : G.adj(v)) {
            int w = edgeToW.to();
            if ((pathWeightTo[v] + edgeToW.weight()) < pathWeightTo[w]) {
                pathWeightTo[w] = pathWeightTo[v] + edgeToW.weight();
                edgeTo[w] = edgeToW;
                if (pq.contains(w)) {
                   pq.changeKey(w, pathWeightTo[w]);
                }
                else {
                    pq.insert(w, pathWeightTo[w]);
                }
            }
        }
    }

    public double distTo(int v) {
        return pathWeightTo[v];
    }

    public boolean hasPathTo(int v) {
        return pathWeightTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }

        Stack<DirectedEdge> path = new Stack<>();
        for (DirectedEdge edgeToVertex = edgeTo[v]; edgeToVertex != null; edgeToVertex = edgeTo[edgeToVertex.from()]) {
            path.push(edgeToVertex);
        }

        return path;
    }

}
