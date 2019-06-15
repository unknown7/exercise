package collection.linkedlist;

public interface Iterator<T> {
	boolean hasNext();
	T next();
	void add(T element);
	void remove();
}
