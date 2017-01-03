package com.anwarruff.sedgewick.algorithms.course.part2.week1;

/**
 * Created by aruff on 1/2/17.
 */
public class DepthFirstSearch {
    private boolean marked[];
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        count++;
        for (int w: G.adj(v)) {
            if ( ! marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }


}
