package com.anwarruff.sedgewick.algorithms.course.part2.week1;

import java.util.Iterator;

/**
 * Created by aruff on 1/9/17.
 */
public class PathTester {
    public static boolean isPathToVertex(Iterable<Integer> pathIterable, Integer[][] paths) {
        if (pathIterable == null) {
            return false;
        }

        for (int i = 0; i < paths.length; ++i) {
            int matchingVertices = 0;
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
