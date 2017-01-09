package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import edu.princeton.cs.algs4.Bag;

/**
 * Created by aruff on 1/9/17.
 */
public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int i = 0; i < V; ++i) {
            adj[i] = new Bag<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (Integer w : adj(v)) {
                R.addEdge(w, v);
            }
        }

        return R;
    }
}
