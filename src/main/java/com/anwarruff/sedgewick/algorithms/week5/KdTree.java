package com.anwarruff.sedgewick.algorithms.week5;

import edu.princeton.cs.algs4.*;

/**
 * Created by aruff on 12/6/16.
 */
public class KdTree {
    private Node root;             // root of BST
    private int size;

    private class Node {
        private Point2D point;           // sorted by key
        private RectHV rectangle;         // associated data
        private Node left;
        private Node right;  // left and right subtrees
        private int level;
        private int size;

        public Node(Point2D point, RectHV rectangle, Node left, Node right, int level, int size) {
            this.point = point;
            this.rectangle = rectangle;
            this.left = left;
            this.right = right;
            this.level = level;
            this.size = size;
        }
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public void insert(Point2D point) {
        if (point == null) throw new IllegalArgumentException("first argument to put() is null");
        root = put(root, point, 0);
    }

    private Node put(Node parent, Point2D point, int level) {
        if (parent == null) {
            return new Node(point, new RectHV(0.0,0.0,0.0,0.0), null, null, level, 1);
        }

        double parentCoordinate = (level%2 == 0) ? parent.point.x() : parent.point.y();
        double pointCoordinate = (level%2 == 0) ? point.x() : point.y();

            if (pointCoordinate < parentCoordinate) {
                parent.left  = put(parent.left,  point, level+1);
            }
            else if (pointCoordinate > parentCoordinate) {
                parent.right  = put(parent.right,  point, level+1);
            }
            else {
                parent.rectangle = new RectHV(0.0, 0.0, 0.0, 0.0);
            }

             parent.size = 1 + size(parent.left) + size(parent.right);
            return parent;
    }

    public Iterable<Point2D> levelOrder() {
        Queue<Point2D> keys = new Queue<>();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x.point);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    public void draw() {

    }

    public KdTree nearest(Point2D query) {
        return null;
    }

    public Point2D[] range(RectHV rect) {
        return new Point2D[0];
    }
}
