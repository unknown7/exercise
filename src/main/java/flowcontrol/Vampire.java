package flowcontrol;

import java.util.*;

public class Vampire {
    private Inner inner1 = new Inner(1);
    private Inner inner2;
    private static Inner inner9 = new Inner(9);
    public Vampire() {
        inner2 = new Inner(2);
    }

    public static void main(String[] args) {
        new Vampire();
        Map<Integer, String> result = new HashMap<Integer, String>();
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
        screen(ak, ai);
        screen(ak, aj);
        for (int l = 0; l < ak.length; l++) {
            if (ak[l] != -1)
                return false;
        }
        return true;
    }
    private static void screen(int[] source, int[] target) {
        a: for (int l = 0; l < target.length; l++) {
            for (int m = 0; m < source.length; m++) {
                if (source[m] == target[l]) {
                    source[m] = -1;
                    continue a;
                }
            }
        }
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

    static class Inner {
        private int id;
        private String str;
        Inner(int id) {
            System.err.println("Inner:" + id + ",str = " + str);
        }
    }
}
