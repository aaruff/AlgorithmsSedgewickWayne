package com.anwarruff.sedgewick.algorithms.textbook.chapter1.section3;

import java.util.Iterator;

/**
 * Created by aruff on 12/29/16.
 */
public class QueueArrayImp<Item extends Comparable<Item>> implements Iterable<Item>{
    private Item[] items;
    private int head;
    private int tail;

    public QueueArrayImp() {
        head = tail = 0;
        items = (Item[]) new Comparable[1];
    }

    public void enqueue(Item item) {
        if (tail == items.length) {
            if ((tail - head) > (3 * items.length) / 4) {
                resize(items.length * 2);
            }
            else {
                shiftLeft();
            }
        }

        items[tail++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new IndexOutOfBoundsException("No items on queue to remove");
        Item item = items[head];
        items[head++] = null;
        return item;
    }

    private void resize(int length) {
        Item[] items = (Item[]) new Comparable[length];
        System.arraycopy(this.items, head, items, 0, tail - head);

        this.items = items;
        tail = tail - head;
        head = 0;
    }

    private void shiftLeft() {
        System.arraycopy(items, head, items, 0, tail - head);
        tail = tail - head;
        head = 0;
    }

    public int size() {
        return tail - head;
    }

    public boolean isEmpty() {
        return (tail - head) == 0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator(items, head, tail);
    }

    private class QueueIterator implements Iterator<Item> {
        private final Item[] items;
        private int head;
        private int tail;

        public QueueIterator(Item[] items, int head, int tail) {
            this.items = (Item[]) new Comparable[tail-head];
            System.arraycopy(items, head, this.items, 0, tail - head);
            this.head = 0;
            this.tail = tail - head - 1;
        }

        @Override
        public boolean hasNext() {
            return head <= tail;
        }

        @Override
        public Item next() {
            Item item = items[head];
            items[head++] = null;
            return item;
        }
    }
}
