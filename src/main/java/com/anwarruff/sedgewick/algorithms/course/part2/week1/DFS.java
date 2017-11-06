package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

public class DFS {
    private final boolean[] visited;
    private final int[] from;
    private int v;

    public DFS(Graph G, int v) {
        this.v = v;
        this.visited = new boolean[G.V()];
        this.from = new int[G.V()];
        this.from[v] = -1;

        search(G, v);
    }

    private void search(Graph G, int v) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                from[w] = v;
                search(G, w);
            }
        }
    }

    public boolean hasPathTo(int w) {
        return visited[w];
    }

    public Iterable<Integer> pathTo(int w) {
        if (!hasPathTo(w)) {
            return null;
        }

        Stack<Integer> stack = new Stack<>();
        for (int previousNode = w; previousNode != v; previousNode = from[previousNode]) {
            stack.push(previousNode);
        }
        stack.push(v);

        return stack;
    }
}
