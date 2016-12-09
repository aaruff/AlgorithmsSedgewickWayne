package com.anwarruff.sedgewick.algorithms.week5;

import edu.princeton.cs.algs4.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Point2D point) {
        if (point == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(point) != null;
    }

    private RectHV get(Point2D point) {
        return get(root, point, 0);
    }

    private RectHV get(Node parent, Point2D point, int level) {
        if (parent == null) return null;

        double parentCoordinate = (level%2 == 0) ? parent.point.x() : parent.point.y();
        double pointCoordinate = (level%2 == 0) ? point.x() : point.y();

        if (pointCoordinate < parentCoordinate) {
            return get(parent.left, point, level + 1);
        }
        else if (pointCoordinate > parentCoordinate) {
            return get(parent.right, point, level + 1);
        }
        else {
            return parent.rectangle;
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
        RectHV rectHV = new RectHV(0.0, 0.0, 1.0, 1.0);
        root = put(root, point, rectHV, 0);
    }

    private Node put(Node parent, Point2D point, RectHV rectangle, int level) {
        if (parent == null) {
            int size = 1;
            Node leftNode = null;
            Node rightNode = null;
            return new Node(point, rectangle, leftNode, rightNode, level, size);
        }
        boolean vertical = level%2 == 0;
        double parentCoordinate = (vertical) ? parent.point.x() : parent.point.y();
        double pointCoordinate = (vertical) ? point.x() : point.y();
        double pointOtherCoordinate = (! vertical) ? point.x() : point.y();
        double parentOtherCoordinate = (! vertical) ? parent.point.x() : parent.point.y();

        // Either Left or Down
        if (pointCoordinate < parentCoordinate) {
            double xmin = rectangle.xmin();
            double ymin = rectangle.ymin();
            double xmax = (vertical) ? parentCoordinate : rectangle.xmax();
            double ymax = (vertical) ? rectangle.ymax() : parentCoordinate;
            parent.left  = put(parent.left,  point, new RectHV(xmin, ymin, xmax, ymax), level+1);
        }
        else if (pointCoordinate > parentCoordinate) {
            double xmin = (vertical) ? parentCoordinate : rectangle.xmin();
            double ymin = (vertical) ? rectangle.ymin() : parentCoordinate;
            double xmax = rectangle.xmax();
            double ymax = rectangle.ymax();
            parent.right  = put(parent.right,  point, new RectHV(xmin, ymin, xmax, ymax), level+1);
        }
        else if (pointOtherCoordinate < parentOtherCoordinate) {
            double xmin = rectangle.xmin();
            double ymin = rectangle.ymin();
            double xmax = (vertical) ? parentCoordinate : rectangle.xmax();
            double ymax = (vertical) ? rectangle.ymax() : parentCoordinate;
            parent.left  = put(parent.left,  point, new RectHV(xmin, ymin, xmax, ymax), level+1);
        }
        else if (pointOtherCoordinate > parentOtherCoordinate) {
            double xmin = (vertical) ? parentCoordinate : rectangle.xmin();
            double ymin = (vertical) ? rectangle.ymin() : parentCoordinate;
            double xmax = rectangle.xmax();
            double ymax = rectangle.ymax();
            parent.right  = put(parent.right,  point, new RectHV(xmin, ymin, xmax, ymax), level+1);
        }
        else {
            parent.rectangle = rectangle;
        }

         parent.size = 1 + size(parent.left) + size(parent.right);
        return parent;
    }

    public Iterable<Node> levelOrder() {
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
        return nearestPoint(root, queryPoint, null, 0);
    }

    /**
     * Goal: Find query point q
     *
     * @param node
     * @param queryPoint
     * @param oldChampion
     * @param level
     * @return
     */
    public Point2D nearestPoint(Node node, Point2D queryPoint, Point2D oldChampion, int level) {
        if (node == null) {
            return null;
        }
        double deltaX = queryPoint.x() - node.point.x();
        double deltaY = queryPoint.y() - node.point.y();
        double distance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        deltaX = queryPoint.x() - oldChampion.x();
        deltaY = queryPoint.y() - oldChampion.y();
        double championDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));

        Point2D newChampion;
        // closest point found
        if (distance == 0) {
            return node.point;
        }
        // first observation, or closest so far observed
        else if (oldChampion == null || distance < championDistance) {
            newChampion = node.point;
        }
        // current observation point is not the closest
        else {
            newChampion = oldChampion;
        }

        // Left or Right?
        boolean vertical = level%2 == 0;
        double splitCoordinate = (vertical) ? node.point.x() : node.point.y();
        double queryCoordinate = (vertical) ? queryPoint.x() : queryPoint.y();

        if (queryCoordinate < splitCoordinate) {
            nearestPoint(node.left,  queryPoint, newChampion, level+1);
            nearestPoint(node.right,  queryPoint, newChampion, level+1);
        }
        else {
            return newChampion;
        }

    }


    public Point2D[] range(RectHV rect) {
        if (rect == null) return null;

        ArrayList<Point2D> intersection = new ArrayList<>();
        range(root, rect, 0, intersection);
        return intersection.toArray(new Point2D[intersection.size()]);
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
