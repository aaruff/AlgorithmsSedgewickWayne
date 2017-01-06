package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import com.anwarruff.sedgewick.algorithms.textbook.chapter1.section3.Bag;

public class Graph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    /**
     * Create a graph of V unconnected vertices. Edges can be added between vertices via addEdge(v).
     * @param V max number of vertices in this graph
     */
    public Graph(int V) {
        this.E = 0;
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int vertex = 0; vertex < this.V; vertex++) {
            adj[vertex] = new Bag<>();
        }
    }

    /**
     * @return the number of vertices in this graph.
     */
    public int V() {
        return V;
    }

    /**
     * @return the number of edges in this graph.
     */
    public int E() {
        return E;
    }

    /**
     * Adds an edge connecting vertices v and w
     * @param v first vertex on edge
     * @param w second vertex on edge
     */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        ++E;
    }

    /**
     * Returns an iterable collection containing all of the vertices adjacent to v.
     * @param v vertex
     * @return
     */
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
