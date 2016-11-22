package com.anwarruff.sedgewick.algorithms.week1;

public interface UnionFind
{
    /**
     * Connects two components
     * @param p
     * @param q
     */
    void union(int p, int q);

    /**
     * Returns true if two components are connected
     * @param p
     * @param q
     * @return
     */
    boolean connected(int p, int q);

}
