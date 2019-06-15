package collection;

public class Counter {
	private Object[][] container = new Object[1][2];
	private int index;
	private int cur;
	public void put(String key) {
		ensureCapacity(index);
		Integer count = get(key);
		if (count == null)
			container[index++] = new Object[] { key, 1 };
		else
			container[cur][1] = ++count;
	}
	public Integer get(String key) {
		for (int i = 0; i < container.length; i++) {
			if (container[i][0] == null) {
				continue;
			} else if (container[i][0].equals(key)) {
				cur = i;
				return Integer.valueOf(String.valueOf(container[i][1]));
			}
		}
		return null;
	}
	private void ensureCapacity(int size) {
		int length = container.length;
		if (length <= size)
			length = (length * 3) / 2 + 1;
		if (length <= size)
			length = size;
		
		Object[][] newArr = new Object[length][2];
		System.arraycopy(container, 0, newArr, 0, container.length);
		container = newArr;
	}
	public void trim2Size() {
		int size = 0;
		for (Object[] o : container) {
			if (o[0] != null)
				size++;
			else
				break;
		}
		Object[][] newArr = new Object[size][2];
		System.arraycopy(container, 0, newArr, 0, size);
		container = newArr;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		int sum = 0;
		for (Object[] o : container) {
			sb.append(o[0]).append(" : ").append(o[1]).append("\n");
			sum += Integer.valueOf(String.valueOf(o[1]));
		}
		return sb.append("sum : ").append(sum).toString();
	}
}
