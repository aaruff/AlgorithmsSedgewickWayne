package com.anwarruff.sedgewick.algorithms.course.part1.week1;

public class ThreeSumNaive implements ThreeSum
{
    public int count(int[] a)
    {
        int triples = 0;
        for (int i = 0; i < a.length; ++i) {
            for (int j = i + 1; j < a.length; ++j) {
                for (int k = j + 1; k < a.length; ++k) {
                    if (a[i]+a[j]+a[k] == 0) {
                        ++triples;
                    }
                }
            }
        }

        return triples;
    }

    public static void main(String[] args)
    {
        ThreeSumRunner.run(ThreeSumNaive::new);
    }
}
