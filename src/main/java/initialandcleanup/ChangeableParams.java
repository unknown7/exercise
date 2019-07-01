package initialandcleanup;

public class ChangeableParams {
    public static void main(String[] args) {
        ChangeableParams cp = new ChangeableParams();
        cp.f(1);
    }
    void f(int... i) {
        System.err.println("int");
    }
    void f(String... s) {
        System.err.println("String");
    }
}
