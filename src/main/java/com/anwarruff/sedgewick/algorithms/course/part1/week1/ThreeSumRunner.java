package com.anwarruff.sedgewick.algorithms.course.part1.week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.function.Supplier;


public class ThreeSumRunner
{
    public static int[] randomInts(int N)
    {
        int MAX = 1000000;
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(-MAX, MAX);
        }
        return a;
    }

    public static void run(Supplier<ThreeSum> threeSumSupplier)
    {
        //int[] a = StdInWrapper.readAllInts(fileName);
        int times = 6;
        double oldTime = 0.0, newTime = 0.0, ratio = 0.0;
        for (int i = 1, N = 250; i <= times; ++i, N*=2) {
            double tTime = 0.0;
            for (int j = 1; j <= 4; ++j) {
                tTime += ThreeSumRunner.runThreeSum(ThreeSumRunner.randomInts(N), threeSumSupplier);
            }
            newTime = tTime/4;
            ratio = (i == 1) ? newTime : newTime/oldTime;

            StdOut.println("Ratio " + i + " = " + ratio + ", N = " + N + ", t(2N)/t(N) = " + newTime + "/" + oldTime);
            oldTime = newTime;
        }
    }

    private static double runThreeSum(int[] a, Supplier<ThreeSum> threeSumSupplier)
    {
        ThreeSum threeSum = threeSumSupplier.get();
        Stopwatch stopwatch = new Stopwatch();
        int sums = threeSum.count(a);
        //StdOut.println("zeros = " + sums);
        return stopwatch.elapsedTime();
    }

}
