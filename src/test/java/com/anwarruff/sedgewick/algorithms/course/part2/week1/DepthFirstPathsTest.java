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
    public void testMarkedSubGraphZero() throws Exception {
        GraphFactory graphFactory = new GraphFactory("tinyG.txt");

        DepthFirstPaths depthFirstSearch = new DepthFirstPaths(graphFactory.getGraph(), 0);

        Integer[][] pathsToOne = {{0, 1}};
        assertTrue(isPathToVertex(depthFirstSearch, pathsToOne, 1));

        Integer[][] pathsToTwo = {{0, 2}};
        assertTrue(isPathToVertex(depthFirstSearch, pathsToTwo, 2));

        Integer[][] pathsToThree = {{0, 5, 3}, {0, 5, 4, 3}, {0, 6, 4, 3}, {0, 6, 4, 5, 3}};
        assertTrue(isPathToVertex(depthFirstSearch, pathsToThree, 3));

        Integer[][] pathsToFour = {{0, 6, 4}, {0, 5, 4}, {0, 5, 3, 4}};
        assertTrue(isPathToVertex(depthFirstSearch, pathsToFour, 4));

        Integer[][] pathsToFive = {{0, 5}, {0, 6, 4, 5}, {0, 6, 4, 3, 5}};
        assertTrue(isPathToVertex(depthFirstSearch, pathsToFive, 5));

        Integer[][] pathsToSix = {{0, 6}, {0, 5, 3, 4, 6}, {0, 5, 4, 6}};
        assertTrue(isPathToVertex(depthFirstSearch, pathsToSix, 6));

        Integer[][] pathsToRest = {{}};
        for (int i = 7; i <= 12; ++i) {
            assertFalse(isPathToVertex(depthFirstSearch, pathsToRest, 8));
        }
    }


    private boolean isPathToVertex(DepthFirstPaths depthFirstPaths, Integer[][] paths, int v) {
        for (int i = 0; i < paths.length; ++i) {
            int matchingVertices = 0;
            Iterable<Integer> pathIterable = depthFirstPaths.pathTo(v);
            if (pathIterable == null) {
                return false;
            }

            Iterator<Integer> pathIterator = pathIterable.iterator();
            for (int j = 0, w; j < paths[i].length && pathIterator.hasNext(); ++j) {
                w = pathIterator.next();
                if (paths[i][j] == w) {
                    ++matchingVertices;
                }
            }
            if (matchingVertices == paths[i].length) {
                return true;
            }
        }
        return false;
    }

}