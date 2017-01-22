package com.anwarruff.sedgewick.algorithms.course.part2.week1.assignment;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aruff on 1/22/17.
 */
public class OutcastTest {

     @Test
     public void testOutcast5() throws Exception {
         OutcastFileReader outcastFileReader = new OutcastFileReader("outcast5.txt");
         WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
         Outcast outcast = new Outcast(wordNet);
         assertEquals("table", outcast.outcast(outcastFileReader.getNouns()));
     }

    @Test
    public void testOutcast8() throws Exception {
        OutcastFileReader outcastFileReader = new OutcastFileReader("outcast8.txt");
        WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordNet);
        assertEquals("bed", outcast.outcast(outcastFileReader.getNouns()));
    }

    @Test
    public void testOutcast11() throws Exception {
        OutcastFileReader outcastFileReader = new OutcastFileReader("outcast11.txt");
        WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordNet);
        assertEquals("potato", outcast.outcast(outcastFileReader.getNouns()));
    }
}