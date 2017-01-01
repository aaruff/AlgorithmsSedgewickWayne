package com.anwarruff.sedgewick.algorithms.course.part1.week2.assignment;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by aruff on 11/5/16.
 */
public class Deque<Item> implements Iterable<Item>{
    private int n;
    private Node<Item> front;
    private Node<Item> rear;

    public Deque() {
        n = 0;
    }

    public int size()
    {
        return n;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    /**
     * Constant time
     * @param item
     */
    public void addFirst(Item item)
    {
        if (item == null) {
            throw new NullPointerException("Item argument passed is null");
        }

        addToFront(item);
        ++n;
    }

    /**
     * Constant time
     * @param item
     */
    public void addLast(Item item)
    {
        if (item == null) {
            throw new NullPointerException("Item argument passed is null");
        }

        addToRear(item);
        ++n;
    }

    /**
     * Constant time
     * @return
     */
    public Item removeFirst()
    {
        if (n <= 0) {
            throw new NoSuchElementException("No items to remove");
        }

        --n;
        return removeFromFront();
    }

    /**
     * Constant time
     * @return
     */
    public Item removeLast()
    {
        if (n <= 0) {
            throw new NoSuchElementException("No items to remove");
        }

        --n;
        return removeFromRear();
    }

    /**
     * Constant time
     * @return
     */
    @Override
    public Iterator<Item> iterator() {
        return new FrontToBackIterator();
    }

    //-------------------------------------
    //   Private
    //-------------------------------------
    /**
     * works for cases where the list is not empty
     */
    private void addToFront(Item item)
    {
        Node<Item> newFront = new Node<>(item);

        if (front == null) {
            front = newFront;
            rear = newFront;
        }
        else {
            newFront.setNext(front);
            front.setPrior(newFront);
            front = newFront;
        }
    }

    /**
     * works for cases where the list is not empty
     */
    private void addToRear(Item item)
    {
        Node<Item> newRear = new Node<>(item);

        if (rear == null) {
            rear = newRear;
            front = newRear;
        }
        else {
            newRear.setPrior(rear);
            rear.setNext(newRear);
            rear = newRear;
        }
    }

    /**
     * works for cases where the list is not empty
     */
    private Item removeFromRear()
    {
        Node<Item> oldRear = rear;
        Node<Item> newRear = oldRear.getPriorNode();

        if (newRear == null) {
            front = null;
            rear = null;
        }
        else {
            newRear.setNext(null);
            rear = newRear;
        }

        return oldRear.getItem();
    }

    /**
     * works for cases where the list is not empty
     */
    private Item removeFromFront()
    {
        Node<Item> oldFront = front;
        Node<Item> newFront = front.getNextNode();

        if (newFront == null) {
            front = null;
            rear = null;
        }
        else {
            newFront.setPrior(null);
            front = newFront;
        }

        return oldFront.getItem();
    }


    private class FrontToBackIterator implements Iterator<Item>
    {
        private Node<Item> current = front;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (! hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.getItem();
            current = current.getNextNode();
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("The remove function is unsupported on this iterator.");
        }
    }

    /*
     * Roughly 48 bytes
     */
    private class Node<Item>
    {
        private Node<Item> prior;
        private Node<Item> next;
        private Item item;

        public Node(Item item) {
            this.item = item;
        }

        public Node<Item> getPriorNode() {
            return prior;
        }

        public void setPrior(Node<Item> prior) {
            this.prior = prior;
        }

        public Node<Item> getNextNode() {
            return next;
        }

        public void setNext(Node<Item> next) {
            this.next = next;
        }

        public Item getItem() {
            return item;
        }
    }
}
