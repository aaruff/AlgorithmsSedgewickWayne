package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import edu.princeton.cs.algs4.Digraph;

/**
 * Created by aruff on 1/9/17.
 */
public class DirectedDFS {
    private boolean[] marked;

    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if ( ! marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

}
