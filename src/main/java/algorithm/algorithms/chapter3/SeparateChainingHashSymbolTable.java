package algorithm.algorithms.chapter3;

public class SeparateChainingHashSymbolTable<K, V> {

	public static void main(String[] args) {
		SeparateChainingHashSymbolTable<Integer, String> table = new SeparateChainingHashSymbolTable<>(8);
		table.put(1, "one");
		table.put(2, "two");
		table.put(3, "three");
		table.put(4, "four");
		table.put(5, "five");
		table.put(6, "six");
		table.put(7, "seven");
		table.put(8, "eight");
		table.put(9, "nine");
		table.put(10, "ten");
		table.put(11, "eleven");
		System.err.println(table.get(11));
		System.err.println(table.delete(10));
	}

	private int m;

	private int n;

	private Entry<K, V>[] entries;

	public SeparateChainingHashSymbolTable(int m) {
		this.m = m;
		entries = (Entry<K, V>[]) new Entry[m];
	}

	public void put(K key, V value) {
		ensureCapacity();
		int index = indexOf(key);
		Entry<K, V> first = entries[index];
		Entry<K, V> entry = first;
		while (entry != null) {
			if (entry.key.equals(key)) {
				entry.value = value;
				return;
			}
			entry = entry.next;
		}
		entries[index] = new Entry<>(key, value, first);
		n++;
	}

	public V get(K key) {
		int index = indexOf(key);
		Entry<K, V> entry = entries[index];
		while (entry != null) {
			if (entry.key.equals(key)) {
				return entry.value;
			}
			entry = entry.next;
		}
		return null;
	}

	public V delete(K key) {
		ensureCapacity();
		int index = indexOf(key);
		Entry<K, V> entry = entries[index];
		if (entry != null && entry.key.equals(key)) {
			entries[index] = entry.next;
			n--;
			return entry.value;
		}
		while (entry != null && entry.next != null) {
			Entry<K, V> next = entry.next;
			if (next.key.equals(key)) {
				entry.next = next.next;
				n--;
				return next.value;
			}
			entry = next;
		}
		return null;
	}

	private void ensureCapacity() {
		Entry<K, V>[] snapshot = entries;
		int size = entries.length;
		if (n / 2 >= m) {
			size = m * 2;
		}
		if (n <= m / 8) {
			size = m / 2;
		}
		if (size != entries.length) {
			n = 0;
			m = size;
			entries = (Entry<K, V>[]) new Entry[m];
			for (int i = 0; i < snapshot.length; i++) {
				Entry<K, V> entry = snapshot[i];
				while (entry != null) {
					put(entry.key, entry.value);
					entry = entry.next;
				}
			}
		}
	}

	private int indexOf(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	private class Entry<K, V> {
		private K key;
		private V value;
		private Entry<K, V> next;

		public Entry(K key, V value, Entry<K, V> next) {
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}
}
