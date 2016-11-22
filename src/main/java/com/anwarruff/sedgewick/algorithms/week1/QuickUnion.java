package com.anwarruff.sedgewick.algorithms.week1;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class QuickUnion implements UnionFind
{
    private int numNodes;
    private int[] graph;

    public QuickUnion(int numNodes)
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
        for (int i = 0; i < numNodes; ++i)
            graph[i] = i;
    }

    @Override
    public void union(int p, int q)
    {
        graph[getRoot(p)] = getRoot(q);
    }

    public int getRoot(int i)
    {
        return (graph[i] == i) ? i : getRoot(graph[i]);
    }

    @Override
    public boolean connected(int p, int q)
    {
        return getRoot(p) == getRoot(q);
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        UnionFindRunner.run("tinyUF.txt", QuickUnion::new);
    }

}
