package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import edu.princeton.cs.algs4.Stack;

/**
 * Created by aruff on 1/15/17.
 */
public class DirectedCycle {
    private boolean marked[];
    private int edgeTo[];
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onStack = new boolean[G.V()];
        cycle = new Stack<>();
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (this.hasCycle()) {
                return;
            }

            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
            // w is marked and on the stack, so we have reached a loop
            else if (onStack[w]) {
                cycle = new Stack<>();
                // walk back along the path until the repeat vertex (w) is found.
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                // push on the repeat vertex (w), and the vertex from which we found the loop (v)
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    private boolean hasCycle() {
        return cycle != null;
    }
}
