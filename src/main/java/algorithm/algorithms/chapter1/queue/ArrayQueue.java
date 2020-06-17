package algorithm.algorithms.chapter1.queue;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayQueue<T> implements Queue<T> {

    private int capacity = 10;

    private T[] arr = (T[]) new Object[capacity];

    private int size = 0;

    private int index = 0;

    @Override
    public void enqueue(T value) {
        ensureCapacity();
        arr[size++] = value;
    }

    @Override
    public T dequeue() {
        ensureCapacity();
        T value = arr[index];
        arr[index] = null;
        index++;
        size--;
        return value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (arr.length >= capacity) {
            arr = Arrays.copyOf(arr, capacity << 1);
        }
        if (arr.length / 4 <= capacity) {
            arr = Arrays.copyOf(arr, arr.length >> 1);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                return arr[i++];
            }
        };
    }
}
