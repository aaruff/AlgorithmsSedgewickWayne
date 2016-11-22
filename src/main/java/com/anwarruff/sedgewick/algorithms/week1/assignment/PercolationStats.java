package com.anwarruff.sedgewick.algorithms.week1.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
    private Double mean;
    private Double stdDev;
    private Double confLow;
    private Double confHigh;

    public PercolationStats(int gridSize, int trials)
    {
        if (gridSize <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        double results[] = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation percolation = new Percolation(gridSize);
            int numOpened = 0;
            int[] sites = shuffle(getListNumbers(gridSize*gridSize));
            while ( ! percolation.percolates()) {
                int row = (sites[numOpened]-1)/gridSize + 1;
                int col = (sites[numOpened]-1)%gridSize + 1;
                percolation.open(row, col);
                ++numOpened;
            }

            results[i] = numOpened/(double)(gridSize*gridSize);
        }

        this.mean = StdStats.mean(results);
        this.stdDev = StdStats.stddev(results);
        this.confLow = mean - (1.96*stdDev)/Math.sqrt(trials);
        this.confHigh =  mean + (1.96*stdDev)/Math.sqrt(trials);
    }

    private int[] getListNumbers(int N)
    {
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = i+1;
        }
        return nums;
    }

    private int[] shuffle(int[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            // choose index uniformly in [i, N-1]
            int r = i + (int) (StdRandom.uniform() * (N - i));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }

        return a;
    }

    public double mean()
    {
        return mean;
    }

    public double stddev()
    {
        return stdDev;
    }

    public double confidenceLo()
    {
        return confLow;
    }

    public double confidenceHi()
    {
        return confHigh;
    }

    public static void main(String[] args)
    {
        int gridWidth = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);

        PercolationStats percolationStats = new PercolationStats(gridWidth, trials);
        StdOut.println("mean\t = "+percolationStats.mean());
        StdOut.println("stddev\t = "+percolationStats.stddev());
        StdOut.println("95% confidence interval\t = "
                + percolationStats.confidenceLo() + ", "
                + percolationStats.confidenceHi());
    }
}
