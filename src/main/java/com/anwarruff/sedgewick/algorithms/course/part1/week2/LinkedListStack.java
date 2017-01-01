package com.anwarruff.sedgewick.algorithms.course.part1.week2;

public class LinkedListStack
{
    private Node first;


    private class Node<T>
    {
        private T value;
        private Node next;

        public Node(T value) {
           this.value = value;
        }
    }
}
