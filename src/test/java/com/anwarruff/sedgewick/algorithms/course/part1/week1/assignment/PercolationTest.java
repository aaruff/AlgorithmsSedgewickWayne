package com.anwarruff.sedgewick.algorithms.course.part1.week1.assignment;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class PercolationTest {
    @Test
    public void test1x1GridClosedOnCreation() {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.isOpen(1,1));
    }

//    @Test
//    public void test1x1GridDoesNotPercolateOnCreation() {
//        Percolation percolation = new Percolation(1);
//        assertFalse(percolation.percolates());
//    }

    @Test
    public void test1x1GridOpen() {
        Percolation percolation = new Percolation(1);
        percolation.open(1,1);
        assertTrue("1x1 Grid: Site (1,1) opened, but isOpen() returning false", percolation.isOpen(1,1));
    }

    @Test
    public void test1x1SiteClosedNotFull() {
        Percolation percolation = new Percolation(1);
        assertFalse("1x1 Grid: Site (1,1) not opened, but full", percolation.isFull(1,1));
    }

    @Test
    public void test1x1SiteFullOnOpen() {
        Percolation percolation = new Percolation(1);
        percolation.open(1,1);
        assertTrue("1x1 Grid: Site (1,1) opened, but not full", percolation.isFull(1,1));
    }

    @Test
    public void test1x1GridIsNotFullOnCreation() {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.isFull(1,1));
    }

    @Test
    public void test1x1PercolatesWhenPathFromTopToBottom() {
        Percolation percolation = new Percolation(1);
        percolation.open(1,1);
        assertTrue("1x1 Grid: Site (1,1) opened, but not percolate", percolation.percolates());
    }

    @Test
    public void test1x1DoesNotPercolatesWhenNoPathOpenFromTopToBottom() {
        Percolation percolation = new Percolation(1);
        assertFalse("1x1 Grid: Site (1,1) not opened, but not percolate", percolation.percolates());
    }

    @Test
    public void testSixBySixSnakeCase() {
        Percolation percolation = new Percolation(6);
        int[][] cases = {{1,6},{2,6}, {3,6}, {4,6}, {5,6}, {5,5}, {4,4}, {3,4}, {2,4},
                {2,3}, {2,2}, {2,1}, {3,1}, {4,1}, {5,1}, {5,2}, {6,2}, {5,4}};
        boolean[] results = {true, true, true, true, true, true, false, false, false, false,
                false, false, false, false, false, false, false, true};

        for (int i = 0; i < cases.length; ++i) {
            int row = cases[i][0];
            int col = cases[i][1];
            percolation.open(row, col);
            assertThat(percolation.isFull(row, col))
                    .as(String.format("Site (%d,%d) should be full", row, col))
                    .isEqualTo(results[i]);
        }

        int[][] path = {{1,6},{2,6}, {3,6}, {4,6}, {5,6}, {5,5}, {5,4}, {4,4}, {3,4},
                {2,4}, {2,3}, {2,2}, {2,1}, {3,1}, {4,1}, {5,1}, {5,2}, {6,2}};
        for (int i = 0; i < path.length; ++i) {
            int row = path[i][0];
            int col = path[i][1];
            assertThat(percolation.isFull(row,col))
                    .as(String.format("Site (%d,%d) should be full", row, col))
                    .isEqualTo(true);
        }

        assertTrue(percolation.percolates());
    }

}
