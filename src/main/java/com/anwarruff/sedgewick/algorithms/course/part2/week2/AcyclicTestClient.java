package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import java.util.ArrayList;

/**
 * Created by aruff on 2/6/17.
 */
public class AcyclicTestClient {
    public static void main(String[] args) {
        EdgeWeightedGraphFactory.VertexType vertexType = EdgeWeightedGraphFactory.VertexType.STRING;
        EdgeWeightedGraphFactory factory = new EdgeWeightedGraphFactory("Quiz2.txt", vertexType);
        VertexEncoder encoder = factory.getEncoding();

        AcyclicShortestPath dsp = new AcyclicShortestPath(factory.getGraph(), encoder.getEncoding("E"));
        ArrayList<DirectedEdge<String>> list = new ArrayList<>();
        for (edu.princeton.cs.algs4.DirectedEdge e : dsp.getPaths()) {
            String v = encoder.getEncoding(e.from());
            String w = encoder.getEncoding(e.to());
            double weight = e.weight();
            System.out.println(v + "->" + w + " " + String.format("%5.2f", weight));
        }

    }
}
