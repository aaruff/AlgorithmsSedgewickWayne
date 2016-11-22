package com.anwarruff.sedgewick.algorithms.week2.assignment;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RandomizedQueueTest {
    // 1. The order of two or more iterators to the same randomized queue must be mutually independent;
    // each iterator must maintain its own random order.
    @Test
    public void testIndependentIterators() {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        for (int i = 0; i < 10; ++i) {
            r.enqueue(i);
        }

        ArrayList firstResults = new ArrayList();
        for (Iterator<Integer> iterator = r.iterator(); iterator.hasNext();) {
            firstResults.add(iterator.next());
        }

        ArrayList secondResults = new ArrayList();
        for (Iterator<Integer> iterator = r.iterator(); iterator.hasNext();) {
            secondResults.add(iterator.next());
        }

        boolean same = true;
        for(int i = 0; i < 10; ++i) {
            same = same && firstResults.get(i) == secondResults.get(i);
        }

        assertFalse(same);
    }

    @Test
    public void testAddRemoveRandomlyManyItems() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        int rAdd = StdRandom.uniform(50, 100);
        for (int i = 0; i < rAdd; ++i) {
            randomizedQueue.enqueue(i);
        }

        int rRemove = StdRandom.uniform(50, rAdd);
        for (int i = 0; i < rRemove; ++i) {
            randomizedQueue.dequeue();
        }

        int diff = rAdd - rRemove;
        assertTrue(randomizedQueue.size() == diff);
    }

    @Test
    public void afterArraySizeIncreases_TestSizeOfQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);

        assertTrue(randomizedQueue.size() == 2);
    }

    @Test
    public void afterArraySizeDecreases_TestSizeOfQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.dequeue();

        assertTrue(randomizedQueue.size() == 1);
    }

    @Test
    public void testItemsEnteredAreAlwaysReturned() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        Integer[] items = new Integer[50];
        for (int i = 0; i < 50; ++i) {
            items[i] = i+1;
        }

        for (int i = 0; i < items.length; ++i) {
            randomizedQueue.enqueue(items[i]);
        }

        while(randomizedQueue.size() > 0) {
            boolean inArray = false;
            Integer item = randomizedQueue.dequeue();
            for (int i = 0; i < items.length; ++i) {
                if (item == items[i]) {
                    inArray = true;
                    break;
                }
            }
            assertTrue(inArray);
        }
    }

    @Test
    public void testIteratorIteratesOverAllItems() {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        int ITEMS = 10;
        for (int i = 0; i < ITEMS; ++i) {
            r.enqueue(i);
        }

        int counter = 0;
        Iterator<Integer> iterator = r.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            ++counter;
        }

        assertTrue(counter == ITEMS);
    }

    @Test
    public void testNoDuplicatesDequed() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        Integer[] items = new Integer[50];
        Integer[] matches = new Integer[50];
        for (int i = 0; i < 50; ++i) {
            items[i] = i+1;
            matches[i] = 0;
        }

        for (int i = 0; i < items.length; ++i) {
            randomizedQueue.enqueue(items[i]);
        }

        while(randomizedQueue.size() > 0) {
            Integer item = randomizedQueue.dequeue();
            for (int i = 0; i < items.length; ++i) {
                if (item == items[i]) {
                    matches[i] = matches[i] + 1;
                    break;
                }
            }
        }

        int sumMatches = 0;
        for (int i = 0; i < 50; ++i) {
            sumMatches += matches[i];
        }

        assertTrue(sumMatches == 50);
   }

   @Test
    public void testDequeueUniform() {
       /*
        * Successfull Outcomes: a, ba, ca, bca, cba
        * Total: 3! = 6
        * p("a") = 1/3
        * p("xa") = 2/P(3,2) = 2/3*2 = 1/3
        * p("xxa") = 2/P(3,3) = 2/3*2*1 = 1/3
        *
        * E("a") = 1/3 * 3000 = 1000
        * E("xa") = 1/3 * 3000 = 1000
        * E("xxa") = 1/3 * 3000 = 1000
        *           ------------------------
        *           |  "a"   | "xa" | "xxa" |
        *           -------------------------
        * Expected: |  1000  | 1000 | 1000  |
        *           -------------------------
        */
       ArrayList<String> items = new ArrayList<>(Arrays.asList("a", "b", "c"));
       RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
       int[] observed = new int[3];
       for (int i = 0; i < 3000; ++i) {
           int count = 0;
           items.forEach(randomizedQueue::enqueue);
           boolean found = false;
           while ( ! found && ! randomizedQueue.isEmpty()) {
               found = randomizedQueue.dequeue().equals("a");
               ++count;
           }

           while ( ! randomizedQueue.isEmpty()) {
               randomizedQueue.dequeue();
           }

           observed[count-1] += 1;
       }

       double expected = 1000.0;
       double chiSquare = 0.0;
       for (int i = 0; i < observed.length; ++i) {
           chiSquare += ((observed[i] - expected) * (observed[i] - expected)) / expected;
       }

       for (int i = 0; i < observed.length; ++i) {
           StdOut.println(observed[i]);
       }
       StdOut.println(chiSquare);
   }

}

