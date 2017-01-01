package com.anwarruff.sedgewick.algorithms.course.part1.week1;

import static junit.framework.Assert.assertTrue;

public class UnionFindTest
{
    public static void testUnionFind(UnionFindSupplier unionFindSupplier) throws Exception
    {
        UnionFind unionFind = unionFindSupplier.get(10);

        // 0 1 2 3 3 5 6 7 8 9
        unionFind.union(4, 3);
        assertTrue(unionFind.connected(4, 3));

        // 0 1 2 8 8 5 6 7 8 9
        unionFind.union(3, 8);
        assertTrue(unionFind.connected(3, 8));

        // 0 1 2 8 8 5 5 7 8 9
        unionFind.union(6, 5);
        assertTrue(unionFind.connected(6, 5));

        // 0 1 2 8 8 5 5 7 8 8
        unionFind.union(9, 4);
        assertTrue(unionFind.connected(9, 3));
        assertTrue(unionFind.connected(9, 4));
        assertTrue(unionFind.connected(9, 8));
        assertTrue(unionFind.connected(9, 9));

        // 0 1 1 8 8 5 5 7 8 8
        unionFind.union(2, 1);
        assertTrue(unionFind.connected(2, 1));

        // 0 1 1 8 8 5 5 7 8 8
        unionFind.union(8, 9);
        assertTrue(unionFind.connected(8, 9));

        // 0 1 1 8 8 0 0 7 8 8
        unionFind.union(5, 0);
        assertTrue(unionFind.connected(5, 0));

        // 0 1 1 8 8 0 0 1 8 8
        unionFind.union(7, 2);
        assertTrue(unionFind.connected(7, 2));

        // 1 1 1 8 8 1 1 1 8 8
        unionFind.union(6, 1);
        assertTrue(unionFind.connected(6, 0));
        assertTrue(unionFind.connected(6, 1));
        assertTrue(unionFind.connected(6, 2));
        assertTrue(unionFind.connected(6, 5));
        assertTrue(unionFind.connected(6, 6));
        assertTrue(unionFind.connected(6, 7));
    }
}
