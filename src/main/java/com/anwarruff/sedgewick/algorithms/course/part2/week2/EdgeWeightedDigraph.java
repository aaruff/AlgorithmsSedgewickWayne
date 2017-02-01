package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by aruff on 2/1/17.
 */
public class EdgeWeightedDigraph {
    // number of vertices
    private final int V;
    // number of edges
    private int E;
    // adjacency list representation of an edge weighted graph
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V) {
        this.V = V;
        E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge edge) {
        adj[edge.from()].add(edge);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> bag = new Bag<>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                bag.add(e);
            }
        }

        return bag;
    }

}
