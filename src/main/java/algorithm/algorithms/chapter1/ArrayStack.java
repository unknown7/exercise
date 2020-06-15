package algorithm.algorithms.chapter1;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayStack<T> implements Stack<T> {
    private T[] arr = (T[]) new Object[10];
    private int size = 0;

    public void push(T item) {
        ensureCapacity();
        arr[size++] = item;
    }

    public T pop() {
        if (!isEmpty())
            return arr[--size];
        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size + 1 >= arr.length) {
            T[] newArr = Arrays.copyOf(arr, size << 1);
            arr = newArr;
        }
        if (size <= arr.length / 4) {
            T[] newArr = Arrays.copyOf(arr, arr.length >> 1);
            arr = newArr;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int i = size;
            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public T next() {
                return arr[--i];
            }
        };
    }
}
