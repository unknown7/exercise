package flowcontrol;

public class Fibonacci {
    private static final int N = 10;

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            System.err.print(fibonacci(i) + " ");
        }
    }

    private static int fibonacci(int n) {
        if (n > 1) {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
        return 1;
    }
}
