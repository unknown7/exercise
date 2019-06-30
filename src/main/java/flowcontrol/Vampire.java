package flowcontrol;

import java.util.*;

public class Vampire {
    public static void main(String[] args) {
        Map<Integer, String> result = new HashMap<>();
        for (int i = 10; i < 100; i++) {
            for (int j = 10; j < 100; j++) {
                int k = i * j;
                if ((k / 1000 > 0) && contains(k, i, j)) {
                    result.put(k, i + " * " + j + " = " + k);
                }
            }
        }
        for (Map.Entry<Integer, String> entry : result.entrySet()) {
            System.err.println(entry.getValue());
        }
    }
    private static boolean contains(int k, int i, int j) {
        int[] ak = int2array(k),
                ai = int2array(i),
                aj = int2array(j);
        a: for (int l = 0; l < ai.length; l++) {
            for (int m = 0; m < ak.length; m++) {
                if (ak[m] == ai[l]) {
                    ak[m] = -1;
                    continue a;
                }
            }
        }

        b: for (int l = 0; l < aj.length; l++) {
            for (int m = 0; m < ak.length; m++) {
                if (ak[m] == aj[l]) {
                    ak[m] = -1;
                    continue b;
                }
            }
        }

        for (int l = 0; l < ak.length; l++) {
            if (ak[l] != -1)
                return false;
        }
        return true;
    }
    private static int[] int2array(int x) {
        int[] array = new int[10];
        int cnt = 0;
        while (x > 0) {
            int t = x % 10;
            array[cnt++] = t;
            x /= 10;
        }
        return Arrays.copyOf(array, cnt);
    }
}
