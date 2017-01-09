package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by aruff on 1/9/17.
 */
public class DigraphFactory {
    private Digraph graph;
    public DigraphFactory(String fileName) {
        File file = new File(getClass().getClassLoader().getResource("part2/week1/" + fileName).getFile());
        try {
            Scanner scanner = new Scanner(file);

            int vertices = Integer.valueOf(scanner.nextLine());
            graph = new Digraph(vertices);

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

    public Digraph getGraph() {
        return graph;
    }
}
