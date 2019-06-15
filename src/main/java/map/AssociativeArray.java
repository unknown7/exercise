package map;

import java.util.Arrays;

public class AssociativeArray<K, V> {
	private Object[][] pairs = new Object[0][2];
	private int index;
	private final Object[][] EMPTY_ARRAY = {};

	public void put(K key, V value) {
		ensureCapacity(index + 1);
		
		for (Object[] pair : pairs)
			if (key.equals(pair[0])) {
				pair[1] = value;
				return;
			}
		pairs[index++] = new Object[] { key, value };
	}

	@SuppressWarnings("unchecked")
	public V get(K key) {
		if (isEmpty())
			return null;
		for (Object[] pair : pairs)
			if (key.equals(pair[0]))
				return (V) pair[1];
		return null;
	}

	public int size() {
		return pairs.length;
	}

	public boolean isEmpty() {
		return size() <= 0;
	}

	private void ensureCapacity(int size) {
		int oldCapacity = pairs.length;
		if (oldCapacity < size) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < size)
				newCapacity = size;
			pairs = Arrays.copyOf(pairs, newCapacity);
			for (int i = oldCapacity; i < pairs.length; i++)
				pairs[i] = new Object[2];
		}
	}
	
	public void trimToSize() {
		if (index < pairs.length)
			pairs = (index == 0) ? EMPTY_ARRAY : Arrays.copyOf(pairs, index);
	}

	@Override
	public String toString() {
		trimToSize();
		StringBuffer buf = new StringBuffer();
		buf.append('{');
		for (int i = 0, j = pairs.length; i < j; i++) {
			buf.append(pairs[i][0]).append('=').append(pairs[i][1]);
			if (i < j - 1)
				buf.append(',').append(' ');
		}
		buf.append('}');
		return buf.toString();
	}
}
