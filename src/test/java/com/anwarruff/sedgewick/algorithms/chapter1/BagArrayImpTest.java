package com.anwarruff.sedgewick.algorithms.chapter1;

import com.anwarruff.sedgewick.algorithms.chapter1.section3.BagArrayImp;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by aruff on 12/28/16.
 */
public class BagArrayImpTest {
    @Test
    public void testBagIterator() throws Exception {
        ArrayList<String> entries = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
        BagArrayImp<String> b = new BagArrayImp<>();

        assertTrue(b.isEmpty());

        for (String s : entries) {
            b.add(s);
        }

        assertFalse(b.isEmpty());

        assertEquals(entries.size(), b.size());

        for (String s : b) {
            assertTrue(entries.contains(s));
            entries.remove(s);
        }

        assertEquals(0, entries.size());
    }
}