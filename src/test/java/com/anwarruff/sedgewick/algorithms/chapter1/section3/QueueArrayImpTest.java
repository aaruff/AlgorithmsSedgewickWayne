package com.anwarruff.sedgewick.algorithms.chapter1.section3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aruff on 12/29/16.
 */
public class QueueArrayImpTest {
    private QueueArrayImp<String> queue;
    List<String> inputList;

    @Before
    public void setUp() throws Exception {
        inputList = Arrays.asList("A", "B", "C", "D", "E");
        queue = new QueueArrayImp<>();
        for (String s : inputList) {
            queue.enqueue(s);
        }
    }

    @Test
    public void testIterator() throws Exception {
        assertEquals(inputList.size(), queue.size());

        int i = 0;
        for (String queueString : queue) {
            assertTrue(inputList.get(i++).equals(queueString));
        }
    }


    @Test
    public void testSize() throws Exception {
        QueueArrayImp<String> q = new QueueArrayImp<>();
        assertTrue(q.isEmpty());
        assertEquals(0, q.size());

        q.enqueue("A");

        assertFalse(q.isEmpty());
        assertEquals(1, q.size());

        q.dequeue();

        assertEquals(0, q.size());
        assertTrue(q.isEmpty());
    }

    @Test
    public void testDequeue() throws Exception {
        assertEquals(inputList.size(), queue.size());

        for (int i = inputList.size()-1, j = 0; i >= 0; --i, ++j) {
            String s = queue.dequeue();
            assertTrue(s.equals(inputList.get(j)));
            assertEquals(i, queue.size());

            Iterator<String> iterator = queue.iterator();
            for (int k = j + 1; k < inputList.size(); ++k) {
                assertTrue(iterator.hasNext());
                assertEquals(inputList.get(k), iterator.next());
            }
        }

        assertEquals(0, queue.size());
    }

}