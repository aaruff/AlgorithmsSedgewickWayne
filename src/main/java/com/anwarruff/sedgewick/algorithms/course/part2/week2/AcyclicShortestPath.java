package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.Topological;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.DirectedEdge;

import java.util.ArrayList;

/**
 * Created by aruff on 2/1/17.
 */
public class AcyclicShortestPath {
    private DirectedEdge[] edgeTo;
    private double[] shortestDistanceTo;

    public AcyclicShortestPath(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        shortestDistanceTo = new double[G.V()];

        // Initialize all vertex path weights to pos infinity
        for (int v = 0; v < G.V(); v++) {
            shortestDistanceTo[v] = Double.POSITIVE_INFINITY;
        }

        // Set the source to zero
        shortestDistanceTo[0] = 0.0;

        // Perform the topological sort
        Topological top = new Topological(G);
        for (int v : top.order()) {
            relax(G, v);
        }
    }

    // relax all vertices incident to v
    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge edge : G.adj(v)) {
            int w = edge.to();
            double edgeWeight = edge.weight();
            // Update the path weight from s->w if the current path from s->v + v->w is less than the previous path s->w.
            if ((shortestDistanceTo[v] + edgeWeight) < shortestDistanceTo[w]) {
                shortestDistanceTo[w]  = shortestDistanceTo[v] + edgeWeight;
                edgeTo[w] = edge;
            }
        }
    }

    public ArrayList<DirectedEdge> getPaths() {
        ArrayList<DirectedEdge> list = new ArrayList<>();
        for (int v = 0; v < edgeTo.length; v++) {
            if (edgeTo[v] == null) {
                list.add(new DirectedEdge(v, v, 0.0));
            }
            else {
                DirectedEdge e = edgeTo[v];
                list.add(new DirectedEdge(e.from(), e.to(), shortestDistanceTo[v]));
            }
        }

        return list;
    }
}
