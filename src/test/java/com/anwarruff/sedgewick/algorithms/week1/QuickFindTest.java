package com.anwarruff.sedgewick.algorithms.week1;

import org.junit.Test;

public class QuickFindTest
{
    @Test
    public void union() throws Exception
    {
        UnionFindTest.testUnionFind(QuickFind::new);
    }
}