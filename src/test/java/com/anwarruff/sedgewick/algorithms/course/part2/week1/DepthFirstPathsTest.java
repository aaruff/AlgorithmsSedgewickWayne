package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by aruff on 1/8/17.
 */
public class DepthFirstPathsTest {
    @Test
    public void testPathFromSourceToVertex() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");

        DepthFirstPaths depthFirstSearch = new DepthFirstPaths(graphFactory.getGraph(), 0);

        Integer[][] pathsToOne = {{0, 1}};
        assertTrue(PathTester.isPathToVertex(depthFirstSearch.pathTo(1), pathsToOne));

        Integer[][] pathsToTwo = {{0, 2}};
        assertTrue(PathTester.isPathToVertex(depthFirstSearch.pathTo(2), pathsToTwo));

        Integer[][] pathsToThree = {{0, 5, 3}, {0, 5, 4, 3}, {0, 6, 4, 3}, {0, 6, 4, 5, 3}};
        assertTrue(PathTester.isPathToVertex(depthFirstSearch.pathTo(3), pathsToThree));

        Integer[][] pathsToFour = {{0, 6, 4}, {0, 5, 4}, {0, 5, 3, 4}};
        assertTrue(PathTester.isPathToVertex(depthFirstSearch.pathTo(4), pathsToFour));

        Integer[][] pathsToFive = {{0, 5}, {0, 6, 4, 5}, {0, 6, 4, 3, 5}};
        assertTrue(PathTester.isPathToVertex(depthFirstSearch.pathTo(5), pathsToFive));

        Integer[][] pathsToSix = {{0, 6}, {0, 5, 3, 4, 6}, {0, 5, 4, 6}};
        assertTrue(PathTester.isPathToVertex(depthFirstSearch.pathTo(6), pathsToSix));

        Integer[][] pathsToRest = {{}};
        for (int i = 7; i <= 12; ++i) {
            assertFalse(PathTester.isPathToVertex(depthFirstSearch.pathTo(i), pathsToRest));
        }
    }
}