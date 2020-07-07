package algorithm.algorithms.chapter3;

public class BST2<K extends Comparable<K>, V> {

	public static void main(String[] args) {
		BST2<Integer, String> bst2 = new BST2<>();
		bst2.put(6, "six");
		bst2.put(3, "three");
		bst2.put(9, "nine");
		bst2.put(1, "one");
		bst2.put(2, "two");
		bst2.put(7, "seven");
		bst2.put(8, "eight");
		bst2.put(5, "five");
		bst2.delete(5);
		for (int i = 0; i < bst2.size(); i++) {
			System.err.println(bst2.get(bst2.select(i)));
		}
	}

	private Node root;

	public void put(K key, V value) {
		root = put(key, value, root);
	}

	private Node put(K key, V value, Node node) {
		if (node == null)
			return new Node(1, key, value);
		int c = key.compareTo(node.key);
		if (c < 0)
			node.left = put(key, value, node.left);
		else if (c > 0)
			node.right = put(key, value, node.right);
		else
			node.value = value;
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public V get(K key) {
		Node node = get(key, root);
		if (node == null)
			return null;
		return node.value;
	}

	private Node get(K key, Node node) {
		if (node == null)
			return null;
		int c = key.compareTo(node.key);
		if (c < 0)
			return get(key, node.left);
		else if (c > 0)
			return get(key, node.right);
		else
			return node;
	}

	public K max() {
		Node max = max(root);
		if (max == null)
			return null;
		return max.key;
	}

	private Node max(Node node) {
		if (node.right == null)
			return node;
		return max(node.right);
	}

	public K min() {
		Node min = min(root);
		if (min == null)
			return null;
		return min.key;
	}

	private Node min(Node node) {
		if (node.left == null)
			return node;
		return min(node.left);
	}

	public K floor(K key) {
		Node floor = floor(key, root);
		if (floor == null)
			return null;
		return floor.key;
	}

	private Node floor(K key, Node node) {
		if (node == null)
			return null;
		int c = key.compareTo(node.key);
		if (c == 0)
			return node;
		else if (c < 0)
			return floor(key, node.left);
		Node floor = floor(key, node.right);
		if (floor != null)
			return floor;
		return node;
	}

	public K ceiling(K key) {
		Node ceiling = ceiling(key, root);
		if (ceiling == null)
			return null;
		return ceiling.key;
	}

	private Node ceiling(K key, Node node) {
		if (node == null)
			return null;
		int c = key.compareTo(node.key);
		if (c == 0)
			return node;
		else if (c > 0)
			return ceiling(key, node.right);
		Node ceiling = ceiling(key, node.left);
		if (ceiling != null)
			return ceiling;
		return node;
	}

	public K select(int index) {
		Node select = select(index, root);
		if (select == null)
			return null;
		return select.key;
	}

	private Node select(int index, Node node) {
		if (node == null)
			return null;
		int t = size(node.left);
		if (index == t)
			return node;
		else if (index < t)
			return select(index, node.left);
		else
			return select(index - t - 1, node.right);
	}

	public int rank(K key) {
		return rank(key, root);
	}

	private int rank(K key, Node node) {
		if (node == null)
			return 0;
		int c = key.compareTo(node.key);
		if (c == 0)
			return size(node.left);
		else if (c < 0)
			return rank(key, node.left);
		else
			return 1 + size(node.left) + rank(key, node.right);
	}

	public void deleteMax() {
		root = deleteMax(root);
	}

	private Node deleteMax(Node node) {
		if (node == null)
			return null;
		if (node.right == null)
			return node.left;
		node.right = deleteMax(node.right);
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public void deleteMin() {
		root = deleteMin(root);
	}

	private Node deleteMin(Node node) {
		if (node == null)
			return null;
		if (node.left == null)
			return node.right;
		node.left = deleteMin(node.left);
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public void delete(K key) {
		root = delete(key, root);
	}

	private Node delete(K key, Node node) {
		if (node == null)
			return null;
		int c = key.compareTo(node.key);
		if (c < 0)
			node.left = delete(key, node.left);
		else if (c > 0)
			node.right = delete(key, node.right);
		else {
			if (node.left == null)
				return node.right;
			if (node.right == null)
				return node.left;
			Node t = node;
			node = min(t.right);
			node.right = deleteMin(t.right);
			node.left = t.left;
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}

	public int size() {
		return size(root);
	}

	private int size(Node node) {
		if (node == null)
			return 0;
		return node.n;
	}

	private class Node {
		private Node left, right;
		private int n;
		private K key;
		private V value;

		public Node(int n, K key, V value) {
			this.n = n;
			this.key = key;
			this.value = value;
		}
	}
}
