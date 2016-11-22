package com.anwarruff.sedgewick.algorithms.week1;


import edu.princeton.cs.algs4.StdOut;

import java.util.Scanner;

public class UnionFindRunner
{
    public static void run(String fileName, UnionFindSupplier unionFindSupplier)
    {
        Scanner scanner = StdInWrapper.getFileScanner(fileName);
        if (scanner == null) {
            StdOut.println("Failed to load file.");
        }

        int N = Integer.valueOf(scanner.nextLine());
        while(scanner.hasNext()) {
            int p = scanner.nextInt();
            int q = scanner.nextInt();
            UnionFind unionFind = unionFindSupplier.get(N);

            if ( ! unionFind.connected(p, q)) {
                unionFind.union(p, q);
            }
        }

        StdOut.println("Done!");
        scanner.close();
    }

}
