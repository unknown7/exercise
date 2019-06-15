package collection;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;

public class SlowSet<E> extends AbstractSet<E> {
	private E[] elements;
	private int index;
	@SuppressWarnings("unchecked")
	public SlowSet() {
		elements = (E[]) new Object[0];
	}
	@SuppressWarnings("unchecked")
	public SlowSet(int capacity) {
		elements = (E[]) new Object[capacity];
	}
	@Override
	public boolean add(E e) {
		ensureCapacity(index + 1);
		if (!contains(e)) {
			elements[index++] = e;
			return true;
		}
		return false;
	}
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index = 0;
			@Override
			public boolean hasNext() {
				return this.index < SlowSet.this.index;
			}
			@Override
			public E next() {
				return (E) elements[index++];
			}
			@Override
			public void remove() {
			}
		};
	}
	@Override
	public int size() {
		return index;
	}
	private void ensureCapacity(int size) {
		int length = elements.length;
		if (length < size) {
			length = (length * 3) / 2 + 1;
			if (length < size)
				length = size;
			elements = Arrays.copyOf(elements, length);
		}
	}
}
