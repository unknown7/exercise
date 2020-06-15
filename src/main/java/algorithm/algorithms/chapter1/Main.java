package algorithm.algorithms.chapter1;

public class Main {

    public static void main(String[] args) {
        // gcd
        System.err.println(gcd(18, 12));

        // binary search
        int[] a = {1, 2, 4, 6, 7, 8, 99};
        int key = 8;
        System.err.println(binary(key, a));
        System.err.println(binary(key, a, 0, a.length - 1));

        // fibonacci
        int n = 45;
        // fibonacci1
        long begin = System.currentTimeMillis();
        fibonacci1(n);
        long end = System.currentTimeMillis();
        System.err.println("\nfibonacci1 used " + (end - begin) / 1000.0 + "s");

        System.err.println();

        // fibonacci2
        long[] l = new long[n];
        l[0] = 0;
        l[1] = 1;
        for (int i = 2; i < n; i++) {
            l[i] = -1;
        }
        begin = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            System.err.print(fibonacci2(i));
            System.err.print(" ");
        }
        end = System.currentTimeMillis();
        System.err.println("\nfibonacci2 used " + (end - begin) / 1000.0 + "s");

        System.err.println();

        begin = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            System.err.print(fibonacci3(i, l));
            System.err.print(" ");
        }
        end = System.currentTimeMillis();
        System.err.println("\nfibonacci3 used " + (end - begin) / 1000.0 + "s");

        // something else
    }

    private static int gcd(int p, int q) {
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }

    private static int binary(int key, int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) {
                lo = mid + 1;
            } else if (key < a[mid]) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private static int binary(int key, int[] a, int lo, int hi) {
        if (lo > hi) return -1;
        int mid = lo + (hi - lo) / 2;
        if (key > a[mid]) {
            return binary(key, a, mid + 1, hi);
        } else if (key < a[mid]) {
            return binary(key, a, lo, mid - 1);
        } else {
            return mid;
        }
    }

    private static void fibonacci1(long n) {
        long f = 0;
        long g = 1;
        for (int i = 0; i < n; i++) {
            System.err.print(f);
            System.err.print(" ");
            f = f + g;
            g = f - g;
        }
    }

    private static long fibonacci2(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci2(n - 1) + fibonacci2(n - 2);
    }

    private static long fibonacci3(int n, long[] a) {
        if (a[n] > -1) return a[n];
        if (n == 0) return 0;
        if (n == 1) return 1;
        long first = fibonacci3(n - 1, a);
        a[n - 1] = first;
        long second = fibonacci3(n - 2, a);
        a[n - 2] = second;
        return first + second;
    }
}
