package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.DirectedEdge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by aruff on 2/6/17.
 */
public class AcyclicTestClient {
    public static void main(String[] args) {
        EdgeWeightedGraphFactory.VertexType vertexType = EdgeWeightedGraphFactory.VertexType.STRING;
        EdgeWeightedGraphFactory factory = new EdgeWeightedGraphFactory("Quiz2.txt", vertexType);
        VertexEncoder encoder = factory.getEncoding();

        ArrayList<Integer> topologicalOrder = new ArrayList<>();
        for (String s : Arrays.asList("C", "H", "D", "G", "F")) {
            topologicalOrder.add(encoder.getEncoding(s));
        }

        int source = encoder.getEncoding("C");
        AcyclicShortestPath asp = new AcyclicShortestPath(topologicalOrder, factory.getGraph(), source);

        ArrayList<ComparableDirectedEdge> edges = new ArrayList<>();
        for (DirectedEdge e : asp.getPaths()) {
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
