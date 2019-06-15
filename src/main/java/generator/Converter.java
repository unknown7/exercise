package generator;

public class Converter {
	public static int[] convert(Integer[] arr) {
		int[] result = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			result[i] = arr[i];
		return result;
	}
}
