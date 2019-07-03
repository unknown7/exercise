package reuse;

public class A {
    public A() {
        System.err.println("init a..");
    }

    public static void main(String[] args) {
        C c = new C();
    }
}
class B {
    public B(int i) {
        System.err.println("init b(" + i + ")");
    }
}
class C extends A {
    B b1 = new B(1);
    static B b2 = new B(2);
    C() {
        System.err.println("init c..");
    }
}