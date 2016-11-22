package com.anwarruff.sedgewick.algorithms.week1;

import org.junit.Test;

public class WeightedQuickUnionTest
{
    @Test
    public void testUnionFind() throws Exception
    {
        UnionFindTest.testUnionFind(WeightedQuickUnion::new);
    }
}