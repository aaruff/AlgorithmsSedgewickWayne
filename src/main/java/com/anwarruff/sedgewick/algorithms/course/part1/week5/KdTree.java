package com.anwarruff.sedgewick.algorithms.course.part1.week5;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

/**
 * Created by aruff on 12/6/16.
 */
public class KdTree {
    private Node root;             // root of BST

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

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Point2D point) {
        if (point == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(root, point, 0) != null;
    }

    private RectHV get(Node node, Point2D point, int level) {
        if (node == null) return null;

        boolean xAxis = level%2 == 0;
        boolean yAxis = ! xAxis;
        double nodeAxis = (xAxis) ? node.point.x() : node.point.y();
        double nodeAltAxis = (yAxis) ? node.point.x() : node.point.y();
        double pointAxis = (xAxis) ? point.x() : point.y();
        double pointAltAxis = (yAxis) ? point.x() : point.y();

        if (pointAxis < nodeAxis || (pointAxis == nodeAxis && pointAltAxis < nodeAltAxis)) {
            return get(node.left, point, level + 1);
        }
        else if (pointAxis > nodeAxis || pointAxis == nodeAxis && pointAltAxis > nodeAltAxis) {
            return get(node.right, point, level + 1);
        }
        else {
            return node.rectangle;
        }
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public void insert(Point2D point) {
        if (point == null) throw new IllegalArgumentException("first argument to insertPoint() is null");
        root = insertPoint(root, point, 0.0, 0.0, 1.0, 1.0, 0);
    }

    private Node insertPoint(Node node, Point2D point, double xmin, double ymin, double xmax, double ymax, int level) {
        if (node == null) {
            return new Node(point, new RectHV(xmin, ymin, xmax, ymax), null, null, level, 1);
        }

        boolean xAxis = level%2 == 0;
        boolean yAxis = ! xAxis;
        double npx = node.point.x();
        double npy = node.point.y();
        double px = point.x();
        double py = point.y();
        double nodeAxis = (xAxis) ? npx : npy;
        double nodeAltAxis = (yAxis) ? npx : npy;
        double pointAxis = (xAxis) ? px : py;
        double pointAltAxis = (yAxis) ? px : py;

        // When the next node is to the left or down the (xmax,ymax) rectangle point changes
        if (pointAxis < nodeAxis || (pointAxis == nodeAxis && pointAltAxis < nodeAltAxis)) {
            if (xAxis) {
                xmax = npx;
                ymax = node.rectangle.ymax();
            }
            else {
                ymax = npy;
                xmax = node.rectangle.xmax();
            }
            node.left  = insertPoint(node.left, point, xmin, ymin, xmax, ymax, level+1);
        }
        // Right or Up
        else if (pointAxis > nodeAxis || (pointAxis == nodeAxis && pointAltAxis > nodeAltAxis)) {
            if (xAxis) {
                xmin = npx;
                ymin = node.rectangle.ymin();
            }
            else {
                ymin = npy;
                xmin = node.rectangle.xmin();
            }
            node.right  = insertPoint(node.right,  point, xmin, ymin, xmax, ymax, level+1);
        }
        else {
            node.rectangle = new RectHV(xmin, ymin, xmax, ymax);
        }

         node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Iterable<Node> levelOrder() {
        Queue<Node> keys = new Queue<>();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node node = queue.dequeue();
            if (node == null) continue;
            keys.enqueue(node);
            queue.enqueue(node.left);
            queue.enqueue(node.right);
        }
        return keys;
    }

    public void draw() {
        for (Node n : levelOrder()) {
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLACK);
            n.point.draw();
            // vertical
            if (n.level%2 == 0) {
                StdDraw.setPenRadius(0.001);
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(n.point.x(), n.rectangle.ymin(), n.point.x(), n.rectangle.ymax());
            }
            else {
                StdDraw.setPenRadius(0.001);
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(n.rectangle.xmin(), n.point.y(), n.rectangle.xmax(), n.point.y());
            }
        }
    }

    public Point2D nearest(Point2D queryPoint) {
        if (queryPoint == null) return null;
        Node node = nearestPoint(root, queryPoint, root, 0);
        if (node == null) {
            return null;
        }

        return node.point;
    }

    /**
     * Goal: Find query point q
     *
     * @param node
     * @param query
     * @param oldNearest
     * @param level
     * @return
     */
    private Node nearestPoint(Node node, Point2D query, Node oldNearest, int level) {
        if (node == null) {
            return oldNearest;
        }

        double nodeDistance = node.point.distanceSquaredTo(query);
        double oldNearestDistance = oldNearest.point.distanceSquaredTo(query);
        double rectDistance = node.rectangle.distanceSquaredTo(query);
        if (oldNearestDistance < rectDistance) {
            return oldNearest;
        }

        Node champion = (nodeDistance < oldNearestDistance) ? node : oldNearest;

        boolean splitX = level%2 == 0;
        boolean splitY = ! splitX;
        double queryX = query.x();
        double queryY = query.y();
        double nodeX = node.point.x();
        double nodeY = node.point.y();

        Node firstDirection, secondDirection;
        if (splitX && (queryX < nodeX || (queryX == nodeX && queryY < nodeY))
                || splitY && (queryY < nodeY || (queryY == nodeY && queryX < nodeX))) {
           firstDirection = node.left;
           secondDirection = node.right;
        }
        else if (splitX && (queryX > nodeX || (queryX == nodeX && queryY > nodeY))
                || splitY && (queryY > nodeY || (queryY == nodeY && queryX > nodeX))) {
            firstDirection = node.right;
            secondDirection = node.left;
        }
        else {
            return champion;
        }

        champion = nearestPoint(firstDirection, query, champion, level + 1);


        // If the champ point is closer than the nodes
        // query point and the rectangle corresponding to the node,
        // there is no need to explore that node, or it's sub tree
        // pruning rule
        if (champion.point.distanceSquaredTo(query) < rectDistance) {
            return champion;
        }
        else {
            return nearestPoint(secondDirection, query, champion, level + 1);
        }
    }


    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) return null;

        ArrayList<Point2D> intersection = new ArrayList<>();
        range(root, rect, 0, intersection);
        return intersection;
    }

    private void range(Node node, RectHV searchRect, int level, ArrayList<Point2D> intersection) {
        if (node == null) {
            return;
        }

        boolean vertical = level%2 == 0;
        double searchMax = (vertical) ? searchRect.xmax() : searchRect.ymax();
        double searchMin = (vertical) ? searchRect.xmin() : searchRect.ymin();
        double splitAxis = (vertical) ? node.point.x() : node.point.y();

        // search rectangle contains current point
        if (searchRect.contains(node.point)) {
            intersection.add(node.point);
            range(node.left, searchRect, level+1, intersection);
            range(node.right, searchRect, level + 1, intersection);
        }
        // axis splits rectangle
        else if (searchMin <= splitAxis && splitAxis <= searchMax) {
            range(node.left, searchRect, level+1, intersection);
            range(node.right, searchRect, level + 1, intersection);
        }
        // rectangle to the left or bottom of this point
        else if (searchMax < splitAxis){
            range(node.left, searchRect, level+1, intersection);
        }
        // rectangle to the above or right of this point
        else {
            range(node.right, searchRect, level + 1, intersection);
        }
    }
}
