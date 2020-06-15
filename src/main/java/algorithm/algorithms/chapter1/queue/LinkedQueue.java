package algorithm.algorithms.chapter1.queue;

import java.util.Iterator;

public class LinkedQueue<T> implements Queue<T> {

    private Node first;

    private Node last;

    private int size = 0;

    private class Node {
        T value;
        Node next;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    @Override
    public void enqueue(T value) {
        Node old = last;
        last = new Node();
        last.value = value;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            old.next = last;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (!isEmpty()) {
            T value = first.value;
            first = first.next;
            size--;
            if (isEmpty()) {
                last = null;
            }
            return value;
        }
        throw new RuntimeException();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node n = first;
            @Override
            public boolean hasNext() {
                return n != null;
            }

            @Override
            public T next() {
                T value = n.value;
                n = n.next;
                return value;
            }
        };
    }
}
