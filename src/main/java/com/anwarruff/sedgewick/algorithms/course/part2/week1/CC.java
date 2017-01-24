package com.anwarruff.sedgewick.algorithms.course.part2.week1;

/**
 * Created by aruff on 1/9/17.
 */
public class CC {
    private int count;
    private int[] id;
    private boolean[] marked;

    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        for (int s = 0; s < G.V(); ++s) {
            if ( ! marked[s]) {
                dfs(G, s);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        this.id[v] = count;
        for (int w : G.adj(v)) {
            if ( ! marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int count() {
        return count;
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int id(int v) {
        return id[v];
    }
}