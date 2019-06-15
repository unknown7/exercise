package collection.linkedlist;

public abstract class AbstractList<T> {
	protected Link<T> header = new Link<T>(null, null);
	public AbstractList() {
		header.next = header;
	}
	public abstract Iterator<T> iterator();
	public abstract void add(T element);
	public boolean contains(T element) {
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T e = it.next();
			if (e != null && e.equals(element))
				return true;
		}
		return false;
	}
	public int size() {
		Iterator<T> it = iterator();
		int size = 0;
		while (it.hasNext()) {
			it.next();
			size++;
		}
		return size;
	}
	@Override
	public String toString() {
		Iterator<T> it = iterator();
		if (!it.hasNext())
			return "[]";
		StringBuffer sb = new StringBuffer();
		sb.append('[');
		for (;;) {
			T e = it.next();
			sb.append(e == this ? "(this SList)" : String.valueOf(e));
			if (!it.hasNext())
				return sb.append(']').toString();
			sb.append(',').append('\n');
		}
	}
}
