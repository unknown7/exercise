package algorithm.algorithms.chapter3;

public class BST<K extends Comparable<K>, V> {

	public static void main(String[] args) {
		BST<Integer, Integer> bst = new BST<>();
		bst.put(5, 5);
		bst.put(2, 2);
		bst.put(6, 6);
		bst.put(3, 3);
		System.err.println(bst.ceiling(4));
	}

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

	public K min() {
		if (root == null) return null;
		return min(root).key;
	}

	private Node min(Node node) {
		if (node.left == null) return node;
		return min(node.left);
	}

	public K max() {
		if (root == null) return null;
		return max(root).key;
	}

	private Node max(Node node) {
		if (node.right == null) return node;
		return max(node.right);
	}

	public K floor(K key) {
		Node floor = floor(key, root);
		if (floor == null) return null;
		return floor.key;
	}

	private Node floor(K key, Node node) {
		if (node == null) return null;
		int c = key.compareTo(node.key);
		if (c == 0) return node;
		else if (c < 0) return floor(key, node.left);
		Node floor = floor(key, node.right);
		if (floor != null) return floor;
		else return node;
	}

	public K ceiling(K key) {
		Node ceiling = ceiling(key, root);
		if (ceiling == null) return null;
		return ceiling.key;
	}

	private Node ceiling(K key, Node node) {
		if (node == null) return null;
		int c = key.compareTo(node.key);
		if (c == 0) return node;
		if (c > 0) return ceiling(key, node.right);
		Node ceiling = ceiling(key, node.left);
		if (ceiling != null) return ceiling;
		else return node;
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
