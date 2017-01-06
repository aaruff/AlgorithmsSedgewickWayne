package com.anwarruff.sedgewick.algorithms.course.part2.week1;

/**
 * Created by aruff on 1/6/17.
 */
public class GraphProcessor {
    /**
     * @param G graph
     * @param v vertex
     * @return the degree of vertex v in graph G
     */
    public static int degree(Graph G, int v) {
        int numAdjacentVertices = 0;
        for (int ignored : G.adj(v)) {
            ++numAdjacentVertices;
        }

        return numAdjacentVertices;
    }

    /**
     * @param G
     * @return the largest degree of any node in graph G.
     */
    public static int maxDegree(Graph G) {
        int maxDegree = 0;
        for (int vertex = 0; vertex < G.V(); vertex++) {
            int degree = degree(G, vertex);
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }

    /**
     * @param G
     * @return the avg degree of edges in the graph G.
     */
    public static double agvDegree(Graph G) {
        return ( 2.0 * G.E())/G.V();
    }

    /**
     * @param G
     * @return the number of self loops in the graph G.
     */
    public static int numberOfSelfLoops(Graph G) {
        int selfLoops = 0;
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (w == v) {
                    ++selfLoops;
                }
            }
        }
        return selfLoops/2;
    }
}
