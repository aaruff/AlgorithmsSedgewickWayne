package com.anwarruff.sedgewick.algorithms.course.part2.week1;

/**
 * Created by aruff on 1/3/17.
 */
public class TinyGPrinter {
    public static void main(String[] args) {
        GraphFactory graphFactory = new GraphFactory("tinyCG.txt");
        System.out.println(graphFactory.getGraph());
    }
}
