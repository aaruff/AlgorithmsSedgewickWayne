package com.anwarruff.sedgewick.algorithms.course.part2.week2;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by aruff on 2/4/17.
 */
public class EdgeWeightedGraphFactory {
    private EdgeWeightedDigraph graph;
    private VertexEncoder vertexEncoder;
    public enum VertexType {STRING, INTEGER}

    public EdgeWeightedGraphFactory(String fileName, VertexType vertexType) {
        File file = new File(getClass().getClassLoader().getResource("part2/week2/" + fileName).getFile());
        vertexEncoder = new VertexEncoder();
        try {
            Scanner scanner = new Scanner(file);

            int vertices = Integer.valueOf(scanner.nextLine());
            int edges = Integer.valueOf(scanner.nextLine());

            graph = new EdgeWeightedDigraph(vertices);

            int i = 0;
            while (scanner.hasNextLine() && i < edges) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split("\\s+");

                int v, w;
                if (vertexType == VertexType.STRING) {
                    String vID = parts[0].trim();
                    if ( ! vertexEncoder.isEncoded(vID)) {
                        vertexEncoder.encode(vID);
                    }

                    String wID = parts[1].trim();
                    if ( ! vertexEncoder.isEncoded(wID)) {
                        vertexEncoder.encode(wID);
                    }

                    v = vertexEncoder.getEncoding(vID);
                    w = vertexEncoder.getEncoding(wID);
                }
                else {
                    v = Integer.valueOf(parts[0]);
                    w = Integer.valueOf(parts[1]);
                }

                double weight = Double.valueOf(parts[2]);
                DirectedEdge e = new DirectedEdge(v, w, weight);
                graph.addEdge(e);
            }
            scanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public VertexEncoder getEncoding() {
        return vertexEncoder;
    }

    public EdgeWeightedDigraph getGraph() {
        return graph;
    }

}
