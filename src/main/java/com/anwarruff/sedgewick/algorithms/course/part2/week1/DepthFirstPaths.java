package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import com.anwarruff.sedgewick.algorithms.textbook.chapter1.section3.Stack;

/**
 * Created by aruff on 1/2/17.
 */
public class DepthFirstPaths {
    private boolean marked[];
    private int[] edgeTo;
    private int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if ( ! marked[w]) {
                edgeTo[w] = v; // mark record of going to w from v
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
