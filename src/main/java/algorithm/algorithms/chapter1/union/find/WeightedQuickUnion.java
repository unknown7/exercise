package algorithm.algorithms.chapter1.union.find;

import java.util.Arrays;

public class WeightedQuickUnion implements UnionFind {

	private int[] a;
	private int[] weight;
	private int count;

	public WeightedQuickUnion(int n) {
		count = n;
		a = new int[n];
		weight = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = i;
			weight[i] = 1;
		}
	}

	@Override
	public int find(int i) {
		while (i != a[i]) i = a[i];
		return i;
	}

	@Override
	public void union(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) return;
		if (weight[pRoot] < weight[qRoot]) {
			a[pRoot] = qRoot;
			weight[qRoot] += weight[pRoot];
		} else {
			a[qRoot] = pRoot;
			weight[pRoot] += weight[qRoot];
		}
		count--;
	}

	@Override
	public boolean connected(int p, int q) {
		return find(p) == find(q);
	}

	@Override
	public int count() {
		System.err.println("a:" + Arrays.toString(a));
		System.err.println("weight:" + Arrays.toString(weight));
		return count;
	}

	@Override
	public void rangeCheck(int p, int q) {
		if (p < 0 || p > a.length || q < 0 || q > a.length)
			throw new RuntimeException("out of range!");
	}
}
