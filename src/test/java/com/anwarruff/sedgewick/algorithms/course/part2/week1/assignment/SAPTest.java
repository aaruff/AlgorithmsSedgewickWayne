package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

import com.anwarruff.sedgewick.algorithms.course.part2.week1.DigraphFactory;
import edu.princeton.cs.algs4.Bag;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 1/20/17.
 */
public class SAPTest {
    @Test
    public void digraph1Test() throws Exception {
        DigraphFactory digraph1Factory = new DigraphFactory("assignment/digraph1.txt");
        SAP sap = new SAP(digraph1Factory.getGraph());
        assertEquals(4, sap.length(3, 11));
        assertEquals(1, sap.ancestor(3, 11));
        assertEquals(3, sap.length(9, 12));
        assertEquals(5, sap.ancestor(9, 12));
        assertEquals(4, sap.length(7, 2));
        assertEquals(0, sap.ancestor(7, 2));
        assertEquals(-1, sap.length(1, 6));
        assertEquals(-1, sap.ancestor(1, 6));
        Bag<Integer> groupA = new Bag<>();
        groupA.add(8);
        groupA.add(3);
        groupA.add(1);
        Bag<Integer> groupB = new Bag<>();
        groupB.add(11);
        groupB.add(12);
        groupB.add(10);
        groupB.add(9);
        groupB.add(5);

        assertEquals(1, sap.length(groupA, groupB));
        assertEquals(1, sap.ancestor(groupA, groupB));
    }

    @Test
    public void digraph2Test() throws Exception {
        DigraphFactory digraph1Factory = new DigraphFactory("assignment/digraph2.txt");
        SAP sap = new SAP(digraph1Factory.getGraph());
        assertEquals(1, sap.length(1, 2));
        assertEquals(2, sap.ancestor(1, 2));
    }

    @Test
    public void digraph3Test() throws Exception {
        DigraphFactory digraph1Factory = new DigraphFactory("assignment/digraph3.txt");
        SAP sap = new SAP(digraph1Factory.getGraph());
        assertEquals(2, sap.length(2, 6));
        assertEquals(2, sap.ancestor(2, 6));
    }
}