package generator;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Countries {
	public static final String[][] DATA = { { "CHINA", "Beijing" },
			{ "INDIA", "New Delhi" }, { "JAPAN", "Tokyo" },
			{ "FRANCE", "Paris" }, { "GERMANY", "Berlin" },
			{ "GREECE", "Athens" },
			{ "UNITED STATES OF AMERICA", "Washington, D.C" } };

	private static class FlyweightMap extends AbstractMap<String, String> {
		private static class Entry implements Map.Entry<String, String> {
			int index;

			public Entry(int index) {
				this.index = index;
			}

			@Override
			public boolean equals(Object obj) {
				return DATA[index][0].equals(obj);
			}

			@Override
			public String getKey() {
				return DATA[index][0];
			}

			@Override
			public String getValue() {
				return DATA[index][1];
			}

			@Override
			public String setValue(String value) {
				throw new UnsupportedOperationException();
			}

			@Override
			public int hashCode() {
				return DATA[index][0].hashCode();
			}
		}

		static class EntrySet extends AbstractSet<Map.Entry<String, String>> {
			private int size;

			public EntrySet(int size) {
				if (size < 0)
					size = 0;
				else if (size > DATA.length)
					size = DATA.length;
				this.size = size;
			}

			@Override
			public Iterator<Map.Entry<String, String>> iterator() {
				return new Iterator<Map.Entry<String, String>>() {
					private Entry entry = new Entry(-1);

					@Override
					public boolean hasNext() {
						return entry.index < size - 1;
					}

					@Override
					public Map.Entry<String, String> next() {
						entry.index++;
						return entry;
					}

					@Override
					public void remove() {
					}
				};
			}

			@Override
			public int size() {
				return size;
			}
		}

		@Override
		public Set<Map.Entry<String, String>> entrySet() {
			return new EntrySet(DATA.length);
		}
	}

	static Map<String, String> select(final int size) {
		return new FlyweightMap() {
			@Override
			public Set<Map.Entry<String, String>> entrySet() {
				return new EntrySet(size);
			}
		};
	}
	
	static Map<String, String> map = new FlyweightMap();
	
	public static Map<String, String> capitals() {
		return map;
	}
	
	public static Map<String, String> capitals(int size) {
		return select(size);
	}
	
	static List<String> names = new ArrayList<String>(map.keySet());
	
	public static List<String> names() {
		return names;
	}
	
	public static List<String> names (int size) {
		return new ArrayList<String>(select(size).keySet());
	}
}
