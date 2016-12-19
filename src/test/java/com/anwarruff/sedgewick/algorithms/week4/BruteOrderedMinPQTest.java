package com.anwarruff.sedgewick.algorithms.week4;

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
        BruteOrderedMinPQ<String> bruteOrderedMinPQ = new BruteOrderedMinPQ<>(max, lettersInAlphabet);
        assertEquals(bruteOrderedMinPQ.size(), max, bruteOrderedMinPQ.size());
    }

    @Test
    public void min() throws Exception {
        String[] firstEntries = "ghijklmnopqrstuvwxyz".split("");
        BruteOrderedMinPQ<String> bruteOrderedMinPQ = new BruteOrderedMinPQ<>(5, firstEntries);
        assertEquals("g", bruteOrderedMinPQ.min());

        String[] secondEntries = "abc".split("");
        for (String s : secondEntries) {
            bruteOrderedMinPQ.insert(s);
        }

        assertEquals("a", bruteOrderedMinPQ.min());
    }

    @Test
    public void delMin() throws Exception {
        String[] lettersInAlphabet = "abcdefghijklmnopqrstuvwxyz".split("");
        BruteOrderedMinPQ<String> bruteOrderedMinPQ = new BruteOrderedMinPQ<>(5, lettersInAlphabet);
        assertEquals("a", bruteOrderedMinPQ.delMin());
        assertEquals("b", bruteOrderedMinPQ.delMin());
        assertEquals("c", bruteOrderedMinPQ.delMin());
        assertEquals("d", bruteOrderedMinPQ.delMin());
        assertEquals("e", bruteOrderedMinPQ.delMin());
        assertTrue(bruteOrderedMinPQ.isEmpty());
    }

    @Test
    public void isEmpty() throws Exception {

    }

    @Test
    public void size() throws Exception {

    }

}