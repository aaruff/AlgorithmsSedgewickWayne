package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by aruff on 1/24/17.
 */
public class EdgeWeightedGraph {
    private final int V;
    private final Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }

    public int V() {
        return V;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
}
