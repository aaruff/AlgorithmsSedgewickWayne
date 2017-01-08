package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import com.anwarruff.sedgewick.algorithms.textbook.chapter1.section3.Queue;

/**
 * Created by aruff on 1/8/17.
 */
public class BreadthFirstSearch {
    private boolean[] marked;
    private int count;

    public BreadthFirstSearch(Graph G, int s) {
        count = 0;
        marked = new boolean[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        while ( ! queue.isEmpty()) {
            for (Integer w : G.adj(queue.deque())) {
                if ( ! marked[w]) {
                    count++;
                    marked[w] = true;
                    queue.enqueue(w);
                }
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
