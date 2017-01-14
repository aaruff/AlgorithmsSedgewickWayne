package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import edu.princeton.cs.algs4.Stack;

/**
 * Created by aruff on 1/14/17.
 */
public class DepthFirstDirectedPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;
    public DepthFirstDirectedPaths(Digraph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (! hasPathTo(v)) {
            return null;
        }

        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }

        path.push(s);
        return path;
    }
}
