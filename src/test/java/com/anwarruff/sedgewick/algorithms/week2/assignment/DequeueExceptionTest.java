package com.anwarruff.sedgewick.algorithms.week2.assignment;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DequeueExceptionTest {

    @Test
    public void adding_ThrowsNullPointerException_When_NullItemAdded(){
        Deque<Integer> d = new Deque<>();
        assertThatThrownBy(() -> d.addFirst(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> d.addLast(null)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void removing_ThrowsNoSuchElementException_When_DequeIsEmpty(){
        Deque<Integer> d = new Deque<>();
        assertThatThrownBy(d::removeFirst).isInstanceOf(NoSuchElementException.class);
        assertThatThrownBy(d::removeLast).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void removingFromIterator_ThrowsUnsupportedOperationException(){
        Deque<Integer> d = new Deque<>();
        Iterator<Integer> i = d.iterator();
        assertThatThrownBy(i::remove).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void nextOnEndOfIterator_ThrowsNoSuchElementException(){
        Deque<Integer> d = new Deque<>();
        Iterator<Integer> i = d.iterator();
        assertThatThrownBy(i::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void addFirstRemoveLast_getFirst_ThrowsNoSuchElementException()
    {
        Deque<Integer> d = new Deque<>();
        Integer i = 1;
        d.addFirst(i);
        d.removeLast();
        assertThatThrownBy(d::removeFirst).isInstanceOf(NoSuchElementException.class);
    }

}
