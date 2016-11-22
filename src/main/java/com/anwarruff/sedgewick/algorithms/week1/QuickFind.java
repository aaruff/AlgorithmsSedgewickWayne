package com.anwarruff.sedgewick.algorithms.week1;

import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class QuickFind implements UnionFind
{
    private int numNodes;
    private int[] graph;

    public QuickFind(int numNodes)
    {
        this.numNodes = numNodes;
        this.graph = new int[numNodes];
        init();
    }

    /**
     * 1. Constructs an array of N items.
     * 2. Each item is initially in its own group.
     * 3. The index of the array is the item ID, and the value the group ID.
     */
    private void init()
    {
        for (int i = 0; i < numNodes; ++i) {
            graph[i] = i;
        }
    }

    @Override
    public void union(int p, int q)
    {
        if (connected(p, q))
            return;

        int pGroup = graph[p];
        int qGroup = graph[q];

        // N running time
        for (int i = 0; i < numNodes; ++i) {
            if (graph[i] == pGroup)
                graph[i] = qGroup;
        }
    }

    @Override
    public boolean connected(int p, int q)
    {
        return graph[p] == graph[q];
    }

    @Override
    public String toString()
    {
        return Arrays.toString(graph);
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        //UnionFindRunner.run("largeUF.txt", QuickFind::new);
        int[] pvalues = new int[] {4,0,6,8,9,2};
        int[] qvalues = new int[] {0,3,6,8,9,2};
        QuickFind quickFind = new QuickFind(10);
        quickFind.union(4,0);
        quickFind.union(0,3);
        quickFind.union(6,0);
        quickFind.union(8,2);
        quickFind.union(9,1);
        quickFind.union(2,9);
        StdOut.print(quickFind);
    }

}
