package com.anwarruff.sedgewick.algorithms.course.part1.week2;

public class Stack<T>
{
    private final T[] stack;
    private int pointer;


    public Stack(int N)
    {
        pointer = 0;
        this.stack = (T[]) new Object[N];
    }

    /**
     * Push an item onto the stack.
     * @param t
     */
    public void push(T t)
    {
        if (pointer >= stack.length) {
            throw  new IndexOutOfBoundsException();
        }

        stack[pointer++] = t;
    }

    public T pop()
    {
        if (pointer < 0 || pointer >= stack.length) {
            throw  new IndexOutOfBoundsException();
        }

        return stack[--pointer];
    }

    public T peek()
    {
        if (pointer < 0) {
            throw  new IndexOutOfBoundsException();
        }

        return stack[pointer-1];
    }
}
