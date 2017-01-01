package com.anwarruff.sedgewick.algorithms.course.part1.week1;

import org.junit.Test;

public class QuickUnionTest
{
    @Test
    public void union() throws Exception
    {
        UnionFindTest.testUnionFind(QuickUnion::new);
    }

}