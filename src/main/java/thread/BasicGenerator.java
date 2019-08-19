package thread;

public class BasicGenerator {
	public static <T> Generator<T> create(final Class<T> clazz) {
		return new Generator<T>() {
			@Override
			public T next() {
				try {
					return clazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
	}
}
