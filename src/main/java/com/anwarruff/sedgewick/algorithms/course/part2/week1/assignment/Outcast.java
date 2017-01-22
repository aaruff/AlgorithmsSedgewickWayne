package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

/**
 * Created by aruff on 1/22/17.
 */
public class Outcast {
    private WordNet wordNet;
    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }

    public String outcast(String[] nouns) {
        String outcast = "";
        int maximumDistance = 0;
        for (int i = 0; i < nouns.length; i++) {
            int ithDistance = 0;
            for (int j = 0; j < nouns.length; j++) {
                ithDistance += wordNet.distance(nouns[i], nouns[j]);
            }

            if (ithDistance > maximumDistance) {
                outcast = nouns[i];
                maximumDistance = ithDistance;
            }
        }

        return outcast;
    }
}
