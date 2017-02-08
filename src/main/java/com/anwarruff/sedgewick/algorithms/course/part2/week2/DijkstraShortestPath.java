package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.ArrayList;

/**
 * Created by aruff on 2/1/17.
 */
public class DijkstraShortestPath {
    private DirectedEdge[] edgeTo;
    private double[] weightOf;
    private IndexMinPQ<Double> pq;

    public DijkstraShortestPath(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        weightOf = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        initWeights(G, s);
        pq.insert(s, weightOf[s]);

        findShortestPath(G);
    }

    public DijkstraShortestPath(EdgeWeightedDigraph G, int s, int haltVertex) {
        edgeTo = new DirectedEdge[G.V()];
        weightOf = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());

        initWeights(G, s);
        pq.insert(s, weightOf[s]);

        findShortestPath(G, haltVertex);
    }

    private void findShortestPath(EdgeWeightedDigraph G) {
        while (!pq.isEmpty()) {
            int w = pq.delMin();
            relax(G, w);
        }
    }

    private void findShortestPath(EdgeWeightedDigraph G, int haltVertex) {
        while (!pq.isEmpty()) {
            int w = pq.delMin();
            relax(G, w);
            if (w == haltVertex) {
                break;
            }
        }
    }

    private void initWeights(EdgeWeightedDigraph G, int s) {
        for (int v = 0; v < G.V(); v++) {
            weightOf[v] = Double.POSITIVE_INFINITY;
        }

        // Setup initial conditions
        weightOf[s] = 0.0;
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge edgeVtoW : G.adj(v)) {
            int w = edgeVtoW.to();
            double thisEdgeWeightToW = weightOf[v] + edgeVtoW.weight();
            if (thisEdgeWeightToW < weightOf[w]) {
                if (pq.contains(w)) {
                    pq.changeKey(w, thisEdgeWeightToW);
                } else {
                    pq.insert(w, thisEdgeWeightToW);
                }
                weightOf[w] = thisEdgeWeightToW;
                edgeTo[w] = edgeVtoW;
            }
        }
    }

    public double distTo(int v) {
        return weightOf[v];
    }

    public ArrayList<DirectedEdge> getPaths() {
        ArrayList<DirectedEdge> list = new ArrayList<>();
        for (int v = 0; v < edgeTo.length; v++) {
            if (edgeTo[v] == null) {
                list.add(new DirectedEdge(v, v, 0.0));
            }
            else {
                DirectedEdge e = edgeTo[v];
                list.add(new DirectedEdge(e.from(), e.to(), weightOf[v]));
            }
        }

        return list;
    }


    public boolean hasPathTo(int v) {
        return weightOf[v] < Double.POSITIVE_INFINITY;
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
