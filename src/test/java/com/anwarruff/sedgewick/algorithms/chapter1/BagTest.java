package com.anwarruff.sedgewick.algorithms.chapter1;

import com.anwarruff.sedgewick.algorithms.chapter1.section3.Bag;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by aruff on 12/22/16.
 */
public class BagTest {
    @Test
    public void testBagIterator() throws Exception {
        ArrayList<String> entries = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
        Bag<String> b = new Bag<>();
        for (String s : entries) {
            b.add(s);
        }

        assertEquals(entries.size(), b.size());

        for (String s : b) {
            assertTrue(entries.contains(s));
            entries.remove(s);
        }

        assertEquals(0, entries.size());
    }

}