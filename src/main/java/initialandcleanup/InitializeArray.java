package initialandcleanup;

import java.util.Arrays;

public class InitializeArray {
    public static void main(String[] args) {
        Integer[] array1 = new Integer[10];
        int[] array2 = new int[10];
        System.err.println(Arrays.toString(array1));
        System.err.println(Arrays.toString(array2));
        for (int i = 0; i < array1.length; i++) {
            System.err.println(array1[i].intValue());
        }
    }
}
