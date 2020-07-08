package algorithm.algorithms;

import java.util.Arrays;

public class RangeMerge {

	public static void main(String[] args) {
		Queue result = new Queue();
		Stack storage = init();
		while (!storage.isEmpty()) {
			int[] a = storage.pop();
			if (!storage.isEmpty()) {
				int[] b = storage.pop();
				if (a[1] >= b[0]) {
					storage.push(new int[] {a[0], b[1]});
				} else {
					storage.push(b);
					result.enqueue(a);
				}
			} else {
				result.enqueue(a);
			}
		}
		while (!result.isEmpty()) {
			System.err.println(Arrays.toString(result.dequeue()));
		}
	}

	private static Stack init() {
		int[] a = {1,2};
		int[] b = {2,4};
		int[] c = {3,7};
		int[] d = {8,11};
		int[] e = {10,66};
		int[] f = {70,999};
		Stack stack1 = new Stack();
		stack1.push(a);
		stack1.push(b);
		stack1.push(c);
		stack1.push(d);
		stack1.push(e);
		stack1.push(f);
		Stack stack2 = new Stack();
		while (!stack1.isEmpty())
			stack2.push(stack1.pop());
		return stack2;
	}

	private static class Queue {
		private Node head;
		private Node tail;
		private int n;

		public void enqueue(int[] v) {
			Node node = new Node(v);
			if (tail == null && head == null) {
				tail = node;
				head = node;
			} else {
				tail.next = node;
				tail = node;
			}
			n++;
		}

		public int[] dequeue() {
			if (tail == null && head == null)
				throw new RuntimeException();
			int[] v = head.v;
			head = head.next;
			n--;
			return v;
		}
		
		public boolean isEmpty() {
			return n == 0;
		}
	}

	private static class Stack {
		private Node first;
		private int n;

		public void push(int[] v) {
			Node node = new Node(v);
			if (first == null) {
				first = node;
			} else {
				node.next = first;
				first = node;
			}
			n++;
		}

		public int[] pop() {
			if (first == null)
				throw new RuntimeException();
			int[] v = first.v;
			first = first.next;
			n--;
			return v;
		}

		public boolean isEmpty() {
			return n == 0;
		}

		public int size() {
			return n;
		}
	}

	private static class Node {
		int[] v;
		Node next;

		public Node(int[] v) {
			this.v = v;
		}
	}
}
