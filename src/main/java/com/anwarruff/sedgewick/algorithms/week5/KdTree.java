package com.anwarruff.sedgewick.algorithms.week5;

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
        return get(point) != null;
    }

    private RectHV get(Point2D point) {
        return get(root, point, 0);
    }

    private RectHV get(Node node, Point2D point, int level) {
        if (node == null) return null;

        double parentCoordinate = (level%2 == 0) ? node.point.x() : node.point.y();
        double pointCoordinate = (level%2 == 0) ? point.x() : point.y();

        if (pointCoordinate < parentCoordinate) {
            return get(node.left, point, level + 1);
        }
        else if (pointCoordinate > parentCoordinate) {
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
        if (point == null) throw new IllegalArgumentException("first argument to put() is null");
        root = put(root, point, new RectHV(0.0, 0.0, 1.0, 1.0), 0);
    }

    private Node put(Node current, Point2D insertPoint, RectHV currentRectangle, int level) {
        if (current == null) {
            int size = 1;
            Node leftNode = null;
            Node rightNode = null;
            return new Node(insertPoint, currentRectangle, leftNode, rightNode, level, size);
        }
        boolean vertical = level%2 == 0;
        double parentCoordinate = (vertical) ? current.point.x() : current.point.y();
        double pointCoordinate = (vertical) ? insertPoint.x() : insertPoint.y();
        double pointOtherCoordinate = (! vertical) ? insertPoint.x() : insertPoint.y();
        double parentOtherCoordinate = (! vertical) ? current.point.x() : current.point.y();

        // Either Left or Down
        if (pointCoordinate < parentCoordinate) {
            double xmin = currentRectangle.xmin();
            double ymin = currentRectangle.ymin();
            double xmax = (vertical) ? parentCoordinate : currentRectangle.xmax();
            double ymax = (vertical) ? currentRectangle.ymax() : parentCoordinate;
            current.left  = put(current.left,  insertPoint, new RectHV(xmin, ymin, xmax, ymax), level+1);
        }
        else if (pointCoordinate > parentCoordinate) {
            double xmin = (vertical) ? parentCoordinate : currentRectangle.xmin();
            double ymin = (vertical) ? currentRectangle.ymin() : parentCoordinate;
            double xmax = currentRectangle.xmax();
            double ymax = currentRectangle.ymax();
            current.right  = put(current.right,  insertPoint, new RectHV(xmin, ymin, xmax, ymax), level+1);
        }
        else if (pointOtherCoordinate < parentOtherCoordinate) {
            double xmin = currentRectangle.xmin();
            double ymin = currentRectangle.ymin();
            double xmax = (vertical) ? parentCoordinate : currentRectangle.xmax();
            double ymax = (vertical) ? currentRectangle.ymax() : parentCoordinate;
            current.left  = put(current.left,  insertPoint, new RectHV(xmin, ymin, xmax, ymax), level+1);
        }
        else if (pointOtherCoordinate > parentOtherCoordinate) {
            double xmin = (vertical) ? parentCoordinate : currentRectangle.xmin();
            double ymin = (vertical) ? currentRectangle.ymin() : parentCoordinate;
            double xmax = currentRectangle.xmax();
            double ymax = currentRectangle.ymax();
            current.right  = put(current.right,  insertPoint, new RectHV(xmin, ymin, xmax, ymax), level+1);
        }
        else {
            current.rectangle = currentRectangle;
        }

         current.size = 1 + size(current.left) + size(current.right);
        return current;
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
     * @param queryPoint
     * @param oldChampion
     * @param level
     * @return
     */
    private Node nearestPoint(Node node, Point2D queryPoint, Node oldChampion, int level) {
        if (node == null) {
            return oldChampion;
        }

        double pointDistance = node.point.distanceSquaredTo(queryPoint);
        double oldChampDistance = oldChampion.point.distanceSquaredTo(queryPoint);
        double rectDistance = node.rectangle.distanceSquaredTo(queryPoint);
        if (oldChampDistance < rectDistance) {
            return oldChampion;
        }

        Node champion = (pointDistance < oldChampDistance) ? node : oldChampion;

        boolean splitX = level%2 == 0;
        double queryX = queryPoint.x();
        double queryY = queryPoint.x();
        double nodeX = node.point.x();
        double nodeY = node.point.y();

        Node firstDirection, secondDirection;
        if (splitX) {
            if (queryX < nodeX) {
               firstDirection = node.left;
               secondDirection = node.right;
            }
            else if (queryX > nodeX) {
                firstDirection = node.right;
                secondDirection = node.left;
            }
            else if (queryY < nodeY) {
                firstDirection = node.left;
                secondDirection = node.right;
            }
            else if (queryY > nodeY) {
                firstDirection = node.right;
                secondDirection = node.left;
            }
            else {
                return champion;
            }
        }
        // split Y
        else {
            if (queryY < nodeY) {
                firstDirection = node.left;
                secondDirection = node.right;
            }
            else if (queryY > nodeY) {
                firstDirection = node.right;
                secondDirection = node.left;
            }
            else if (queryX < nodeX) {
                firstDirection = node.left;
                secondDirection = node.right;
            }
            else if (queryX > nodeX) {
                firstDirection = node.right;
                secondDirection = node.left;
            }
            else {
                return champion;
            }

        }

        champion = nearestPoint(secondDirection, queryPoint, champion, level + 1);


        // If the champ point is closer than the nodes
        // query point and the rectangle corresponding to the node,
        // there is no need to explore that node, or it's sub tree
        // pruning rule
        if (champion.point.distanceSquaredTo(queryPoint) < rectDistance) {
            return champion;
        }
        else {
            return nearestPoint(firstDirection, queryPoint, champion, level + 1);
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
