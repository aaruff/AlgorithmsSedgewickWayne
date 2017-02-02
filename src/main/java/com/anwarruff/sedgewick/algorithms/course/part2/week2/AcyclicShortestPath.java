package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

/**
 * Created by aruff on 2/1/17.
 */
public class AcyclicShortestPath {
    private DirectedEdge[] edgeTo;
    private double[] pathWeightTo;

    public AcyclicShortestPath(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        pathWeightTo = new double[G.V()];

        // Initialize all vertex path weights to pos infinity
        for (int v = 0; v < G.V(); v++) {
            pathWeightTo[v] = Double.POSITIVE_INFINITY;
        }

        // Set the source to zero
        pathWeightTo[0] = 0.0;

        // Perform the topological sort
        Topological top = new Topological(G);
        for (int v : top.order()) {
            relax(G, v);
        }
    }

    // relax all vertices incident to v
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge edgeToW : G.adj(v)) {
            int w = edgeToW.to();
            // Update the path weight from s->w if the current path from s->v + v->w is less than the previous path s->w.
            if ((pathWeightTo[v] + edgeToW.weight()) < pathWeightTo[w]) {
                pathWeightTo[w]  = pathWeightTo[v] + edgeToW.weight();
                edgeTo[w] = edgeToW;
            }
        }
    }
}
