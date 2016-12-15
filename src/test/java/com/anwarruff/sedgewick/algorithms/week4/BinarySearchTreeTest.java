package com.anwarruff.sedgewick.algorithms.week4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 12/13/16.
 */
public class BinarySearchTreeTest {
    BinarySearchTree<String, Integer> strIntBST;
    Integer[] expectedValues = {0, 12, 8, 3, 4, 5, 12, 7, 8, 9, 10, 11, 12};
    String[] str = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};

    @Before
    public void setup() {
        strIntBST = new BinarySearchTree<>();
        for (Integer i = 0; i < str.length; i++) {
            strIntBST.put(str[i], i);
        }
    }

    @Test
    public void testBSTPut() {
        for (Integer i = 0; i < expectedValues.length; i++) {
            assertEquals(expectedValues[i], strIntBST.get(str[i]));
        }
    }

    @Test
    public void testMin() {
        String minKey = "A";
        assertEquals(minKey, strIntBST.min());
    }

    @Test
    public void testMax() {
        String maxKey = "X";
        assertEquals(maxKey, strIntBST.max());
    }

}