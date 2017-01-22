package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

import edu.princeton.cs.algs4.Digraph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by aruff on 1/18/17.
 * Shortest Ancestral Path
 */
public class SAP {
    private Digraph G;

    public SAP(Digraph G) {
        if (G == null) {
            throw new NullPointerException();
        }

        this.G = new Digraph(G.V());
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                this.G.addEdge(v, w);
            }
        }
    }

    public int length(int v, int w) {
        return (new LCA(G, v, w)).length();
    }

    public int ancestor(int v, int w) {
        return (new LCA(G, v, w)).leastCommonAncestor();
    }

    public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null) {
            throw new NullPointerException();
        }

        int lcaLength = LCA.NOT_FOUND;
        for (Integer a : subsetA) {
            for (Integer b : subsetB) {
                int currentLength = (new LCA(G, a, b)).length();
                if (currentLength != LCA.NOT_FOUND && (lcaLength == LCA.NOT_FOUND || currentLength < lcaLength)) {
                    lcaLength = currentLength;
                }
            }
        }

        return lcaLength;
    }

    public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null) {
            throw new NullPointerException();
        }

        int lcaLength = LCA.NOT_FOUND;
        int lca = LCA.NOT_FOUND;
        for (Integer a : subsetA) {
            for (Integer b : subsetB) {
                LCA leastCommonAncestor = new LCA(G, a, b);
                int currentLength = leastCommonAncestor.length();
                if (currentLength != LCA.NOT_FOUND && (lcaLength == LCA.NOT_FOUND || currentLength < lcaLength)) {
                    lcaLength = currentLength;
                    lca = leastCommonAncestor.leastCommonAncestor();
                }
            }
        }

        return lca;
    }


    private class LCA {
        private Digraph G;
        private Map<Integer, Integer> distancesFromV;
        private Map<Integer, Integer> distancesFromW;
        private static final int NOT_FOUND = -1;

        public LCA(Digraph G, int v, int w) {
            if (v < 0 || v >= G.V() || w < 0 || w >= G.V()) {
                throw new IndexOutOfBoundsException();
            }

            this.G = G;
            distancesFromV = getDistancesFrom(v);
            distancesFromW = getDistancesFrom(w);
        }

        private Map<Integer, Integer> getDistancesFrom(int source) {
            LinkedList<Integer> nextToVisit = new LinkedList<>();
            Map<Integer, Integer> distance = new HashMap<>();

            nextToVisit.add(source);
            distance.put(source, 0);

            while(!nextToVisit.isEmpty()) {
                int vertex = nextToVisit.poll();
                int neighborDistance = distance.get(vertex) + 1;
                for (Integer neighbor : G.adj(vertex)) {
                    if(!distance.containsKey(neighbor) || neighborDistance < distance.get(neighbor)) {
                        distance.put(neighbor, neighborDistance);
                        nextToVisit.add(neighbor);
                    }
                }
            }

            return distance;
        }

        public int length() {
            int shortestLength = NOT_FOUND;
            for (Map.Entry<Integer, Integer> entry : distancesFromV.entrySet()) {
                int vertex = entry.getKey();
                if (distancesFromW.containsKey(vertex)) {
                    int currentLength = distancesFromW.get(entry.getKey()) + entry.getValue();
                    if (shortestLength == NOT_FOUND || currentLength < shortestLength) {
                        shortestLength = currentLength;
                    }
                }
            }

            return shortestLength;
        }

        public int leastCommonAncestor() {
            int shortestLength = NOT_FOUND;
            int leastCommonAncestor = NOT_FOUND;
            for (Map.Entry<Integer, Integer> entry : distancesFromV.entrySet()) {
                int vertex = entry.getKey();
                if (distancesFromW.containsKey(vertex)) {
                    int currentLength = distancesFromW.get(entry.getKey()) + entry.getValue();
                    if (shortestLength == NOT_FOUND || currentLength < shortestLength) {
                        shortestLength = currentLength;
                        leastCommonAncestor = vertex;
                    }
                }
            }

            return leastCommonAncestor;
        }
    }

}
