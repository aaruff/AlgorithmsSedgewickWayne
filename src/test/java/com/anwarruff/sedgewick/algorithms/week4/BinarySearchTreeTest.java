package com.anwarruff.sedgewick.algorithms.week4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 12/13/16.
 */
public class BinarySearchTreeTest {
    @Test
    public void testBSTPut() {
        String[] str = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        Integer[] exp = {0, 12, 8, 3, 4, 5, 12, 7, 8, 9, 10, 11, 12};
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        for (Integer i = 0; i < str.length; i++) {
            bst.put(str[i], i);
        }

        for (Integer i = 0; i < str.length; i++) {
            assertEquals(exp[i], bst.get(str[i]));
        }
    }

    @Test
    public void testMin() {
        String[] str = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        for (Integer i = 0; i < str.length; i++) {
            bst.put(str[i], i);
        }

        Integer val = 8;
        assertEquals(val, bst.min());
    }
}