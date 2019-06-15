package collection.arraylist;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SimpleArrayList<E> extends AbstractList<E> {
	private Object[] elementData;
	private int size;
	public SimpleArrayList() {
		this(10);
	}
	public SimpleArrayList(int size) {
		elementData = new Object[size];
	}
	public SimpleArrayList(Collection<? extends E> c) {
		elementData = c.toArray();
	}
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	@Override
	public boolean contains(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (elementData[i].equals(o)) {
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int index;
			@Override
			public boolean hasNext() {
				return index < size;
			}
			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				return (E) elementData[index++];
			}
			@Override
			public void remove() {
			}
		};
	}
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elementData, size);
	}
	@Override
	public <T> T[] toArray(T[] a) {
		System.arraycopy(a, 0, elementData, 0, size);
		return a;
	}
	@Override
	public boolean add(E e) {
		ensureCapacity(size + 1);
		elementData[size++] = e;
		return true;
	}
	@Override
	public boolean remove(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					remove(i);
					return true;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (elementData[i].equals(o)) {
					remove(i);
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if (contains(o)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean addAll(Collection<? extends E> c) {
		Object[] array = c.toArray();
		int increments = array.length;
		ensureCapacity(size + increments);
		System.arraycopy(array, 0, elementData, size, increments);
		size += increments;
		return true;
	}
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Object[] array = c.toArray();
		int increments = array.length;
		ensureCapacity(size + increments);
		int movedLength = size - index;
		System.arraycopy(elementData, index, elementData, index + increments, movedLength);
		System.arraycopy(array, 0, elementData, index, increments);
		size += increments;
		return true;
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		boolean removed = true;
		for (Object o : c) {
			if (!remove(o)) {
				removed = !removed;
			}
		}
		return removed;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		for (int i = 0; i < elementData.length; i++) {
			if (!c.contains(elementData[i])) {
				elementData[i] = null;
			}
		}
		return true;
	}
	@Override
	public void clear() {
		size = 0;
		elementData = new Object[0];
	}
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		return (E) elementData[index];
	}
	@Override
	public E set(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		@SuppressWarnings("unchecked")
		E old = (E) elementData[index];
		elementData[index] = element;
		return old;
	}
	@Override
	public void add(int index, E element) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = element;
		size++;
	}
	@Override
	public E remove(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		@SuppressWarnings("unchecked")
		E old = (E) elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		elementData[size] = null;
		return old;
	}
	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (elementData[i].equals(o)) {
					return i;
				}
			}
		}
		return -1;
	}
	@Override
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size - 1; i >= 0; i--) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = size - 1; i >= 0; i--) {
				if (elementData[i].equals(o)) {
					return i;
				}
			}
		}
		return -1;
	}
	@Override
	public ListIterator<E> listIterator() {
		return null;
	}
	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return null;
	}
	@Override
	public String toString() {
		Iterator<E> it = iterator();
		if (!it.hasNext())
			return "[]";
		
		StringBuffer sb = new StringBuffer();
		sb.append('[');
		for (;;) {
			E e = it.next();
			sb.append(e == this ? "(this SimpleArrayList)" : e);
			if (!it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append(' ');
		}
	}
	public void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (minCapacity > newCapacity) {
				newCapacity = minCapacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}
}
