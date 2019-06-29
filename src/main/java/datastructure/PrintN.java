package datastructure;

public class PrintN {
    int n;

    public PrintN(int n) {
        this.n = n;
    }
    public static void main(String[] args) {
        PrintN printN = new PrintN(7);
        printN.loop();
        System.err.println();
        printN.recursion(1);
    }
    void loop() {
        System.err.println("loop..");
        for (int i = 1; i <= n; i++) {
            System.err.print(i + " ");
        }
    }
    void recursion(int t) {
        if (t == 1)
            System.err.println("recursion..");
        if (t <= n) {
            System.err.print(t + " ");
            recursion(++t);
        }
    }
}
