package initialandcleanup;

public class Up {
    public Up(float x) {
        System.err.println("float " + x);
    }
    public Up(double x) {
        System.err.println("double " + x);
    }

    public static void main(String[] args) {
        int x = 5;
        new Up(x);
        new Up(x).new Inner();
        new Up.StaticInner();
    }

    int x;
    static int y;
    class Inner {
        Inner() {
            System.err.println(x);
            System.err.println(y);
            System.err.println("initialize inner..");
        }
    }
    static class StaticInner {
        StaticInner() {
//            不能访问非静态成员
//            System.err.println(x);
            System.err.println(y);
            System.err.println("initialize static inner..");
        }
    }
}
