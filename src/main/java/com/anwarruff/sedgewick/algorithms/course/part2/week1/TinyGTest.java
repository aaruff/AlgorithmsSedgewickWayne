package com.anwarruff.sedgewick.algorithms.course.part2.week1;

/**
 * Created by aruff on 1/3/17.
 */
public class TinyGTest {
    public static void main(String[] args) {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        Graph G = graphFactory.getGraph();
        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(G, 0);

        depthFirstPaths.printPath(3);
    }
}
