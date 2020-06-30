package algorithm.algorithms.chapter3;

public class BST<K extends Comparable<K>, V> {

	private Node root;

	public void put(K key, V value) {
		root = put(key, value, root);
	}

	private Node put(K key, V value, Node node) {
		if (node == null) return new Node(key, value, 1);
		int c = key.compareTo(node.key);
		if (c > 0) {
			node.right = put(key, value, node.right);
		} else if (c < 0) {
			node.left = put(key, value, node.left);
		} else {
			node.value = value;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public V get(K key) {
		return get(key, root);
	}

	private V get(K key, Node node) {
		if (node == null) return null;
		int c = key.compareTo(node.key);
		if (c > 0) {
			return get(key, node.right);
		} else if (c < 0) {
			return get(key, node.left);
		} else {
			return node.value;
		}
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null) return 0;
		return node.n;
	}

	public class Node {
		private K key;
		private V value;
		private Node left, right;
		private int n;

		public Node(K key, V value, int n) {
			this.key = key;
			this.value = value;
			this.n = n;
		}
	}
}
