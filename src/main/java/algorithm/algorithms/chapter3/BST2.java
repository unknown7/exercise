package algorithm.algorithms.chapter3;

public class BST2<K extends Comparable<K>, V> {

	public static void main(String[] args) {
		BST2<Integer, String> bst2 = new BST2<>();
		bst2.put(9, "nine");
		bst2.put(3, "three");
		bst2.put(7, "seven");
		bst2.put(1, "one");
		bst2.put(2, "two");
		for (int i = 0; i < bst2.size(); i++) {
			Integer key = bst2.select(i);
			String value = bst2.get(key);
			System.err.println("index:" + bst2.rank(key) + ", key:" + key + ", value:" + value);
		}
	}

	private Node root;

	public void put(K key, V value) {
		root = put(key, value, root);
	}

	private Node put(K key, V value, Node node) {
		if (node == null)
			return new Node(key, value, 1);
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

	public K min() {
		if (root == null)
			return null;
		return min(root).key;
	}

	private Node min(Node node) {
		if (node.left == null)
			return node;
		return min(node.left);
	}

	public K max() {
		if (root == null)
			return null;
		return max(root).key;
	}

	private Node max(Node node) {
		if (node.right == null)
			return node;
		return max(node.right);
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
		else
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
		else
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
		if (c < 0)
			return rank(key, node.left);
		else if (c > 0)
			return 1 + size(node.left) + rank(key, node.right);
		else
			return size(node.left);
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
		private Node left;
		private Node right;
		private K key;
		private V value;
		private int n;

		Node(K key, V value, int n) {
			this.key = key;
			this.value = value;
			this.n = n;
		}
	}
}
