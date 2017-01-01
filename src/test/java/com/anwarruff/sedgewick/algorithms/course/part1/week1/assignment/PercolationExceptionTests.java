package com.anwarruff.sedgewick.algorithms.course.part1.week1.assignment;

import org.junit.Test;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PercolationExceptionTests
{
    @Test
    public void instantiation_ThrowsIllegalArgumentException_OnOutOfBoundValues() throws Exception
    {
        int zeroOrLess = (new Random()).nextInt(Integer.MAX_VALUE) * -1;
        int cases[] = {0, zeroOrLess};
        for (int c : cases) {
            assertThatThrownBy(() -> new Percolation(c))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    public void isOpenAndIsFull_ThrowsIllegalArgumentException_OnOutOfBoundValues() {
        int n = 1;
        Percolation percolation = new Percolation(n);
        int zeroOrLess = (new Random()).nextInt(Integer.MAX_VALUE) * -1;
        int nOrMore = ((int) (Math.random() * (Integer.MAX_VALUE-n))) + n;

        int[][] cases = {
                // Zero case
                {n,0}, {0,n}, {0,0},
                // Random Zero Or Less Site IDs
                {n, zeroOrLess}, {zeroOrLess,n}, {zeroOrLess, zeroOrLess},
                // N + 1 case
                {n, n+1}, {n+1, n}, {n+1, n+1},
                // N Or More Site IDs
                {n, nOrMore}, {nOrMore, n}, {nOrMore, nOrMore}
        };

        // Test outside bounds on isOpen, and isFull
        BinaryIntegerPredicate[] tests = {percolation::isOpen, percolation::isFull};
        for (BinaryIntegerPredicate p : tests) {
            for (int[] c : cases) {
                assertThatThrownBy(() -> p.test(c[0], c[1]))
                        .isInstanceOf(IndexOutOfBoundsException.class);
            }
        }
    }

    @Test
    public void open_ThrowsIllegalArgumentException_OnOutOfBoundValues() {
        int n = 1;
        Percolation percolation = new Percolation(n);
        int zeroOrLess = (new Random()).nextInt(Integer.MAX_VALUE) * -1;
        int nOrMore = ((int) (Math.random() * (Integer.MAX_VALUE-n))) + n;

        int[][] cases = {
                {n,0}, {0,n}, {0,0},
                // Zero Or Less Site IDs
                {n, zeroOrLess}, {zeroOrLess,n}, {zeroOrLess, zeroOrLess},
                // N + 1 case
                {n, n+1}, {n+1, n}, {n+1, n+1},
                // N Or More Site IDs
                {n, nOrMore}, {nOrMore, n}, {nOrMore, nOrMore}
        };

        for (int[] c : cases) {
            assertThatThrownBy(() -> percolation.open(c[0], c[1]))
                    .isInstanceOf(IndexOutOfBoundsException.class);
        }
    }

    @FunctionalInterface
    private interface BinaryIntegerPredicate {
        boolean test(int x, int y);
    }
}