package algorithm.algorithms.chapter3;

public class RedBlackBST<K extends Comparable<K>, V> {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private Node root;

	private class Node {
		private K key;
		private V value;
		private Node left, right;
		private int n;
		private boolean color;

		public Node(K key, V value, int n, boolean color) {
			this.key = key;
			this.value = value;
			this.n = n;
			this.color = color;
		}
	}

	public void put(K key, V value) {
		root = put(key, value, root);
		root.color = BLACK;
	}

	private Node put(K key, V value, Node node) {
		if (node == null)
			return new Node(key, value, 1, RED);
		int c = key.compareTo(node.key);
		if (c < 0) {
			node.left = put(key, value, node.left);
		} else if (c > 0) {
			node.right = put(key, value, node.right);
		} else {
			node.value = value;
		}

		if (isRed(node.right) && !isRed(node.left))
			node = rotateLeft(node);
		if (isRed(node.left) && isRed(node.left.left))
			node = rotateRight(node);
		if (isRed(node.left) && isRed(node.right))
			flipColors(node);

		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.n = h.n;
		h.n = size(h.left) + size(h.right) + 1;
		return x;
	}

	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.n = h.n;
		h.n = size(h.left) + size(h.right) + 1;
		return x;
	}

	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	private boolean isRed(Node h) {
		return h.color;
	}

	public int size() {
		return size(root);
	}

	public int size(Node node) {
		if (node == null)
			return 0;
		return node.n;
	}
}
