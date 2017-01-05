package com.anwarruff.sedgewick.algorithms.course.part2.week1;

/**
 * Created by aruff on 1/5/17.
 */
public class TestSearch {
    public static void test(Graph G, int s) {
        DepthFirstSearch search = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v)) {
                System.out.print(v + " ");
            }
        }
        System.out.println();

        if (search.count() != G.V()) {
            System.out.println("NOT ");
        }
        System.out.println("connected");
    }

    public static void main(String[] args) {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        Graph G = graphFactory.getGraph();
        System.out.println(G);
        TestSearch.test(G, 3);
    }
}
