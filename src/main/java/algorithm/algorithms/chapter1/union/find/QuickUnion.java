package algorithm.algorithms.chapter1.union.find;

import java.util.Arrays;

public class QuickUnion implements UnionFind {

	private int[] a;
	private int count;

	public QuickUnion(int n) {
		count = n;
		a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = i;
		}
	}

	@Override
	public int find(int i) {
		while (i != a[i])
			i = a[i];
		return i;
	}

	@Override
	public void union(int p, int q) {
		this.rangeCheck(p, q);
		int pId = find(p);
		int qId = find(q);
		if (pId == qId) return;
		a[pId] = qId;
		count--;
	}

	@Override
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	@Override
	public int count() {
		System.err.println("a:" + Arrays.toString(a));
		return count;
	}

	@Override
	public void rangeCheck(int p, int q) {
		if (p < 0 || p > a.length || q < 0 || q > a.length)
			throw new RuntimeException("out of range!");
	}
}
