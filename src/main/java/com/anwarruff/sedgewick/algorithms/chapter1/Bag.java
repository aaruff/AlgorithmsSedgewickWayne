package com.anwarruff.sedgewick.algorithms.chapter1;

import java.util.Iterator;

/**
 * Created by aruff on 12/22/16.
 */
public class Bag<T> implements Iterable<T>{
    private Node head;
    private int size = 0;

    public void add(T item) {
        Node current = head;
        while (current != null && current.next != null) {
           current = current.next;
        }

        if (current == null) {
           head = new Node(item);
        }
        else {
            current.next = new Node(item);
        }

        ++size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new BagIterator(head);
    }

    private class BagIterator implements Iterator<T> {
        private Node node;

        public BagIterator(Node node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public T next() {
            T item = node.item;
            node = node.next;
            return item;
        }
    }

    private class Node {
        private T item;
        private Node next;
        public Node(T item) {
            this.item = item;
        }
    }
}
