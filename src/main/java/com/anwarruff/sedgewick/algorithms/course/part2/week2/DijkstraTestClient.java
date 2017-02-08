package com.anwarruff.sedgewick.algorithms.course.part2.week2;


import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by aruff on 2/4/17.
 */
public class DijkstraTestClient {
    public static void main(String[] args) {
        EdgeWeightedGraphFactory.VertexType vertexType = EdgeWeightedGraphFactory.VertexType.STRING;
        EdgeWeightedGraphFactory factory = new EdgeWeightedGraphFactory("Quiz1.txt", vertexType);
        VertexEncoder encoder = factory.getEncoding();

        int sourceVertex = encoder.getEncoding("B");
        int stopVertex = encoder.getEncoding("G");
        DijkstraShortestPath dsp = new DijkstraShortestPath(factory.getGraph(), sourceVertex, stopVertex);

        ArrayList<ComparableDirectedEdge> edges = new ArrayList<>();
        for (DirectedEdge e : dsp.getPaths()) {
            String from = encoder.getEncoding(e.from());
            String to = encoder.getEncoding(e.to());
            double weight = e.weight();
            edges.add(new ComparableDirectedEdge(from, to, weight));
        }

        Collections.sort(edges);

        for (ComparableDirectedEdge e : edges) {
            System.out.println(e.from() + "->" + e.to() + " " + String.format("%5.2f", e.weight()));
        }

        for (ComparableDirectedEdge e : edges) {
            System.out.print(String.format("%d ", (long) e.weight()));
        }

    }
}
