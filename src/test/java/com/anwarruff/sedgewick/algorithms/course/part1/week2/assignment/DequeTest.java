package com.anwarruff.sedgewick.algorithms.course.part1.week2.assignment;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DequeTest {
    @Test
    public void dequeIsEmptyOnInitialization()
    {
        Deque<Integer> d = new Deque<>();
        assertTrue(d.size() == 0);
        assertTrue(d.isEmpty());
    }

    @Test
    public void size_ReturnsOne_WhenOneItemAddedFirst()
    {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        assertTrue(d.size() == 1);
    }

    @Test
    public void size_ReturnsOne_WhenOneItemAddedLast()
    {
        Deque<Integer> d = new Deque<>();
        d.addLast(1);
        assertTrue(d.size() == 1);
    }

    @Test
    public void removeFirst_ReturnsItem_WhenOneItemAddedFirst()
    {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        assertTrue(d.removeFirst() == 1);
    }

    @Test
    public void addFirstRemoveLast_ReturnsItem()
    {
        Deque<Integer> d = new Deque<>();
        Integer i = 1;
        d.addFirst(i);
        assertTrue(d.removeLast() == i);
    }

    @Test
    public void itemsAdded_WithFirst_And_Removed_WithLast_ShouldBe_FIFO()
    {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        assertTrue(d.removeLast() == 1);
        assertTrue(d.removeLast() == 2);
        assertTrue(d.removeLast() == 3);
    }

    @Test
    public void itemsAdded_WithFirst_And_Removed_WithFirst_ShouldBe_LIFO()
    {
        Deque<Integer> d = new Deque<>();
        assertTrue(d.size() == 0);
        d.addFirst(1);
        assertTrue(d.size() == 1);
        d.addFirst(2);
        assertTrue(d.size() == 2);
        d.addFirst(3);

        assertTrue(d.size() == 3);

        assertTrue(d.removeFirst() == 3);
        assertTrue(d.size() == 2);
        assertTrue(d.removeFirst() == 2);
        assertTrue(d.size() == 1);
        assertTrue(d.removeFirst() == 1);
        assertTrue(d.size() == 0);
    }

    @Test
    public void iterator_Should_IterateOverItems_FromFront_ToRear(){
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addFirst(2);
        d.addFirst(3);
        Iterator<Integer> i = d.iterator();
        assertTrue(i.hasNext());
        assertTrue(i.next() == 3);
        assertTrue(i.hasNext());
        assertTrue(i.next() == 2);
        // should be last node
        assertTrue(i.hasNext());
        assertTrue(i.next() == 1);
        assertFalse(i.hasNext());
    }

    @Test
    public void testAddFirstRemoveRear() {
        Deque<Integer> deque = new Deque<>();
        for (int i = 0; i < 100; ++i) {
            deque.addFirst(i);
        }
        for (int i = 0; i < 100; ++i) {
            assertTrue(deque.removeLast() == i);
        }
    }

    @Test
    public void testAddRearRemoveFirst() {
        Deque<Integer> deque = new Deque<>();

        int rAdd = StdRandom.uniform(50, 100);
        for (int i = 0; i < rAdd; ++i) {
            deque.addLast(i);
        }

        int rRemove = StdRandom.uniform(50, rAdd);
        for (int i = 0; i < rRemove; ++i) {
            assertTrue(deque.removeFirst() == i);
        }

        int diff = rAdd - rRemove;
        assertTrue(diff == deque.size());

    }
}
