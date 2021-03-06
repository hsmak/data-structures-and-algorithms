package org.hsmak.datastructures.queue;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;

/**
 * ToDo
 * Using an Array Circularly; via Modulo operation %
 * The modulo operator is ideal for treating an array circularly
 * <p>
 * Assuming that the array has fixed length N, new elements are enqueued toward the “end” of the current queue,
 * progressing from the front to index N − 1 and continuing at index 0, then 1
 * <p>
 * When we dequeue an element and want to “advance” the front index, we use the arithmetic f = (f + 1) % N
 *
 * @param <E>
 */
public class ArrayQueue<E> implements IQueue<E> {

    private final static int CAPACITY = 1000;

    private E[] data;
    private int f;
    private int sz;


    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public static void main(String[] args) {
        ArrayQueue<String> aq = new ArrayQueue<>(10);
        aq.enqueue("A");
        aq.enqueue("B");
        aq.enqueue("C");
        System.out.println(aq.size());
        System.out.println(aq.dequeue());
        aq.enqueue("D");
        System.out.println(aq);

    }

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return sz == 0;
    }

    @Override
    public E first() {
        if (isEmpty())
            return null;

        return data[f];
    }

    /**
     * Enqueue in the back of the queue
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {

        if (sz == data.length)
            throw new IllegalStateException("Queue is full");

        int avail = (f + sz) % data.length;
        data[avail] = e;
        sz++;

    }

    /**
     * Dequeue from the front of the queue
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (isEmpty())
            return null;

        E elem = data[f];
        data[f] = null;
        f = (f + 1) % data.length;
        sz--;
        return elem;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("f", f)
                .append("sz", sz)
                .append("data", Arrays.toString(data))
                .toString();
    }
}
