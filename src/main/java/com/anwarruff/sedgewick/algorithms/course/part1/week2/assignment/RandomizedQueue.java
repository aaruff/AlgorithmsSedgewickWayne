package com.anwarruff.sedgewick.algorithms.course.part1.week2.assignment;


import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Your randomized queue implementation must support each randomized queue operation
 * (besides creating an iter) in constant amortized time. That is, any sequence
 * of m randomized queue operations (starting from an empty queue) should take at most
 * cm steps in the worst case, for some constant c. A randomized queue containing n items
 * must use at most 48n + 192 bytes of memory.
 *
 * Additionally, your iter implementation must support operations n() and hasNext()
 * in constant worst-case time; and construction in linear time; you may (and will need to)
 * use a linear amount of extra memory per iter.
 */
public class RandomizedQueue<Item> implements Iterable<Item>{
    private int n;
    private Item[] items;

    public RandomizedQueue() {
        n = 0;
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    /**
     * Enqueue creates a randomly shuffled collection of items by adding the item to the list
     * using the Fisher-Yates method for adding randomly swapping the added element with another.
     * @param item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (isFull()) {
            resize(enlarge());
        }

        Random rand = new Random(Double.doubleToLongBits(Math.random()));
        int r = rand.nextInt(n + 1);
        if (r == n) {
            items[n] = item;
        }
        else {
            Item tempItem = items[r];
            items[r] = item;
            items[n] = tempItem;
        }

        ++n;
    }

    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        }

        if (isTooBig()) {
            resize(shrink());
        }

        --n;
        Item item = items[n];
        items[n] = null;
        return item;
    }

    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int sample = StdRandom.uniform(n);
        return items[sample];
    }

    public Iterator<Item> iterator() {
        return new IndependentIterator(items, n);
    }


    //--------------------------------------------
    // Private
    //--------------------------------------------

    private boolean isFull() {
        return items.length == n;
    }

    private int enlarge() {
        return items.length * 2;
    }

    private int shrink() {
        return items.length / 2;
    }

    private boolean isTooBig() {
        return n > 0 && n == items.length/4;
    }

    private void resize(int length) {
        Item[] newItems = (Item[]) new Object[length];
        for (int i = 0; i < n; ++i) {
            newItems[i] = items[i];
        }

        items = newItems;
    }


    private class IndependentIterator implements Iterator<Item> {
        private Item[] copiedItems;
        private int index;

        public IndependentIterator(Item[] sourceItems, int size) {
            if (size <= 0) {
                copiedItems = (Item[]) new Object[0];
                index = -1;
            }
            else {
                index = size - 1;
                copiedItems = (Item[]) new Object[size];
                copiedItems[0] = sourceItems[0];
                Random r = new Random(Double.doubleToLongBits(Math.random()));
                for (int i = 1; i < size; ++i) {
                    int rand = r.nextInt(i + 1);
                    if (rand == i) {
                        copiedItems[i] = sourceItems[i];
                    } else {
                        Item tempItem = copiedItems[rand];
                        copiedItems[rand] = sourceItems[i];
                        copiedItems[i] = tempItem;
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public Item next() {
            if (index < 0) {
                throw new NoSuchElementException();
            }

            return copiedItems[index--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
