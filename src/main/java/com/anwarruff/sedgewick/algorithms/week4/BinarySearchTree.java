package com.anwarruff.sedgewick.algorithms.week4;

import java.util.Iterator;
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
        // If the child is null, instantiate a new node with the key and value and pass it back to the parent
        if (node == null) {
            return new Node(key, value);
        }

        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = insertNode(node.left, key, value);
        }
        else if (compareResult > 0) {
            node.right = insertNode(node.right, key, value);
        }
        else {
            node.value = value;
        }

        return node;
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
        Predicate<Node> minTest = (n) -> n.left != null;
        Node n = findBoundary(root, minTest);
        if (n == null) return null;

        return n.key;
    }

    public Key max() {
        Predicate<Node> maxTest = (n) -> n.right != null;
        Node n = findBoundary(root, maxTest);
        if (n == null) return null;

        return n.key;
    }


    private Node findBoundary(Node node, Predicate<Node> boundary) {
        if (node == null) return null;

        if (boundary.test(node)) {
            return findBoundary(node.left, boundary);
        }
        else {
            return node;
        }
    }

    public void delete(Key key) {

    }

    public Iterable<Key> iterator() {
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return null;
            }
        };
    }

    public class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }
}
