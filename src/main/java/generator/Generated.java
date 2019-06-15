package generator;

import java.lang.reflect.Array;
import java.util.List;

public class Generated {
	public static <T> T[] array(Generator<T> gen, Class<T> clazz, int length) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) Array.newInstance(clazz, length);
		return new CollectionData<T>(gen, length).toArray(array);
	}
	public static <T> T[] array(Generator<T> gen, T[] array) {
		return new CollectionData<T>(gen, array.length).toArray(array);
	}
	public static <T> List<T> list(Generator<T> gen, int size) {
		return new CollectionData<T>(gen, size);
	}
}
