package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Produces a Graph given a correctly formatted resource file.
 * For formatting see: http://algs4.cs.princeton.edu/41graph/tinyCG.txt
 */
public class GraphFactory {
    private Graph graph;
    public GraphFactory(String fileName) {
        File file = new File(getClass().getClassLoader().getResource("part2/week1/" + fileName).getFile());
        try {
            Scanner scanner = new Scanner(file);

            int vertices = Integer.valueOf(scanner.nextLine());
            graph = new Graph(vertices);

            int edges = Integer.valueOf(scanner.nextLine());
            int i = 0;
            while (scanner.hasNextLine() && i < edges) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                int v = Integer.valueOf(parts[0]);
                int w = Integer.valueOf(parts[1]);
                graph.addEdge(v, w);
            }
            scanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public Graph getGraph() {
        return graph;
    }
}
