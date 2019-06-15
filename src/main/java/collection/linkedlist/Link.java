package collection.linkedlist;

public class Link<T> {
	T element;
	Link<T> next;
	public Link(T element, Link<T> next) {
		this.element = element;
		this.next = next;
	}
}
