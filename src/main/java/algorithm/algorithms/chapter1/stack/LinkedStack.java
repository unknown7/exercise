package algorithm.algorithms.chapter1.stack;

import java.util.Iterator;

public class LinkedStack<T> implements Stack<T> {

    private Node first;

    private int size = 0;

    public void push(T value) {
        Node old = first;
        first = new Node();
        first.setValue(value);
        first.setNext(old);
        size++;
    }

    public T pop() {
        if (!isEmpty()) {
            T value = first.getValue();
            first = first.next;
            size--;
            return value;
        }
        throw new RuntimeException();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node node = first;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

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
}
