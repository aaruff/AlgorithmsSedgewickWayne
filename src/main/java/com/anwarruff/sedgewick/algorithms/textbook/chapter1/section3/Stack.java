package com.anwarruff.sedgewick.algorithms.textbook.chapter1.section3;

import java.util.Iterator;

/**
 * Created by aruff on 12/23/16.
 */
public class Stack<T> implements Iterable<T> {
    private Node top;
    private int size;

    public Stack() {
        size = 0;
    }

    /**
     * Pushes an item on the stack. Running time is O(1)
     * @param item
     */
    public void push(T item) {
        if (top == null) {
            top = new Node(item);
            ++size;
            return;
        }

        Node node = new Node(item);
        node.next = top;
        top = node;
        ++size;
    }

    public T pop() {
        if (top == null) {
            return null;
        }

        T item = top.item;
        top = top.next;
        --size;
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new StackIterator(top);
    }

    private class StackIterator implements Iterator {
        private Node top;

        public StackIterator(Node top) {
            this.top = top;
        }

        @Override
        public boolean hasNext() {
            return top != null;
        }

        @Override
        public T next() {
            T item = top.item;
            top = top.next;
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
