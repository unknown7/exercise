package collection.linkedlist;

import java.util.NoSuchElementException;

public class SList<T> extends AbstractList<T> {
	protected Link<T> header = new Link<T>(null, null);
	public SList() {
		header.next = header;
	}
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Link<T> lastReturned = header;
			private Link<T> next = header.next;
			@Override
			public boolean hasNext() {
				return next != header;
			}
			@Override
			public T next() {
				if (next == header)
					throw new NoSuchElementException();
				lastReturned = next;
				next = next.next;
				return lastReturned.element;
			}
			@Override
			public void add(T element) {
				lastReturned = header;
				Link<T> newLink = new Link<T>(element, next);
				if (header.next == header)
					header.next = newLink;
				else
					for (Link<T> cur = header;; cur = cur.next)
						if (cur.next == next) {
							cur.next = newLink;
							break;
						}
			}
			@Override
			public void remove() {
				if (lastReturned == header)
					throw new IllegalStateException();
				for (Link<T> cur = header;; cur = cur.next)
					if (cur.next == lastReturned) {
						cur.next = lastReturned.next;
						break;
					}
				lastReturned = header;
			}
		};
	}
	@Override
	public void add(T element) {
		Link<T> newLink = new Link<T>(element, header);
		if (header.next == header)
			header.next = newLink;
		else
			for (Link<T> cur = header;; cur = cur.next)
				if (cur.next == header) {
					cur.next = newLink;
					break;
				}
	}
}
