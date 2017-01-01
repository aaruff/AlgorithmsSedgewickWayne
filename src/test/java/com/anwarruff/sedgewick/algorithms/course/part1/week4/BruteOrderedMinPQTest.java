package com.anwarruff.sedgewick.algorithms.course.part1.week4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 12/18/16.
 */
public class BruteOrderedMinPQTest {
    @Test
    public void insert() throws Exception {
        String[] lettersInAlphabet = "abcdefghijklmnopqrstuvwxyz".split("");
        int max = 5;
        FixedOrderedMinPQ<String> fixedOrderedMinPQ = new FixedOrderedMinPQ<>(max, lettersInAlphabet);
        assertEquals(fixedOrderedMinPQ.size(), max, fixedOrderedMinPQ.size());
    }

    @Test
    public void min() throws Exception {
        String[] firstEntries = "ghijklmnopqrstuvwxyz".split("");
        FixedOrderedMinPQ<String> fixedOrderedMinPQ = new FixedOrderedMinPQ<>(5, firstEntries);
        assertEquals("g", fixedOrderedMinPQ.min());

        String[] secondEntries = "abc".split("");
        for (String s : secondEntries) {
            fixedOrderedMinPQ.insert(s);
        }

        assertEquals("a", fixedOrderedMinPQ.min());
    }

    @Test
    public void delMin() throws Exception {
        String[] lettersInAlphabet = "abcdefghijklmnopqrstuvwxyz".split("");
        FixedOrderedMinPQ<String> fixedOrderedMinPQ = new FixedOrderedMinPQ<>(5, lettersInAlphabet);
        assertEquals("a", fixedOrderedMinPQ.delMin());
        assertEquals("b", fixedOrderedMinPQ.delMin());
        assertEquals("c", fixedOrderedMinPQ.delMin());
        assertEquals("d", fixedOrderedMinPQ.delMin());
        assertEquals("e", fixedOrderedMinPQ.delMin());
        assertTrue(fixedOrderedMinPQ.isEmpty());
    }

    @Test
    public void isEmpty() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

}