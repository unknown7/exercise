package algorithm.algorithms.chapter3;

public class LinerProbingHashSymbolTable<K, V> {

	public static void main(String[] args) {
		LinerProbingHashSymbolTable<Integer, String> table = new LinerProbingHashSymbolTable<>(8);
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
	}

	private int m;

	private int n;

	private K[] keys;

	private V[] values;

	public LinerProbingHashSymbolTable(int m) {
		this.m = m;
		keys = (K[]) new Object[m];
		values = (V[]) new Object[m];
	}

	public void put(K key, V value) {
		ensureCapacity();
		putDirectly(key, value);
	}

	private void putDirectly(K key, V value) {
		int index = indexOf(key);
		for (; keys[index] != null; index = (index + 1) % m) {
			if (keys[index].equals(key)) {
				values[index] = value;
				return;
			}
		}
		keys[index] = key;
		values[index] = value;
		n++;
	}

	public V get(K key) {
		for (int index = indexOf(key); keys[index] != null; index = (index + 1) % m) {
			if (keys[index].equals(key)) {
				return values[index];
			}
		}
		return null;
	}

	private void ensureCapacity() {
		K[] snapshotKeys = keys;
		V[] snapshotValues = values;
		int size = keys.length;
		if (n >= m / 2) {
			size = m * 2;
		}
		if (n * 4 <= m) {
			size = m / 2;
		}
		if (size != keys.length) {
			n = 0;
			m = size;
			keys = (K[]) new Object[size];
			values = (V[]) new Object[size];
			for (int i = 0; i < snapshotKeys.length; i++) {
				K key = snapshotKeys[i];
				V value = snapshotValues[i];
				if (key != null) {
					putDirectly(key, value);
				}
			}
		}
	}

	private int indexOf(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}
}
