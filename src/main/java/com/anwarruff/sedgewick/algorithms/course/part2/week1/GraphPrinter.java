package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import java.util.Iterator;

/**
 * Created by aruff on 1/6/17.
 */
public class GraphPrinter {
    /**
     * Iterates through all vertices of the graph G, and prints the edges (v-w) connected to it.
     * @param G
     */
    public static void edgePrinter(Graph G) {
        for (int v = 0; v < G.V(); ++v) {
            for (int w : G.adj(v)) {
                System.out.println(v + " - " + w);
            }
            System.out.println("------");
        }
    }

    public static void printPath(Iterable<Integer> iterable) {

        Iterator<Integer> i = iterable.iterator();
        while (i.hasNext()) {
            System.out.print(i.next());
            if (i.hasNext())
                System.out.print(" - ");
        }
    }

}
