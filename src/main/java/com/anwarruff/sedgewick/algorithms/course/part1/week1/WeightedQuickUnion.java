package com.anwarruff.sedgewick.algorithms.course.part1.week1;

import edu.princeton.cs.algs4.StdOut;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class WeightedQuickUnion implements UnionFind
{
    public enum OPTIMIZATION {NONE, PATH_COMPRESSION}

    private int numNodes;
    private int[] parent;
    private int[] size;
    private IntUnaryOperator getRoot;

    public WeightedQuickUnion(int numNodes)
    {
        this(numNodes, OPTIMIZATION.PATH_COMPRESSION);
    }

    public WeightedQuickUnion(int[] parent, OPTIMIZATION optimization)
    {
        this.numNodes = parent.length;
        this.parent = parent;
        this.size = new int[numNodes];
        setOptimization(optimization);
        for (int i = 0; i < numNodes; ++i) {
            size[i] = 1;
        }
    }

    public WeightedQuickUnion(int numNodes, OPTIMIZATION optimization)
    {
        this.numNodes = numNodes;
        this.parent = new int[numNodes];
        this.size = new int[numNodes];
        setOptimization(optimization);
        init();
    }

    private void setOptimization(OPTIMIZATION optimization)
    {
        switch(optimization) {
            case NONE:
                getRoot = this::getRootPlain;
                break;
            case PATH_COMPRESSION:
                getRoot = this::getRootWeighted;
                break;
        }
    }

    /**
     * 1. Constructs an array of N items.
     * 2. Each item is initially in its own group.
     * 3. The index of the array is the item ID, and the value the group ID.
     */
    private void init()
    {
        for (int i = 0; i < numNodes; ++i) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q)
    {
        if (connected(p, q))
            return;

        int pRoot = getRoot.applyAsInt(p);
        int qRoot = getRoot.applyAsInt(q);
        if (size[pRoot] >= size[qRoot]) {
            // set qRoot to point to pRoot
            parent[qRoot] = parent[pRoot];
            // increase pRoot's size
            size[pRoot] += size[qRoot];
        }
        else {
            // set pRoot to point to qRoot
            parent[pRoot] = parent[qRoot];
            // increase the size of qRoot
            size[qRoot] += size[pRoot];
        }
    }

    @Override
    public boolean connected(int p, int q)
    {
        return getRoot.applyAsInt(p) == getRoot.applyAsInt(q);
    }

    private int getRootWeighted(int nodeId)
    {
        int nodeParentId = parent[nodeId];
        if (nodeParentId == nodeId) {
            return nodeId;
        }
        while(nodeParentId != parent[nodeParentId]) {
            parent[nodeId] = parent[nodeParentId];
            nodeParentId = parent[nodeParentId];
        }

        return nodeParentId;
    }

    private int getRootPlain(int nodeId)
    {
        while(nodeId != parent[nodeId]) {
            nodeId = parent[nodeId];
        }

        return nodeId;
    }

    @Override
    public String toString()
    {
        return Arrays.toString(parent);
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        //UnionFindRunner.run("mediumUF.txt", WeightedQuickUnion::new);
        WeightedQuickUnion weightedQuickUnion = new WeightedQuickUnion(10, OPTIMIZATION.NONE);

        weightedQuickUnion.union(2, 6);
        weightedQuickUnion.union(5, 7);
        weightedQuickUnion.union(4, 0);
        weightedQuickUnion.union(1, 2);
        weightedQuickUnion.union(4, 7);
        weightedQuickUnion.union(9, 2);
        weightedQuickUnion.union(2, 3);
        weightedQuickUnion.union(5, 6);
        weightedQuickUnion.union(1, 8);
        StdOut.print(weightedQuickUnion);
    }
}
