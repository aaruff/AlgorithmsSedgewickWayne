package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.EdgeWeightedDirectedCycle;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

/**
 * Created by aruff on 2/6/17.
 */
public class BellmanFordShortestPath {
    private double[] shortestDistanceTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordShortestPath(EdgeWeightedDigraph G, int s) {
        shortestDistanceTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<>();

        initializeDistances(G, s);

        queue.enqueue(s);
        onQ[s] = true;

        while (!queue.isEmpty() && !this.hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = true;
            relax(G, v);
        }
    }


    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge edge : G.adj(v)) {
            int w = edge.to();
            double edgeWeight = edge.weight();
            // Update the path weight from s->w if the current path from s->v + v->w is less than the previous path s->w.
            if ((shortestDistanceTo[v] + edgeWeight) < shortestDistanceTo[w]) {
                shortestDistanceTo[w]  = shortestDistanceTo[v] + edgeWeight;
                edgeTo[w] = edge;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }

            if (cost++ % G.V() == 0) {
                findNegativeCycle();
            }
        }
    }

    public void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spanningTree;
        spanningTree = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                spanningTree.addEdge(edgeTo[v]);
            }
        }

        EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spanningTree);
        cycle = cf.cycle();
    }

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }

    private void initializeDistances(EdgeWeightedDigraph G, int s) {
        for (int v = 0; v < G.V(); v++) {
            shortestDistanceTo[v] = Double.POSITIVE_INFINITY;
        }
        shortestDistanceTo[s] = 0.0;
    }
}
