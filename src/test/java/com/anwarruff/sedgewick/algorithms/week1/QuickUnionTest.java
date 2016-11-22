package com.anwarruff.sedgewick.algorithms.week1;

import org.junit.Test;

public class QuickUnionTest
{
    @Test
    public void union() throws Exception
    {
        UnionFindTest.testUnionFind(QuickUnion::new);
    }

}