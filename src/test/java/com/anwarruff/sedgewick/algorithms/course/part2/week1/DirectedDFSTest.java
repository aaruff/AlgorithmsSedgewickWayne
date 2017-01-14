package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by aruff on 1/13/17.
 */
public class DirectedDFSTest {
    @Test
    public void testMarkedPathsFromZero() throws Exception {
        DigraphFactory digraphFactory = new DigraphFactory("tinyDG.txt");
        DirectedDFS directedDFS = new DirectedDFS(digraphFactory.getGraph(), 0);
        ArrayList<Integer> zeroConnected = new ArrayList<>(Arrays.asList(0, 1, 5, 4, 3, 2));
        ArrayList<Integer> zeroNotConnected = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10, 11, 12));

        for (Integer connectedVertex : zeroConnected) {
            assertTrue(directedDFS.marked(connectedVertex));
        }
        for (Integer notConnectedVertex : zeroNotConnected) {
            assertFalse(directedDFS.marked(notConnectedVertex));
        }

    }

}