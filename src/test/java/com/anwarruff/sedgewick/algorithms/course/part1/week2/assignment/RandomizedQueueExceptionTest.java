package com.anwarruff.sedgewick.algorithms.course.part1.week2.assignment;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RandomizedQueueExceptionTest {
    // NullPointerException if null is added
    @Test
    public void addingNullItem_Throws_NullPointerException(){
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        assertThatThrownBy(() -> r.enqueue(null)).isInstanceOf(NullPointerException.class);
    }

    // NoSuchElementException of the client attempts to *sample* or *dequeue* an item from an empty queue
    @Test
    public void sample_OnEmptyQueue_Throws_NoSuchElementException(){
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        assertThatThrownBy(r::sample).isInstanceOf(NoSuchElementException.class);
    }
    // NoSuchElementException of the client attempts to *sample* or *dequeue* an item from an empty queue
    @Test
    public void dequeue_OnEmptyQueue_Throws_NoSuchElementException(){
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        assertThatThrownBy(r::dequeue).isInstanceOf(NoSuchElementException.class);
    }

    // UnsupportedOperationException of the client calls *remove* on the iterator
    @Test
    public void removingFromIterator_ThrowsUnsupportedOperationException(){
        RandomizedQueue<Integer> d = new RandomizedQueue<>();
        Iterator<Integer> i = d.iterator();
        assertThatThrownBy(i::remove).isInstanceOf(UnsupportedOperationException.class);
    }

    // NoSuchElementException if *next* is called and there are no more items to return
    @Test
    public void nextOnEndOfIterator_ThrowsNoSuchElementException(){
        RandomizedQueue<Integer> d = new RandomizedQueue<>();
        Iterator<Integer> i = d.iterator();
        assertThatThrownBy(i::next).isInstanceOf(NoSuchElementException.class);
    }
}
