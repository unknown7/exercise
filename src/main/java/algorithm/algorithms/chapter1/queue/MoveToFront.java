package algorithm.algorithms.chapter1.queue;

import algorithm.algorithms.chapter1.stack.Stack;

import java.util.Iterator;

public class MoveToFront implements Stack<Integer> {

	public static void main(String[] args) {
		MoveToFront stack = new MoveToFront();
		for (int i = 0; i < 10; i++) {
			stack.push(i);
		}
		stack.push(1);
		stack.push(2);
		stack.push(3);
		while (!stack.isEmpty()) {
			System.err.println(stack.pop());
		}
	}

	private Node first;

	private int size = 0;

	public void push(Integer value) {
		Node node = first;
		while (node != null) {
			Node next = node.next;
			if (next != null) {
				Integer v = next.value;
				if (v.equals(value)) {
					node.next = next.next;
					next.next = first;
					first = next;
					return;
				}
			}
			node = node.next;
		}
		Node add = new Node();
		add.value = value;
		add.next = first;
		first = add;
		size++;
	}

	public Integer pop() {
		if (!isEmpty()) {
			Integer value = first.value;
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
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			Node node = first;
			@Override
			public boolean hasNext() {
				return node != null;
			}

			@Override
			public Integer next() {
				Integer value = node.value;
				node = node.next;
				return value;
			}
		};
	}

	private class Node {
		Integer value;
		Node next;
	}
}
