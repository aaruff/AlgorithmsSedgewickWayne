package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

import com.anwarruff.sedgewick.algorithms.course.part2.week1.GraphFactory;
import org.junit.Test;

public class DFSStackImpTest {
    @Test
    public void testMarkedSubGraphZero() throws Exception {
        DFSStackImp dfs = new DFSStackImp(GraphFactory.getGraph("tinyG.txt"), 0);
    }

}