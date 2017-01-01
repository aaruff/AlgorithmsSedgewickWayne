package com.anwarruff.sedgewick.algorithms.textbook.chapter1.section3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aruff on 12/28/16.
 */
public class StackArrayImpTest {
    private StackArrayImp<String> stack;
    List<String> inputList;

    @Before
    public void setUp() throws Exception {
        inputList = Arrays.asList("A", "B", "C", "D", "E");
        stack = new StackArrayImp<>();
        for (String s : inputList) {
            stack.push(s);
        }
    }

    @Test
    public void testWhenArraySizeShrunkToZeroIsAbleToIncreaseSize() {
        // array size is 5, now we decrease the size to zero
        inputList.forEach(item -> stack.pop());
        assertTrue(stack.isEmpty());

        stack.push("Z");
        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());
    }

    @Test
    public void testIterator() throws Exception {
        assertEquals(inputList.size(), stack.size());
        List<String> popList = Arrays.asList("E", "D", "C", "B", "A");

        int i = 0;
        for (String queueString : stack) {
            assertTrue(popList.get(i++).equals(queueString));
        }
    }

    @Test
    public void testSize() throws Exception {
        StackArrayImp<String> stack = new StackArrayImp<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());

        stack.push("A");

        assertFalse(stack.isEmpty());
        assertEquals(1, stack.size());

        stack.pop();

        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPop() throws Exception {
        List<String> popList = Arrays.asList("E", "D", "C", "B", "A");
        assertEquals(popList.size(), stack.size());
        for (int i = popList.size()-1, j = 0; i >= 0; --i, ++j) {
            String s = stack.pop();
            assertTrue(s.equals(popList.get(j)));
            assertEquals(i, stack.size());

            Iterator<String> iterator = stack.iterator();
            for (int k = j + 1; k < popList.size(); ++k) {
                assertTrue(iterator.hasNext());
                assertEquals(popList.get(k), iterator.next());
            }
        }

        assertEquals(0, stack.size());
    }

}