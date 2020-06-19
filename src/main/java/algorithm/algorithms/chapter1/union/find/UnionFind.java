package algorithm.algorithms.chapter1.union.find;

public interface UnionFind {

	int find(int i);

	void union(int p, int q);

	boolean connected(int p, int q);

	int count();

	void rangeCheck(int p, int q);
}
