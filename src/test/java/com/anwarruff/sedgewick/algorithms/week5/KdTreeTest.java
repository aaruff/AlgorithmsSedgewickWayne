package com.anwarruff.sedgewick.algorithms.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aruff on 12/7/16.
 */
public class KdTreeTest {
    @Test
    public void testInsert() {
        KdTree kdTree = new KdTree();
        // row 0 (root)
        Point2D p1 = new Point2D(0.3,0.7);
        kdTree.insert(p1);

        // row 1 (right)
        Point2D p2 = new Point2D(0.8,0.1);
        kdTree.insert(p2);

        // row 1 (left)
        Point2D p3 = new Point2D(0.25,0.7);
        kdTree.insert(p3);

        // row 2 (left) (left)
        Point2D p4 = new Point2D(0.15,0.5);
        kdTree.insert(p4);

        // row 2 (left) (right)
        Point2D p5 = new Point2D(0.15,0.75);
        kdTree.insert(p5);
    }

    @Test
    public void testContains() {
        KdTree kdTree = new KdTree();
        double[][] points = {
                {0.3, 0.7}, {0.8, 0.1}, {0.25, 0.7}, {0.15, 0.5}, {0.15, 0.75}
        };

        for (int i = 0; i < points.length; ++i) {
            double x = points[i][0], y = points[i][1];
            kdTree.insert(new Point2D(x, y));
        }

        assertFalse(kdTree.contains(new Point2D(0.1, 0.1)));
        for (int i = 0; i < points.length; ++i) {
            double x = points[i][0], y = points[i][1];
            assertTrue(String.format("(%.2f, %.2f)", x, y), kdTree.contains(new Point2D(x, y)));
        }
    }

//    @Test
//    public void testLevelOrderIterator() {
//        KdTree kdTree = new KdTree();
//        ArrayList<Point2D> pointList = new ArrayList<>();
//        double[][] points = {
//                {0.3, 0.7}, {0.8, 0.1}, {0.25, 0.7}, {0.15, 0.5}, {0.15, 0.75}
//        };
//
//        double[][] rowOrder = {
//                {0.3, 0.7}, {0.25, 0.7}, {0.8, 0.1}, {0.15, 0.5}, {0.15, 0.75}
//        };
//
//
//        for (int i = 0; i < points.length; ++i) {
//            kdTree.insert(new Point2D(points[i][0], points[i][1]));
//            pointList.add(new Point2D(rowOrder[i][0], rowOrder[i][1]));
//        }
//
//        int i = 0;
//        for (Point2D p : kdTree.levelOrder()) {
//            assertEquals(p, pointList.get(i++));
//        }
//    }

    @Test
    public void testIsEmpty() {
        KdTree kdTree = new KdTree();
        assertTrue(kdTree.isEmpty());

        Point2D p1 = new Point2D(0.3,0.7);
        kdTree.insert(p1);

        assertFalse(kdTree.isEmpty());
        assertEquals(1, kdTree.size());

    }

    @Test
    public void testSize() {
        KdTree kdTree = new KdTree();
        Point2D p1 = new Point2D(0.3,0.7);
        kdTree.insert(p1);
        assertEquals(1, kdTree.size());

        Point2D p2 = new Point2D(0.0,0.7);
        kdTree.insert(p2);
        assertEquals(2, kdTree.size());

    }

    @Test
    public void testRange() {
        KdTree kdTree = new KdTree();
        double[][] points = {
                {0.5, 0.5}, {0.2, 0.7}, {0.1, 0.2}, {0.8, 0.3}, {0.8, 0.7}, {.3, .9}
        };
        for (int i = 0; i < points.length; ++i) {
            kdTree.insert(new Point2D(points[i][0], points[i][1]));
        }
        Point2D[] intersection = kdTree.range(new RectHV(0.0, 0.1, 0.3, 0.8));
        assertEquals(2, intersection.length);
        assertEquals(new Point2D(0.2, 0.7), intersection[0]);
        assertEquals(new Point2D(0.1, 0.2), intersection[1]);
    }
}