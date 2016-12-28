package com.anwarruff.sedgewick.algorithms.chapter1;

import java.util.Iterator;

/**
 * Created by aruff on 12/27/16.
 */
public class BagArrayImp<Item extends Comparable<Item>> implements Iterable<Item>{
    private Item[] items;
    private int size;

    public BagArrayImp() {
        size = 0;
        items = (Item[]) new Comparable[1];
    }

    public void add(Item item) {
        if (size == items.length) {
            resize(size*2);
        }

        items[size++] = item;
    }

    private void resize(int newSize) {
        Item[] newBag = (Item[]) new Comparable[newSize];
        System.arraycopy(items, 0, newBag, 0, items.length);
        items = newBag;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Item> iterator() {
        return new BagIterator(items, size);
    }

    private class BagIterator implements Iterator<Item> {
        private Item[] items;
        private int next;
        private int size;

        /**
         * Running time is: O(n)
         * @param items
         */
        public BagIterator(Item[] items, int size) {
            next = 0;
            this.size = size;
            this.items = (Item[]) new Comparable[items.length];
            System.arraycopy(items, 0, this.items, 0, items.length);
        }

        @Override
        public boolean hasNext() {
            return next < size;
        }

        @Override
        public Item next() {
            if (! hasNext()) throw new IndexOutOfBoundsException("No items remaining to iterate over.");
            Item item = items[next];
            items[next] = null;
            ++next;
            return item;
        }
    }




}
