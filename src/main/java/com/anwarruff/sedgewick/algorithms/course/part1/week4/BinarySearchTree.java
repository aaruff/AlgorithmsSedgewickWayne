package com.anwarruff.sedgewick.algorithms.course.part1.week4;

import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by aruff on 12/12/16.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    public void put(Key key, Value value) {
        root = insertNode(root, key, value);
    }

    private Node insertNode(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, 1);
        }

        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = insertNode(node.left, key, value);
            node.count = size(node.left) + size(node.right) + 1;
        }
        else if (compareResult > 0) {
            node.right = insertNode(node.right, key, value);
            node.count = size(node.left) + size(node.right) + 1;
        }
        else {
            node.value = value;
        }

        return node;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return (node != null) ? node.count : 0;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) {
            return 0;
        }

        int keyCompare = key.compareTo(node.key);
        if (keyCompare < 0) {
            return rank(node.left, key);
        }
        else if (keyCompare > 0) {
            return size(node.left) + rank(node.right, key) + 1;
        }
        else {
           return size(node.left);
        }
    }

    public Value get(Key key) {
        Node node = getNode(root, key);
        if (node == null) {
            return null;
        }

        return node.value;
    }

    private Node getNode(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return getNode(node.left, key);
        }
        else if (compareResult > 1) {
            return getNode(node.right, key);
        }
        else {
            return node;
        }
    }

    public Key min() {
        Predicate<Node> leftTest = (n) -> n.left != null;
        Function<Node, Node> leftChild = (n) -> n.left;

        Node n = findBoundary(root, leftTest, leftChild);
        if (n == null) return null;

        return n.key;
    }

    public Key max() {
        Predicate<Node> maxTest = (n) -> n.right != null;
        Function<Node, Node> rightChild = (n) -> n.right;
        Node n = findBoundary(root, maxTest, rightChild);

        if (n == null) return null;

        return n.key;
    }

    private Node findBoundary(Node node, Predicate<Node> next, Function<Node, Node> child) {
        if (node == null) return null;

        if (next.test(node)) {
            return findBoundary(child.apply(node), next, child);
        }
        else {
            return node;
        }
    }

    public Iterable<Key> iterator() {
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return null;
            }
        };
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int count;

        public Node(Key key, Value value, int count) {
            this.key = key;
            this.value = value;
            this.count = count;
        }
    }
}
