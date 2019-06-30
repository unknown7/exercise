package flowcontrol;

import java.util.Arrays;

public class Vampire {
    public static void main(String[] args) {
        for (int i = 10; i < 100; i++) {
            for (int j = 10; j < 100; j++) {
                int k = i * j;

            }
        }
    }
    private static boolean contains(int k, int i, int j) {
        int[] ak = int2array(k),
                ai = int2array(i),
                aj = int2array(j);
    }
    private static int[] int2array(int x) {
        int[] array = new int[Integer.MAX_VALUE];
        int cnt = 0;
        while (x > 0) {
            int t = x % 10;
            array[cnt++] = t;
            x /= 10;
        }
        return Arrays.copyOf(array, cnt);
    }
}
