package generator;

import java.util.AbstractList;

public class CountingIntegerList extends AbstractList<Integer> {
	private int size;
	public CountingIntegerList(int size) {
		if (size < 0)
			size = 0;
		this.size = size;
	}
	@Override
	public Integer get(int index) {
		if (index < 0 || index > size)
			throw new ArrayIndexOutOfBoundsException();
		return Integer.valueOf(index);
	}
	@Override
	public int size() {
		return size;
	}
}
