package test;

public class CastTest {
    private C c;
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        CastTest ct = new CastTest();
        ct.c = (C) ct.create(A.class).createB();
    }
    public <T> T create(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
}
class A {
    public B createB() {
        return new B();
    }
}
class B extends A {
    public void hide() {
        System.err.println("hide");
    }
}
class C extends B{}
class D extends C{}