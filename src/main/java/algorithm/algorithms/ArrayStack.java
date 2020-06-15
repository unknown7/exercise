package algorithm.algorithms;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayStack<Item> implements Iterable<Item> {
    private Item[] arr = (Item[]) new Object[10];
    private int size = 0;

    public void push(Item item) {
        ensureCapacity();
        arr[size++] = item;
    }

    public Item pop() {
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
            Item[] newArr = Arrays.copyOf(arr, size << 1);
            arr = newArr;
        }
        if (size <= arr.length / 4) {
            Item[] newArr = Arrays.copyOf(arr, arr.length >> 1);
            arr = newArr;
        }
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int i = size;
            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Item next() {
                return arr[--i];
            }
        };
    }
}
