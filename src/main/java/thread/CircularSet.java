package thread;

public class CircularSet {
	private int[] arr;
	private int length;
	private int index;
	public CircularSet(int length) {
		this.length = length;
		arr = new int[length];
		for (int i = 0; i < arr.length; i++)
			arr[i] = -1;
	}
	public synchronized void add(int i) {
		arr[index] = i;
		index = ++index % length;
	}
	public synchronized boolean contains(int i) {
		for (int j = 0; j < arr.length; j++)
			if (arr[j] == i)
				return true;
		return false;
	}
}
