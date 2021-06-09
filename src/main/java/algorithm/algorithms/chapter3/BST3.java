package algorithm.algorithms.chapter3;

public class BST3<K extends Comparable<K>, V> {

	private Node<K, V> root;

	public void put(K key, V value) {
		this.root = put(key, value, root);
	}

	public Node<K, V> put(K key, V value, Node<K, V> node) {
		if (node == null)
			return new Node<>(key, value, 1);
		int i = key.compareTo(node.key);
		if (i == 0) node.value = value;
		else if (i < 0) node.left = put(key, value, node.left);
		else node.right = put(key, value, node.right);
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public V get(K key) {
		Node<K, V> node = get(key, root);
		if (node == null) return null;
		else return node.value;
	}

	public Node<K, V> get(K key, Node<K, V> node) {
		if (node == null) return null;
		int i = key.compareTo(node.key);
		if (i == 0) return node;
		else if (i < 0) return get(key, node.left);
		else return get(key, node.right);
	}

	public K max() {
		if (root == null) return null;
		return max(root).key;
	}

	public Node<K, V> max(Node<K, V> node) {
		if (node.right == null) return node;
		return max(node.right);
	}

	public K min() {
		if (root == null) return null;
		return min(root).key;
	}

	public Node<K, V> min(Node<K, V> node) {
		if (node.left == null) return node;
		return min(node.left);
	}

	public K floor(K key) {
		Node<K, V> floor = floor(key, root);
		if (floor == null) return null;
		return floor.key;
	}

	public Node<K, V> floor(K key, Node<K, V> node) {
		if (node == null) return null;
		int i = key.compareTo(node.key);
		if (i == 0) return node;
		else if (i < 0) return floor(key, node.left);
		Node<K, V> floor = floor(key, node.right);
		if (floor == null) return node;
		return floor;
	}

	public K ceiling(K key) {
		Node<K, V> ceiling = ceiling(key, root);
		if (ceiling == null) return null;
		return ceiling.key;
	}

	public Node<K, V> ceiling(K key, Node<K, V> node) {
		if (node == null) return null;
		int i = key.compareTo(node.key);
		if (i == 0) return node;
		else if (i > 0) return ceiling(key, node.right);
		Node<K, V> ceiling = ceiling(key, node.left);
		if (ceiling == null) return node;
		return ceiling;
	}

	public K select(int i) {
		Node<K, V> select = select(i, root);
		if (select == null) return null;
		return select.key;
	}

	public Node<K, V> select(int i, Node<K, V> node) {
		if (node == null) return null;
		int size = size(node.left);
		if (i == size) return node;
		else if (i < size) return select(i, node.left);
		else return select(i - size - 1, node.right);
	}

	public int rank(K key) {
		return rank(key, root);
	}

	public int rank(K key, Node<K, V> node) {
		if (node == null) return 0;
		int i = key.compareTo(node.key);
		if (i == 0) return size(node.left);
		else if (i < 0) return rank(key, node.left);
		else return 1 + size(node.left) + rank(key, node.right);
	}

	public void deleteMin() {
		this.root = deleteMin(root);
	}

	public Node<K, V> deleteMin(Node<K, V> node) {
		if (node == null) return null;
		if (node.left == null) return node.right;
		node.left = deleteMin(node.left);
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public int size() {
		return size(root);
	}

	public int size(Node<K, V> node) {
		if (node == null) return 0;
		return node.n;
	}

	private class Node<K, V> {

		private K key;

		private V value;

		private Node<K, V> left;

		private Node<K, V> right;

		private int n;

		public Node(K key, V value, int n) {
			this.key = key;
			this.value = value;
			this.n = n;
		}

		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public V getValue() {
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

		public Node<K, V> getLeft() {
			return left;
		}

		public void setLeft(Node<K, V> left) {
			this.left = left;
		}

		public Node<K, V> getRight() {
			return right;
		}

		public void setRight(Node<K, V> right) {
			this.right = right;
		}

		public int getN() {
			return n;
		}

		public void setN(int n) {
			this.n = n;
		}
	}
}
