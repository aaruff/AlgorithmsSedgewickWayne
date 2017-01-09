package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 1/9/17.
 */
public class BreadthFirstPathsTest {
    @Test
    public void testShortestPathFromSourceToVertex() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");
        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(graphFactory.getGraph(), 0);
        Integer[][] pathsToOne = {{0, 1}};
        assertTrue(PathTester.isPathToVertex(breadthFirstPaths.pathTo(1), pathsToOne));

        Integer[][] pathsToTwo = {{0, 2}};
        assertTrue(PathTester.isPathToVertex(breadthFirstPaths.pathTo(2), pathsToTwo));

        Integer[][] pathsToThree = {{0, 5, 3}};
        assertTrue(PathTester.isPathToVertex(breadthFirstPaths.pathTo(3), pathsToThree));

        Integer[][] pathsToFour = {{0, 6, 4}, {0, 5, 4}};
        assertTrue(PathTester.isPathToVertex(breadthFirstPaths.pathTo(4), pathsToFour));

        Integer[][] pathsToFive = {{0, 5}};
        assertTrue(PathTester.isPathToVertex(breadthFirstPaths.pathTo(5), pathsToFive));

        Integer[][] pathsToSix = {{0, 6}};
        assertTrue(PathTester.isPathToVertex(breadthFirstPaths.pathTo(6), pathsToSix));

        Integer[][] pathsToRest = {{}};
        for (int i = 7; i <= 12; ++i) {
            assertFalse(PathTester.isPathToVertex(breadthFirstPaths.pathTo(i), pathsToRest));
        }
    }
}