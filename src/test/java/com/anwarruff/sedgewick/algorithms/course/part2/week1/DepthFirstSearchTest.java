package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by aruff on 1/8/17.
 */
public class DepthFirstSearchTest {
    @Test
    public void testMarkedSubGraphZero() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graphFactory.getGraph(), 0);
        ArrayList<Integer> zeroConnected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        ArrayList<Integer> zeroNotConnected = new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12));

        for (Integer connectedVertex : zeroConnected) {
            assertTrue(depthFirstSearch.marked(connectedVertex));
        }
        for (Integer notConnectedVertex : zeroNotConnected) {
            assertFalse(depthFirstSearch.marked(notConnectedVertex));
        }
    }

    @Test
    public void testMarkedSubGraphSeven() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graphFactory.getGraph(), 7);
        ArrayList<Integer> sevenConnected = new ArrayList<>(Arrays.asList(7, 8));
        ArrayList<Integer> sevenNotConnected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 9, 10, 11, 12));

        for (Integer connectedVertex : sevenConnected) {
            assertTrue(depthFirstSearch.marked(connectedVertex));
        }
        for (Integer notConnectedVertex : sevenNotConnected) {
            assertFalse(depthFirstSearch.marked(notConnectedVertex));
        }
    }

    @Test
    public void testMarkedSubGraphNine() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graphFactory.getGraph(), 9);
        ArrayList<Integer> nineConnected = new ArrayList<>(Arrays.asList(9, 10, 11, 12));
        ArrayList<Integer> nineNotConnected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));

        for (Integer connectedVertex : nineConnected) {
            assertTrue(depthFirstSearch.marked(connectedVertex));
        }
        for (Integer notConnectedVertex : nineNotConnected) {
            assertFalse(depthFirstSearch.marked(notConnectedVertex));
        }
    }

    @Test
    public void testSubGraphZeroCount() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graphFactory.getGraph(), 0);
        assertEquals(7, depthFirstSearch.count());
    }

    @Test
    public void testSubGraphSevenCount() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graphFactory.getGraph(), 7);
        assertEquals(2, depthFirstSearch.count());
    }

    @Test
    public void testSubGraphNineCount() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graphFactory.getGraph(), 9);
        assertEquals(4, depthFirstSearch.count());
    }


}