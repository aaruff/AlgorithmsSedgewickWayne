package com.anwarruff.sedgewick.algorithms.chapter1;

import java.util.Iterator;

/**
 * Created by aruff on 12/23/16.
 */
public class Queue<T> implements Iterable<T> {
    private Node front;
    private int size;

    public Queue() {
        size = 0;
    }

    public void enqueue(T item) {
        if (size == 0) {
            front = new Node(item);
            ++size;
        }
        else {
            Node node = front;
            while (node.next != null) {
                node = node.next;
            }

            node.next = new Node(item);
            ++size;
        }
    }

    public T deque() {
        if (front == null) {
            return null;
        }

        Node old = front;
        front = old.next;

        --size;
        return old.item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new QueueIterator(front);
    }

    private class QueueIterator implements Iterator<T> {
        private Node front;

        public QueueIterator(Node front) {
            this.front = front;
        }

        @Override
        public boolean hasNext() {
            return front != null;
        }

        @Override
        public T next() {
            Node old = front;
            front = old.next;

            return old.item;
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
