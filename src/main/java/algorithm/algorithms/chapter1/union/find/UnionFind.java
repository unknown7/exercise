package algorithm.algorithms.chapter1.union.find;

import java.util.Arrays;

public class UnionFind {

	public static void main(String[] args) {
		int count = 10;
		UnionFind uf = new UnionFind(count);
		uf.union(6, 2);
		uf.union(1, 6);
		System.err.println(uf.count());
	}

	private int[] a;
	private int count;

	public UnionFind(int n) {
		count = n;
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = i;
		}
	}

	public int find(int i) {
		return a[i];
	}

	public void union(int p, int q) {
		int pId = find(p);
		int qId = find(q);
		if (pId == qId) return;
		for (int i = 0; i < count; i++) {
			if (a[i] == pId) a[i] = qId;
		}
		count--;
	}

	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	public int count() {
		System.err.println("a:" + Arrays.toString(a));
		return count;
	}
}
