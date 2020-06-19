package algorithm.algorithms.chapter1.union.find;

public class UnionFindMain {

	public static void main(String[] args) {
		int count = 10;
		UnionFind uf1 = new QuickUnion(count);
		uf1.union(6, 2);
		uf1.union(1, 6);
		System.err.println(uf1.count());

		UnionFind uf2 = new QuickFind(count);
		uf2.union(6, 2);
		uf2.union(1, 6);
		System.err.println(uf2.count());

		UnionFind uf3 = new WeightedQuickUnion(count);
		uf3.union(6, 2);
		uf3.union(1, 6);
		System.err.println(uf3.count());
	}


}
