package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by aruff on 1/16/17.
 */
public class WordNet {
    private HashMap<String, Set<Integer>> nounToSynsetIds;
    private HashMap<Integer, String> synsetIdsToNouns;
    private HashMap<Integer, Bag<Integer>> synsetToHypernyms;
    private SAP shortestAncestralPath;

    public WordNet(String synsetFile, String hypernymsFile) {
        if (synsetFile == null || hypernymsFile == null) {
            throw new NullPointerException("null parameter passed to constructor");
        }

        readSynsetsFile(synsetFile);
        readHyperymsFile(hypernymsFile);

        Digraph digraph = new Digraph(synsetIdsToNouns.size());
        int size = synsetIdsToNouns.size();
        for (int v = 0; v < size; v++) {
            if (synsetToHypernyms.containsKey(v)) {
                for (Integer w : synsetToHypernyms.get(v)) {
                    digraph.addEdge(v, w);
                }
            }
        }

        DirectedCycle directedCycle = new DirectedCycle(digraph);
        if (directedCycle.hasCycle()) {
            throw new IllegalArgumentException();
        }


        shortestAncestralPath = new SAP(digraph);
    }

    public Iterable<String> nouns() {
        return nounToSynsetIds.keySet();
    }

    public boolean isNoun(String word) {
        if (word == null) {
            throw new NullPointerException();
        }

        return nounToSynsetIds.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return shortestAncestralPath.length(nounToSynsetIds.get(nounA), nounToSynsetIds.get(nounB));
    }

    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        int synsetId = shortestAncestralPath.ancestor(nounToSynsetIds.get(nounA), nounToSynsetIds.get(nounB));
        return synsetIdsToNouns.get(synsetId);
    }

    private void readSynsetsFile(String synsetFile) {
        //File file = new File(getClass().getClassLoader().getResource("part2/week1/assignment/" + synsetFile).getFile());
        File file = new File(synsetFile);
        try {
            Scanner fileScanner = new Scanner(file);
            nounToSynsetIds = new HashMap<>();
            synsetIdsToNouns = new HashMap<>();
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split(",");

                Integer synsetId = Integer.valueOf(parts[0]);
                String[] nouns = parts[1].split("\\s+");

                synsetIdsToNouns.put(synsetId, parts[1]);


                for (int i = 0; i < nouns.length; i++) {
                    if (nounToSynsetIds.containsKey(nouns[i])) {
                        nounToSynsetIds.get(nouns[i]).add(synsetId);
                    }
                    else {
                        HashSet<Integer> idSet = new HashSet<>();
                        idSet.add(synsetId);
                        nounToSynsetIds.put(nouns[i], idSet);
                    }
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    private void readHyperymsFile(String hypernymsFileName) {
        //File file = new File(getClass().getClassLoader().getResource("part2/week1/assignment/" + hypernymsFileName).getFile());
        File file = new File(hypernymsFileName);
        synsetToHypernyms = new HashMap<>();
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                String[] parts = line.split(",");

                Integer synsetId = Integer.valueOf(parts[0]);
                Bag<Integer> hypernyms = new Bag<>();
                for (int i = 1; i < parts.length; i++) {
                    hypernyms.add(Integer.valueOf(parts[i]));
                }
                synsetToHypernyms.put(synsetId, hypernyms);
            }
            fileScanner.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
