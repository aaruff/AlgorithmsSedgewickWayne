package com.anwarruff.sedgewick.algorithms.course.part1.week1;

import org.junit.Test;

public class QuickFindTest
{
    @Test
    public void union() throws Exception
    {
        UnionFindTest.testUnionFind(QuickFind::new);
    }
}