package algorithm.algorithms.chapter1.stack;

import java.util.Iterator;
import java.util.function.Function;

public class DoubleNodeStack<T> implements Stack<T> {

	public static void main(String[] args) {
		DoubleNodeStack<Integer> stack = new DoubleNodeStack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.addBefore(3, 99);
		stack.addAfterIndex(3, 50);
		while (!stack.isEmpty()) {
			System.err.println(stack.pop());
		}
	}

    private DoubleNode head;

    private DoubleNode tail;

    private int size = 0;

    @Override
    public void push(T value) {
        DoubleNode node = new DoubleNode();
        node.value = value;
        node.next = head;
        if (isEmpty()) {
            tail = node;
        } else {
            head.previous = node;
        }
        head = node;
        size++;
    }

    @Override
    public T pop() {
        if (!isEmpty()) {
            T value = head.value;
            DoubleNode next = head.next;
            if (next != null)
				next.previous = null;
			head = next;
			size--;
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
    		private DoubleNode node = head;
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

    public void addHead(T value) {
        push(value);
    }

    public T removeHead() {
        return pop();
    }

    public void addTail(T value) {
        DoubleNode node = new DoubleNode();
        node.value = value;
        node.previous = tail;
        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    public T removeTail() {
        if (!isEmpty()) {
            T value = tail.value;
            DoubleNode previous = tail.previous;
            previous.next = null;
            tail = previous;
            size--;
            return value;
        }
        throw new RuntimeException();
    }

    public void addBefore(T key, T value) {
        DoubleNode node = head;
        while (node != null) {
            if (node.value.equals(key)) {
                DoubleNode previous = node.previous;
                DoubleNode add = new DoubleNode();
                add.value = value;
                add.previous = previous;
                add.next = node;
                previous.next = add;
                node.previous = add;
                size++;
                return;
            }
            node = node.next;
        }
        throw new RuntimeException();
    }

    public void addAfter(T key, T value) {
        DoubleNode node = head;
        while (node != null) {
            if (node.value.equals(key)) {
                DoubleNode next = node.next;
                DoubleNode add = new DoubleNode();
                add.value = value;
                add.previous = node;
                add.next = next;
                next.previous = add;
                node.next = add;
                size++;
                return;
            }
            node = node.next;
        }
        throw new RuntimeException();
    }

    public void addBeforeIndex(int index, T value) {
        DoubleNode node;
        int mid = size / 2;
        int begin;
		Function<Integer, Integer> operator;
		Function<Integer, Boolean> comparator;
		Function<DoubleNode, DoubleNode> iterator;
        if (index > mid) {
        	begin = size - 1;
			operator = i -> --i;
			comparator = i -> i >= mid;
			iterator = n -> n.previous;
            node = tail;
        } else {
			begin = 0;
			operator = i -> i++;
			comparator = i -> i < mid;
			iterator = n -> n.next;
            node = head;
        }
		for (int i = begin; comparator.apply(i); i = operator.apply(i)) {
			if (i == index) {
				DoubleNode previous = node.previous;
				DoubleNode add = new DoubleNode();
				add.value = value;
				add.next = node;
				add.previous = previous;
				node.previous = add;
				previous.next = add;
				size++;
				return;
			}
			node = iterator.apply(node);
		}
    }

    public void addAfterIndex(int index, T value) {
		DoubleNode node;
		int mid = size / 2;
		int begin;
		Function<Integer, Integer> operator;
		Function<Integer, Boolean> comparator;
		Function<DoubleNode, DoubleNode> iterator;
		if (index > mid) {
			begin = size - 1;
			operator = i -> --i;
			comparator = i -> i >= mid;
			iterator = n -> n.previous;
			node = tail;
		} else {
			begin = 0;
			operator = i -> i++;
			comparator = i -> i < mid;
			iterator = n -> n.next;
			node = head;
		}
		for (int i = begin; comparator.apply(i); i = operator.apply(i)) {
			if (i == index) {
				DoubleNode next = node.next;
				DoubleNode add = new DoubleNode();
				add.value = value;
				add.previous = node;
				add.next = next;
				node.next = add;
				next.previous = add;
				size++;
				return;
			}
			node = iterator.apply(node);
		}
    }

    public T remove(T key){
        return null;
    }

    private class DoubleNode {
        T value;
        DoubleNode previous;
        DoubleNode next;
    }
}
