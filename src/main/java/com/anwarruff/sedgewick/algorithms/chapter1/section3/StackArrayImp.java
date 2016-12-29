package com.anwarruff.sedgewick.algorithms.chapter1.section3;

import java.util.Iterator;

/**
 * Created by aruff on 12/28/16.
 */
public class StackArrayImp<Item extends Comparable<Item>> implements Iterable<Item>{
    private Item[] items;
    private int size;

    public StackArrayImp() {
        size = 0;
        items = (Item[]) new Comparable[1];
    }

    public void push(Item item) {
        if (size == items.length) resize(2*items.length);
        items[size++] = item;
    }

    public Item pop() {
        if (size == items.length/4) resize(items.length/2);

        Item item = items[size - 1];
        items[size - 1] = null;
        --size;
        return item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int size) {
        Item[] items = (Item[]) new Comparable[size];
        System.arraycopy(this.items, 0, items, 0, this.size);
        this.items = items;
    }


    @Override
    public Iterator<Item> iterator() {
        return new StackIterator(items, size);
    }

    private class StackIterator implements Iterator<Item> {
        private Item[] items;
        private int size;
        public StackIterator(Item[] items, int size) {
            this.size = size;
            this.items = (Item[]) new Comparable[size];
            System.arraycopy(items, 0, this.items, 0, size);
        }

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Item next() {
            if ( ! hasNext()) throw new IndexOutOfBoundsException("No items remaining to iterate over.");
            Item item = items[--size];
            items[size] = null;
            return item;
        }
    }
}
