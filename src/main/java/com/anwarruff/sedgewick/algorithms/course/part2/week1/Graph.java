package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import com.anwarruff.sedgewick.algorithms.textbook.chapter1.section3.Bag;

/**
 * Created by aruff on 1/1/17.
 */
public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    /**
     * Create a vertex graph with no edges.
     * @param V
     */
    public Graph(int V) {
        this.E = 0;
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int vertex = 0; vertex < this.V; vertex++) {
            adj[vertex] = new Bag<>();
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
        adj[w].add(v);
        ++E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
