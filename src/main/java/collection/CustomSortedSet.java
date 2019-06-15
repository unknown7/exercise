package collection;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.SortedSet;

public class CustomSortedSet<T extends Comparable<T>> implements SortedSet<T> {
	private final LinkedList<T> list;
	public CustomSortedSet() {
		list = new LinkedList<T>();
	}
	@Override
	public int size() {
		return list.size();
	}
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object o) {
		return Collections.binarySearch(list, (T) o) > 0;
	}
	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}
	@Override
	public Object[] toArray() {
		return list.toArray();
	}
	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
	@Override
	public boolean add(T e) {
		int ip = Collections.binarySearch(list, e);
		if (ip < 0) {
			ip = -(ip + 1);
			if (ip == list.size())
				list.add(e);
			else
				list.add(ip, e);
			return true;
		}
		return false;
	}
	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}
	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean res = false;
		for (T item : c)
			res |= add(item);
		return res;
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}
	@Override
	public void clear() {
		list.clear();
	}
	@Override
	public Comparator<? super T> comparator() {
		return null;
	}
	@Override
	public SortedSet<T> subSet(T fromElement, T toElement) {
		SortedSet<T> newSet = new CustomSortedSet<T>();
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T t = it.next();
			if (fromElement.equals(t)) {
				newSet.add(t);
			}
		}
		return null;
	}
	@Override
	public SortedSet<T> headSet(T toElement) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SortedSet<T> tailSet(T fromElement) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T first() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public T last() {
		// TODO Auto-generated method stub
		return null;
	}

}
