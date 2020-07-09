package algorithm.algorithms;

public class LinkSkipReverse {

	public static void main(String[] args) {
		Node root = new Node(1);
		Node n = root;
		for (int i = 2; i < 20; i++) {
			Node node = new Node(i);
			n.next = node;
			n = node;
		}
		Node node = root;
		while (node != null) {
			System.err.print(node.v + " ");
			node = node.next;
		}
		System.err.println();
		Node reverse = reverse(root);
		while (reverse != null) {
			System.err.print(reverse.v + " ");
			reverse = reverse.next;
		}
	}

	private static Node reverse(Node f) {
		if (f == null)
			return null;
		Node s = f.next;
		if (s == null)
			return f;
		Node t = s.next;
		s.next = f;
		f.next = reverse(t);
		return s;
	}

	private static class Node {
		private int v;
		private Node next;

		public Node(int v) {
			this.v = v;
		}
	}
}
