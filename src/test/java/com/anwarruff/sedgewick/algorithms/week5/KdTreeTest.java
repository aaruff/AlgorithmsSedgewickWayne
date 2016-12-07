package com.anwarruff.sedgewick.algorithms.week5;

import edu.princeton.cs.algs4.Point2D;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by aruff on 12/7/16.
 */
public class KdTreeTest {
    @Test
    public void testInsert() {
        KdTree kdTree = new KdTree();
        Point2D p1 = new Point2D(0.3,0.7);
        kdTree.insert(p1);

        Point2D p2 = new Point2D(0.8,0.1);
        kdTree.insert(p2);
    }

    @Test
    public void testLevelOrderIterator() {
        KdTree kdTree = new KdTree();
        Point2D[] points = {new Point2D(0.3,0.7), new Point2D(0.8,0.1)};
        kdTree.insert(points[0]);

        Point2D p2 = new Point2D(0.8,0.1);
        kdTree.insert(points[1]);

        int i = 0;
        for (Point2D p : kdTree.levelOrder()) {
            assertEquals(points[i++], p);
        }
    }
}