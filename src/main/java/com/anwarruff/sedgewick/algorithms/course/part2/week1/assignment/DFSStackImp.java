package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

public class DFSStackImp {
    private boolean[] visited;
    private int[] from;

    public DFSStackImp(Graph g, int s) {
        visited = new boolean[g.V()];
        from = new int[g.V()];
    }

    private void dfs(Graph g, int s) {
        Stack<Integer> stack = new Stack<>();

        visited[s] = true;
        stack.push(s);

        while (!stack.isEmpty()) {
            // look for an unmarked vertex
            for (Integer w : g.adj(stack.peek())) {
                if (!visited[w]) {
                    from[w] = stack.peek();
                    visited[w] = true;
                    stack.push(w);
                }
            }
            stack.pop();
        }
    }

    public boolean hasPathTo(int w) {
        return visited[w];
    }
}
