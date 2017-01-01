package com.anwarruff.sedgewick.algorithms.course.part1.week4;

public class FixedOrderedMinPQ<T extends Comparable<T>> {
    T[] values;
    int head;

    /**
     * Bounded PQ
     * @param size
     */
    public FixedOrderedMinPQ(int size) {
        head = 0;
        values = (T[]) new Comparable[size];
    }

    public FixedOrderedMinPQ(int size, T[] values) {
        head = 0;
        this.values = (T[]) new Comparable[size];
        for (int i = 0; i < values.length; i++) {
            insert(values[i]);
        }
    }

    /**
     * Insert is O(M)
     * @param t
     */
    public void insert(T t) {
        if (full() && t.compareTo(values[0]) > 0) {
            return;
        }

        int insertPoint = head;
        for (int i = 0; i < head; ++i) {
            if (t.compareTo(values[i]) >  0) {
                insertPoint = i;
                break;
            }
        }

        if (full()) {
            for (int shiftIndex = 1; shiftIndex < insertPoint; ++shiftIndex) {
                values[shiftIndex - 1] = values[shiftIndex];
            }
            insertPoint = (insertPoint == 0) ? 0 : insertPoint - 1;
            values[insertPoint] = t;
        }
        else {
            for (int shiftIndex = head-1; insertPoint <= shiftIndex; --shiftIndex) {
                values[shiftIndex + 1] = values[shiftIndex];
            }

            values[insertPoint] = t;
            head = head + 1;
        }
    }

    private boolean full() {
        return head == values.length;
    }

    public T min() {
        return values[head - 1];
    }

    /**
     * O(M)
     * @return
     */
    public T delMin() {
        if (head == 0) {
            throw new UnsupportedOperationException("The queue is empty.");
        }
        T val = values[--head];
        values[head] = null;
        return val;
    }

    public boolean isEmpty() {
        return head == 0;
    }

    public int size() {
        return head;
    }

}
