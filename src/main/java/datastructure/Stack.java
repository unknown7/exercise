package datastructure;

public class Stack<T> {
	private class Node<U> {
		private U item;
		private Node<U> next;
		public Node() {
			item = null;
			next = null;
		}
		public Node(U item, Node<U> next) {
			this.item = item;
			this.next = next;
		}
		public boolean end() {
			return item == null && next == null;
		}
	}
	private Node<T> top = new Node<T>(); 
	public void push(T item) {
		top = new Node<T>(item, top);
	}
	public T pop() {
		T result = top.item;
		if (!top.end())
			top = top.next;
		return result;
	}
}
