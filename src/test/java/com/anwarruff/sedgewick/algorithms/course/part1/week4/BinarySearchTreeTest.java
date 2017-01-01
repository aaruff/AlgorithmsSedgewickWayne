package com.anwarruff.sedgewick.algorithms.course.part1.week4;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by aruff on 12/13/16.
 */
public class BinarySearchTreeTest {
    BinarySearchTree<String, Integer> strIntBST;
    String[] str = {"S", "E", "A", "R", "C", "H", "E", "X", "A", "M", "P", "L", "E"};
    HashMap<String, Integer> treeValueMap;
    HashMap<String, Integer> treeCount;

    @Before
    public void setup() {
        strIntBST = new BinarySearchTree<>();
        for (Integer i = 0; i < str.length; i++) {
            strIntBST.put(str[i], i);
        }

        treeValueMap = new HashMap<>();
        treeValueMap.put("S", 0);
        treeValueMap.put("E", 12);
        treeValueMap.put("A", 8);
        treeValueMap.put("R", 3);
        treeValueMap.put("C", 4);
        treeValueMap.put("H", 5);
        treeValueMap.put("X", 7);
        treeValueMap.put("M", 9);
        treeValueMap.put("P", 10);
        treeValueMap.put("L", 11);

        treeCount = new HashMap<>();
        treeCount.put("S", 9);
        treeCount.put("E", 8);
        treeCount.put("A", 2);
        treeCount.put("R", 5);
        treeCount.put("C", 1);
        treeCount.put("H", 4);
        treeCount.put("X", 1);
        treeCount.put("M", 3);
        treeCount.put("P", 1);
        treeCount.put("L", 1);
    }

    @Test
    public void testBSTPut() {

        for (Map.Entry<String, Integer> entry : treeValueMap.entrySet()) {
            assertEquals(entry.getKey(), entry.getValue(), strIntBST.get(entry.getKey()));
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

    @Test
    public void testSize() {
        assertEquals(10, strIntBST.size());
        BinarySearchTree<String, Integer> bst = new BinarySearchTree<>();
        bst.put("C", 1);
        bst.put("A", 1);
        bst.put("D", 1);
        assertEquals(3, bst.size());
    }
    @Test
    public void testRank() {
        String key = "E";
        assertEquals(2, strIntBST.rank(key));
    }

}