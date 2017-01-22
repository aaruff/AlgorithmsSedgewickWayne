package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 1/16/17.
 */
public class WordNetTest {
    @Test
    public void testConstructor() throws Exception {
        WordNet wordNet = new WordNet("synsets15.txt", "hypernyms15Path.txt");
        assertTrue(wordNet.isNoun("l"));
    }

}